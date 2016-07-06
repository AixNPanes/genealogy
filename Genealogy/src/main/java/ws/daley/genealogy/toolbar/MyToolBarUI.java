/*
 * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package ws.daley.genealogy.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.IllegalComponentStateException;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.ToolBarUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicBorders;

import sun.swing.DefaultLookup;
import sun.swing.UIAction;
import ws.daley.genealogy.components.MyLazyActionMap;
import ws.daley.genealogy.laf.family.FamilyLookAndFeel;

/**
 * A Basic L&amp;F implementation of ToolBarUI. This implementation is a
 * "combined" view/controller.
 * <p>
 *
 * @author Georges Saab
 * @author Jeff Shapiro
 */
public class MyToolBarUI extends ToolBarUI implements SwingConstants
{
	protected MyToolBar toolBar;
	private boolean floating;
	private int floatingX;
	private int floatingY;
	private RootPaneContainer floatingToolBar;
	protected DragWindow dragWindow;
	private Container dockingSource;
	private int dockingSensitivity = 0;
	protected int focusedCompIndex = -1;

	protected Color dockingColor = null;
	protected Color floatingColor = null;
	protected Color dockingBorderColor = null;
	protected Color floatingBorderColor = null;

	protected MouseInputListener dockingListener;
	protected PropertyChangeListener propertyListener;

	protected ContainerListener toolBarContListener;
	protected FocusListener toolBarFocusListener;
	private Handler handler;

	protected String constraintBeforeFloating = BorderLayout.NORTH;

	// Rollover button implementation.
	private static String IS_ROLLOVER = "JToolBar.isRollover";
	private static Border rolloverBorder;
	private static Border nonRolloverBorder;
	private static Border nonRolloverToggleBorder;
	private boolean rolloverBorders = false;

	private HashMap<AbstractButton, Border> borderTable = new HashMap<AbstractButton, Border>();
	private Hashtable<AbstractButton, Boolean> rolloverTable = new Hashtable<AbstractButton, Boolean>();

	/**
	 * As of Java 2 platform v1.3 this previously undocumented field is no
	 * longer used. Key bindings are now defined by the LookAndFeel, please
	 * refer to the key bindings specification for further details.
	 *
	 * @deprecated As of Java 2 platform v1.3.
	 */
	@Deprecated
	protected KeyStroke upKey;
	/**
	 * As of Java 2 platform v1.3 this previously undocumented field is no
	 * longer used. Key bindings are now defined by the LookAndFeel, please
	 * refer to the key bindings specification for further details.
	 *
	 * @deprecated As of Java 2 platform v1.3.
	 */
	@Deprecated
	protected KeyStroke downKey;
	/**
	 * As of Java 2 platform v1.3 this previously undocumented field is no
	 * longer used. Key bindings are now defined by the LookAndFeel, please
	 * refer to the key bindings specification for further details.
	 *
	 * @deprecated As of Java 2 platform v1.3.
	 */
	@Deprecated
	protected KeyStroke leftKey;
	/**
	 * As of Java 2 platform v1.3 this previously undocumented field is no
	 * longer used. Key bindings are now defined by the LookAndFeel, please
	 * refer to the key bindings specification for further details.
	 *
	 * @deprecated As of Java 2 platform v1.3.
	 */
	@Deprecated
	protected KeyStroke rightKey;

	private static String FOCUSED_COMP_INDEX = "JToolBar.focusedCompIndex";

	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		return new MyToolBarUI();
	}

	@Override
	public void installUI(JComponent c)
	{
		this.toolBar = (MyToolBar) c;

		// Set defaults
		installDefaults();
		installComponents();
		installListeners();
		installKeyboardActions();

		// Initialize instance vars
		this.dockingSensitivity = 0;
		this.floating = false;
		this.floatingX = this.floatingY = 0;
		this.floatingToolBar = null;

		setOrientation(this.toolBar.getOrientation());
		LookAndFeel.installProperty(c, "opaque", Boolean.TRUE);

		if (c.getClientProperty(FOCUSED_COMP_INDEX) != null)
		{
			this.focusedCompIndex = ((Integer) (c.getClientProperty(FOCUSED_COMP_INDEX))).intValue();
		}
	}

	@Override
	public void uninstallUI(JComponent c)
	{

		// Clear defaults
		uninstallDefaults();
		uninstallComponents();
		uninstallListeners();
		uninstallKeyboardActions();

		// Clear instance vars
		if (isFloating()) setFloating(false, null);

		this.floatingToolBar = null;
		this.dragWindow = null;
		this.dockingSource = null;

		c.putClientProperty(FOCUSED_COMP_INDEX, Integer.valueOf(this.focusedCompIndex));
	}

	protected void installDefaults()
	{
		LookAndFeel.installBorder(this.toolBar, "ToolBar.border");
		LookAndFeel.installColorsAndFont(this.toolBar, "ToolBar.background", "ToolBar.foreground", "ToolBar.font");
		// Toolbar specific defaults
		if (this.dockingColor == null || this.dockingColor instanceof UIResource)
		    this.dockingColor = UIManager.getColor("ToolBar.dockingBackground");
		if (this.floatingColor == null || this.floatingColor instanceof UIResource)
		    this.floatingColor = UIManager.getColor("ToolBar.floatingBackground");
		if (this.dockingBorderColor == null || this.dockingBorderColor instanceof UIResource)
		    this.dockingBorderColor = UIManager.getColor("ToolBar.dockingForeground");
		if (this.floatingBorderColor == null || this.floatingBorderColor instanceof UIResource)
		    this.floatingBorderColor = UIManager.getColor("ToolBar.floatingForeground");

		// ToolBar rollover button borders
		Object rolloverProp = this.toolBar.getClientProperty(IS_ROLLOVER);
		if (rolloverProp == null)
		{
			rolloverProp = UIManager.get("ToolBar.isRollover");
		}
		if (rolloverProp != null)
		{
			this.rolloverBorders = ((Boolean) rolloverProp).booleanValue();
		}

		if (rolloverBorder == null)
		{
			rolloverBorder = createRolloverBorder();
		}
		if (nonRolloverBorder == null)
		{
			nonRolloverBorder = createNonRolloverBorder();
		}
		if (nonRolloverToggleBorder == null)
		{
			nonRolloverToggleBorder = createNonRolloverToggleBorder();
		}

		setRolloverBorders(isRolloverBorders());
	}

	protected void uninstallDefaults()
	{
		LookAndFeel.uninstallBorder(this.toolBar);
		this.dockingColor = null;
		this.floatingColor = null;
		this.dockingBorderColor = null;
		this.floatingBorderColor = null;

		installNormalBorders(this.toolBar);

		rolloverBorder = null;
		nonRolloverBorder = null;
		nonRolloverToggleBorder = null;
	}

	protected void installComponents()
	{}

	protected void uninstallComponents()
	{}

	protected void installListeners()
	{
		this.dockingListener = createDockingListener();

		if (this.dockingListener != null)
		{
			this.toolBar.addMouseMotionListener(this.dockingListener);
			this.toolBar.addMouseListener(this.dockingListener);
		}

		this.propertyListener = createPropertyListener(); // added in
		                                                  // setFloating
		if (this.propertyListener != null)
		{
			this.toolBar.addPropertyChangeListener(this.propertyListener);
		}

		this.toolBarContListener = createToolBarContListener();
		if (this.toolBarContListener != null)
		{
			this.toolBar.addContainerListener(this.toolBarContListener);
		}

		this.toolBarFocusListener = createToolBarFocusListener();

		if (this.toolBarFocusListener != null)
		{
			// Put focus listener on all components in toolbar
			Component[] components = this.toolBar.getComponents();

			for (Component component: components)
			{
				component.addFocusListener(this.toolBarFocusListener);
			}
		}
	}

	protected void uninstallListeners()
	{
		if (this.dockingListener != null)
		{
			this.toolBar.removeMouseMotionListener(this.dockingListener);
			this.toolBar.removeMouseListener(this.dockingListener);

			this.dockingListener = null;
		}

		if (this.propertyListener != null)
		{
			this.toolBar.removePropertyChangeListener(this.propertyListener);
			this.propertyListener = null; // removed in setFloating
		}

		if (this.toolBarContListener != null)
		{
			this.toolBar.removeContainerListener(this.toolBarContListener);
			this.toolBarContListener = null;
		}

		if (this.toolBarFocusListener != null)
		{
			// Remove focus listener from all components in toolbar
			Component[] components = this.toolBar.getComponents();

			for (Component component: components)
			{
				component.removeFocusListener(this.toolBarFocusListener);
			}

			this.toolBarFocusListener = null;
		}
		this.handler = null;
	}

	protected void installKeyboardActions()
	{
		InputMap km = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		SwingUtilities.replaceUIInputMap(this.toolBar, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, km);

		MyLazyActionMap.installLazyActionMap(this.toolBar, MyToolBarUI.class, "ToolBar.actionMap");
	}

	InputMap getInputMap(int condition)
	{
		if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) { return (InputMap) DefaultLookup
		        .get(this.toolBar, this, "ToolBar.ancestorInputMap"); }
		return null;
	}

	static void loadActionMap(MyLazyActionMap map)
	{
		map.put(new Actions(Actions.NAVIGATE_RIGHT));
		map.put(new Actions(Actions.NAVIGATE_LEFT));
		map.put(new Actions(Actions.NAVIGATE_UP));
		map.put(new Actions(Actions.NAVIGATE_DOWN));
	}

	protected void uninstallKeyboardActions()
	{
		SwingUtilities.replaceUIActionMap(this.toolBar, null);
		SwingUtilities.replaceUIInputMap(this.toolBar, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, null);
	}

	protected void navigateFocusedComp(int direction)
	{
		int nComp = this.toolBar.getComponentCount();
		int j;

		switch(direction)
		{
			case EAST:
			case SOUTH:

				if (this.focusedCompIndex < 0 || this.focusedCompIndex >= nComp) break;

				j = this.focusedCompIndex + 1;

				while (j != this.focusedCompIndex)
				{
					if (j >= nComp) j = 0;
					Component comp = this.toolBar.getComponentAtIndex(j++);

					if (comp != null && comp.isFocusable() && comp.isEnabled())
					{
						comp.requestFocus();
						break;
					}
				}

				break;

			case WEST:
			case NORTH:

				if (this.focusedCompIndex < 0 || this.focusedCompIndex >= nComp) break;

				j = this.focusedCompIndex - 1;

				while (j != this.focusedCompIndex)
				{
					if (j < 0) j = nComp - 1;
					Component comp = this.toolBar.getComponentAtIndex(j--);

					if (comp != null && comp.isFocusable() && comp.isEnabled())
					{
						comp.requestFocus();
						break;
					}
				}

				break;

			default:
				break;
		}
	}

	@SuppressWarnings("serial")
	static class RolloverMarginBorder extends EmptyBorder
	{

		public RolloverMarginBorder()
		{
			super(3, 3, 3, 3); // hardcoded margin for JLF requirements.
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets)
		{
			Insets margin = null;

			if (c instanceof AbstractButton)
			{
				margin = ((AbstractButton) c).getMargin();
			}
			if (margin == null || margin instanceof UIResource)
			{
				// default margin so replace
				insets.left = this.left;
				insets.top = this.top;
				insets.right = this.right;
				insets.bottom = this.bottom;
			}
			else
			{
				// Margin which has been explicitly set by the user.
				insets.left = margin.left;
				insets.top = margin.top;
				insets.right = margin.right;
				insets.bottom = margin.bottom;
			}
			return insets;
		}
	}

	/**
	 * Creates a rollover border for toolbar components. The rollover border
	 * will be installed if rollover borders are enabled.
	 * <p>
	 * Override this method to provide an alternate rollover border.
	 *
	 * @since 1.4
	 */
	protected Border createRolloverBorder()
	{
		Object border = UIManager.get("ToolBar.rolloverBorder");
		if (border != null) { return (Border) border; }
		UIDefaults table = UIManager.getLookAndFeelDefaults();
		return new CompoundBorder(new BasicBorders.RolloverButtonBorder(table.getColor("controlShadow"),
		        table.getColor("controlDkShadow"), table.getColor("controlHighlight"),
		        table.getColor("controlLtHighlight")), new RolloverMarginBorder());
	}

	/**
	 * Creates the non rollover border for toolbar components. This border will
	 * be installed as the border for components added to the toolbar if
	 * rollover borders are not enabled.
	 * <p>
	 * Override this method to provide an alternate rollover border.
	 *
	 * @since 1.4
	 */
	protected Border createNonRolloverBorder()
	{
		Object border = UIManager.get("ToolBar.nonrolloverBorder");
		if (border != null) { return (Border) border; }
		UIDefaults table = UIManager.getLookAndFeelDefaults();
		return new CompoundBorder(
		        new BasicBorders.ButtonBorder(table.getColor("Button.shadow"), table.getColor("Button.darkShadow"),
		                table.getColor("Button.light"), table.getColor("Button.highlight")),
		        new RolloverMarginBorder());
	}

	/**
	 * Creates a non rollover border for Toggle buttons in the toolbar.
	 */
	private Border createNonRolloverToggleBorder()
	{
		UIDefaults table = UIManager.getLookAndFeelDefaults();
		return new CompoundBorder(new BasicBorders.RadioButtonBorder(table.getColor("ToggleButton.shadow"),
		        table.getColor("ToggleButton.darkShadow"), table.getColor("ToggleButton.light"),
		        table.getColor("ToggleButton.highlight")), new RolloverMarginBorder());
	}

	/**
	 * No longer used, use BasicToolBarUI.createFloatingWindow(JToolBar)
	 * 
	 * @see #createFloatingWindow
	 */
	protected JFrame createFloatingFrame(JToolBar toolbar)
	{
		Window window = SwingUtilities.getWindowAncestor(toolbar);
		@SuppressWarnings("serial")
		JFrame frame = new JFrame(toolbar.getName(), (window != null) ? window.getGraphicsConfiguration() : null)
		{
			// Override createRootPane() to automatically resize
		    // the frame when contents change
			@Override
			protected JRootPane createRootPane()
			{
				@SuppressWarnings("hiding")
				JRootPane rootPane = new JRootPane()
				{
					private boolean packing = false;

					@Override
					public void validate()
					{
						super.validate();
						if (!this.packing)
						{
							this.packing = true;
							pack();
							this.packing = false;
						}
					}
				};
				rootPane.setOpaque(true);
				return rootPane;
			}
		};
		frame.getRootPane().setName("ToolBar.FloatingFrame");
		frame.setResizable(false);
		WindowListener wl = createFrameListener();
		frame.addWindowListener(wl);
		return frame;
	}

	/**
	 * Creates a window which contains the toolbar after it has been dragged out
	 * from its container
	 * 
	 * @return a <code>RootPaneContainer</code> object, containing the toolbar.
	 * @since 1.4
	 */
	protected RootPaneContainer createFloatingWindow(MyToolBar toolbar)
	{
		@SuppressWarnings("serial")
		class ToolBarDialog extends JDialog
		{
			public ToolBarDialog(Frame owner, String title, boolean modal)
			{
				super(owner, title, modal);
			}

			public ToolBarDialog(Dialog owner, String title, boolean modal)
			{
				super(owner, title, modal);
			}

			// Override createRootPane() to automatically resize
			// the frame when contents change
			@Override
			protected JRootPane createRootPane()
			{
				@SuppressWarnings("hiding")
				JRootPane rootPane = new JRootPane()
				{
					private boolean packing = false;

					@Override
					public void validate()
					{
						super.validate();
						if (!this.packing)
						{
							this.packing = true;
							pack();
							this.packing = false;
						}
					}
				};
				rootPane.setOpaque(true);
				return rootPane;
			}
		}

		JDialog dialog;
		Window window = SwingUtilities.getWindowAncestor(toolbar);
		if (window instanceof Frame)
		{
			dialog = new ToolBarDialog((Frame) window, toolbar.getName(), false);
		}
		else if (window instanceof Dialog)
		{
			dialog = new ToolBarDialog((Dialog) window, toolbar.getName(), false);
		}
		else
		{
			dialog = new ToolBarDialog((Frame) null, toolbar.getName(), false);
		}

		dialog.getRootPane().setName("ToolBar.FloatingWindow");
		dialog.setTitle(toolbar.getName());
		dialog.setResizable(false);
		WindowListener wl = createFrameListener();
		dialog.addWindowListener(wl);
		return dialog;
	}

	protected DragWindow createDragWindow(@SuppressWarnings("unused") MyToolBar toolbar)
	{
		Window frame = null;
		if (this.toolBar != null)
		{
			Container p;
			for (p = this.toolBar.getParent(); p != null && !(p instanceof Window); p = p.getParent());
			if (p != null && p instanceof Window) frame = (Window) p;
		}
		if (this.floatingToolBar == null)
		{
			this.floatingToolBar = createFloatingWindow(this.toolBar);
		}
		if (this.floatingToolBar instanceof Window) frame = (Window) this.floatingToolBar;
		@SuppressWarnings("hiding")
		DragWindow dragWindow = new DragWindow(frame);
		return dragWindow;
	}

	/**
	 * Returns a flag to determine whether rollover button borders are enabled.
	 *
	 * @return true if rollover borders are enabled; false otherwise
	 * @see #setRolloverBorders
	 * @since 1.4
	 */
	public boolean isRolloverBorders()
	{
		return this.rolloverBorders;
	}

	/**
	 * Sets the flag for enabling rollover borders on the toolbar and it will
	 * also install the appropriate border depending on the state of the flag.
	 *
	 * @param rollover
	 *            if true, rollover borders are installed. Otherwise
	 *            non-rollover borders are installed
	 * @see #isRolloverBorders
	 * @since 1.4
	 */
	public void setRolloverBorders(boolean rollover)
	{
		this.rolloverBorders = rollover;

		if (this.rolloverBorders)
		{
			installRolloverBorders(this.toolBar);
		}
		else
		{
			installNonRolloverBorders(this.toolBar);
		}
	}

	/**
	 * Installs rollover borders on all the child components of the JComponent.
	 * <p>
	 * This is a convenience method to call <code>setBorderToRollover</code> for
	 * each child component.
	 *
	 * @param c
	 *            container which holds the child components (usually a
	 *            JToolBar)
	 * @see #setBorderToRollover
	 * @since 1.4
	 */
	protected void installRolloverBorders(JComponent c)
	{
		// Put rollover borders on buttons
		Component[] components = c.getComponents();

		for (Component component: components)
		{
			if (component instanceof JComponent)
			{
				((JComponent) component).updateUI();
				setBorderToRollover(component);
			}
		}
	}

	/**
	 * Installs non-rollover borders on all the child components of the
	 * JComponent. A non-rollover border is the border that is installed on the
	 * child component while it is in the toolbar.
	 * <p>
	 * This is a convenience method to call <code>setBorderToNonRollover</code>
	 * for each child component.
	 *
	 * @param c
	 *            container which holds the child components (usually a
	 *            JToolBar)
	 * @see #setBorderToNonRollover
	 * @since 1.4
	 */
	protected void installNonRolloverBorders(JComponent c)
	{
		// Put non-rollover borders on buttons. These borders reduce the margin.
		Component[] components = c.getComponents();

		for (Component component: components)
		{
			if (component instanceof JComponent)
			{
				((JComponent) component).updateUI();
				setBorderToNonRollover(component);
			}
		}
	}

	/**
	 * Installs normal borders on all the child components of the JComponent. A
	 * normal border is the original border that was installed on the child
	 * component before it was added to the toolbar.
	 * <p>
	 * This is a convenience method to call <code>setBorderNormal</code> for
	 * each child component.
	 *
	 * @param c
	 *            container which holds the child components (usually a
	 *            JToolBar)
	 * @see #setBorderToNonRollover
	 * @since 1.4
	 */
	protected void installNormalBorders(JComponent c)
	{
		// Put back the normal borders on buttons
		Component[] components = c.getComponents();

		for (Component component: components)
		{
			setBorderToNormal(component);
		}
	}

	/**
	 * Sets the border of the component to have a rollover border which was
	 * created by the {@link #createRolloverBorder} method.
	 *
	 * @param c
	 *            component which will have a rollover border installed
	 * @see #createRolloverBorder
	 * @since 1.4
	 */
	protected void setBorderToRollover(Component c)
	{
		if (c instanceof AbstractButton)
		{
			AbstractButton b = (AbstractButton) c;

			Border border = this.borderTable.get(b);
			if (border == null || border instanceof UIResource)
			{
				this.borderTable.put(b, b.getBorder());
			}

			// Only set the border if its the default border
			if (b.getBorder() instanceof UIResource)
			{
				b.setBorder(getRolloverBorder(b));
			}

			this.rolloverTable.put(b, b.isRolloverEnabled() ? Boolean.TRUE : Boolean.FALSE);
			b.setRolloverEnabled(true);
		}
	}

	/**
	 * Returns a rollover border for the button.
	 *
	 * @param b
	 *            the button to calculate the rollover border for
	 * @return the rollover border
	 * @see #setBorderToRollover
	 * @since 1.6
	 */
	protected Border getRolloverBorder(AbstractButton b)
	{
		return rolloverBorder;
	}

	/**
	 * Sets the border of the component to have a non-rollover border which was
	 * created by the {@link #createNonRolloverBorder} method.
	 *
	 * @param c
	 *            component which will have a non-rollover border installed
	 * @see #createNonRolloverBorder
	 * @since 1.4
	 */
	protected void setBorderToNonRollover(Component c)
	{
		if (c instanceof AbstractButton)
		{
			AbstractButton b = (AbstractButton) c;

			Border border = this.borderTable.get(b);
			if (border == null || border instanceof UIResource)
			{
				this.borderTable.put(b, b.getBorder());
			}

			// Only set the border if its the default border
			if (b.getBorder() instanceof UIResource)
			{
				b.setBorder(getNonRolloverBorder(b));
			}
			this.rolloverTable.put(b, b.isRolloverEnabled() ? Boolean.TRUE : Boolean.FALSE);
			b.setRolloverEnabled(false);
		}
	}

	/**
	 * Returns a non-rollover border for the button.
	 *
	 * @param b
	 *            the button to calculate the non-rollover border for
	 * @return the non-rollover border
	 * @see #setBorderToNonRollover
	 * @since 1.6
	 */
	protected Border getNonRolloverBorder(AbstractButton b)
	{
		if (b instanceof JToggleButton) return nonRolloverToggleBorder;
		return nonRolloverBorder;
	}

	/**
	 * Sets the border of the component to have a normal border. A normal border
	 * is the original border that was installed on the child component before
	 * it was added to the toolbar.
	 *
	 * @param c
	 *            component which will have a normal border re-installed
	 * @see #createNonRolloverBorder
	 * @since 1.4
	 */
	protected void setBorderToNormal(Component c)
	{
		if (c instanceof AbstractButton)
		{
			AbstractButton b = (AbstractButton) c;

			Border border = this.borderTable.remove(b);
			b.setBorder(border);

			Boolean value = this.rolloverTable.remove(b);
			if (value != null)
			{
				b.setRolloverEnabled(value.booleanValue());
			}
		}
	}

	public void setFloatingLocation(int x, int y)
	{
		this.floatingX = x;
		this.floatingY = y;
	}

	public boolean isFloating()
	{
		return this.floating;
	}

	public void setFloating(boolean b, Point p)
	{
		if (this.toolBar.isFloatable())
		{
			boolean visible = false;
			Window ancestor = SwingUtilities.getWindowAncestor(this.toolBar);
			if (ancestor != null)
			{
				visible = ancestor.isVisible();
			}
			if (this.dragWindow != null) this.dragWindow.setVisible(false);
			this.floating = b;
			if (this.floatingToolBar == null)
			{
				this.floatingToolBar = createFloatingWindow(this.toolBar);
			}
			if (b == true)
			{
				if (this.dockingSource == null)
				{
					this.dockingSource = this.toolBar.getParent();
					this.dockingSource.remove(this.toolBar);
				}
				this.constraintBeforeFloating = calculateConstraint();
				if (this.propertyListener != null) UIManager.addPropertyChangeListener(this.propertyListener);
				this.floatingToolBar.getContentPane().add(this.toolBar, BorderLayout.CENTER);
				if (this.floatingToolBar instanceof Window)
				{
					((Window) this.floatingToolBar).pack();
					((Window) this.floatingToolBar).setLocation(this.floatingX, this.floatingY);
					if (visible)
					{
						((Window) this.floatingToolBar).setVisible(true);
					}
					else
					{
						ancestor.addWindowListener(new WindowAdapter()
						{
							@SuppressWarnings("synthetic-access")
							@Override
							public void windowOpened(@SuppressWarnings("unused") WindowEvent e)
							{
								((Window) MyToolBarUI.this.floatingToolBar).setVisible(true);
							}
						});
					}
				}
			}
			else
			{
				if (this.floatingToolBar == null) this.floatingToolBar = createFloatingWindow(this.toolBar);
				if (this.floatingToolBar instanceof Window) ((Window) this.floatingToolBar).setVisible(false);
				this.floatingToolBar.getContentPane().remove(this.toolBar);
				String constraint = getDockingConstraint(this.dockingSource, p);
				if (constraint == null)
				{
					constraint = BorderLayout.NORTH;
				}
				int orientation = mapConstraintToOrientation(constraint);
				setOrientation(orientation);
				if (this.dockingSource == null) this.dockingSource = this.toolBar.getParent();
				if (this.propertyListener != null) UIManager.removePropertyChangeListener(this.propertyListener);
				this.dockingSource.add(constraint, this.toolBar);
			}
			this.dockingSource.invalidate();
			Container dockingSourceParent = this.dockingSource.getParent();
			if (dockingSourceParent != null) dockingSourceParent.validate();
			this.dockingSource.repaint();
		}
	}

	private int mapConstraintToOrientation(String constraint)
	{
		int orientation = this.toolBar.getOrientation();

		if (constraint != null)
		{
			if (constraint.equals(BorderLayout.EAST) || constraint.equals(BorderLayout.WEST))
			    orientation = SwingConstants.VERTICAL;
			else if (constraint.equals(BorderLayout.NORTH) || constraint.equals(BorderLayout.SOUTH))
			    orientation = SwingConstants.HORIZONTAL;
		}

		return orientation;
	}

	public void setOrientation(int orientation)
	{
		this.toolBar.setOrientation(orientation);

		if (this.dragWindow != null) this.dragWindow.setOrientation(orientation);
	}

	/**
	 * Gets the color displayed when over a docking area
	 */
	public Color getDockingColor()
	{
		return this.dockingColor;
	}

	/**
	 * Sets the color displayed when over a docking area
	 */
	public void setDockingColor(Color c)
	{
		this.dockingColor = c;
	}

	/**
	 * Gets the color displayed when over a floating area
	 */
	public Color getFloatingColor()
	{
		return this.floatingColor;
	}

	/**
	 * Sets the color displayed when over a floating area
	 */
	public void setFloatingColor(Color c)
	{
		this.floatingColor = c;
	}

	private boolean isBlocked(Component comp, Object constraint)
	{
		if (comp instanceof Container)
		{
			Container cont = (Container) comp;
			LayoutManager lm = cont.getLayout();
			if (lm instanceof BorderLayout)
			{
				BorderLayout blm = (BorderLayout) lm;
				Component c = blm.getLayoutComponent(cont, constraint);
				return (c != null && c != this.toolBar);
			}
		}
		return false;
	}

	public boolean canDock(Component c, Point p)
	{
		return (p != null && getDockingConstraint(c, p) != null);
	}

	private String calculateConstraint()
	{
		String constraint = null;
		LayoutManager lm = this.dockingSource.getLayout();
		if (lm instanceof BorderLayout)
		{
			constraint = (String) ((BorderLayout) lm).getConstraints(this.toolBar);
		}
		return (constraint != null) ? constraint : this.constraintBeforeFloating;
	}

	private String getDockingConstraint(Component c, Point p)
	{
		if (p == null) return this.constraintBeforeFloating;
		if (c.contains(p))
		{
			this.dockingSensitivity = (this.toolBar.getOrientation() == SwingConstants.HORIZONTAL)
			        ? this.toolBar.getSize().height : this.toolBar.getSize().width;
			// North (Base distance on height for now!)
			if (p.y < this.dockingSensitivity && !isBlocked(c, BorderLayout.NORTH)) { return BorderLayout.NORTH; }
			// East (Base distance on height for now!)
			if (p.x >= c.getWidth() - this.dockingSensitivity
			        && !isBlocked(c, BorderLayout.EAST)) { return BorderLayout.EAST; }
			// West (Base distance on height for now!)
			if (p.x < this.dockingSensitivity && !isBlocked(c, BorderLayout.WEST)) { return BorderLayout.WEST; }
			if (p.y >= c.getHeight() - this.dockingSensitivity
			        && !isBlocked(c, BorderLayout.SOUTH)) { return BorderLayout.SOUTH; }
		}
		return null;
	}

	protected void dragTo(Point position, Point origin)
	{
		if (this.toolBar.isFloatable())
		{
			try
			{
				if (this.dragWindow == null) this.dragWindow = createDragWindow(this.toolBar);
				Point offset = this.dragWindow.getOffset();
				if (offset == null)
				{
					Dimension size = this.toolBar.getPreferredSize();
					offset = new Point(size.width / 2, size.height / 2);
					this.dragWindow.setOffset(offset);
				}
				Point global = new Point(origin.x + position.x, origin.y + position.y);
				Point dragPoint = new Point(global.x - offset.x, global.y - offset.y);
				if (this.dockingSource == null) this.dockingSource = this.toolBar.getParent();
				this.constraintBeforeFloating = calculateConstraint();
				Point dockingPosition = this.dockingSource.getLocationOnScreen();
				Point comparisonPoint = new Point(global.x - dockingPosition.x, global.y - dockingPosition.y);
				if (canDock(this.dockingSource, comparisonPoint))
				{
					this.dragWindow.setBackground(getDockingColor());
					String constraint = getDockingConstraint(this.dockingSource, comparisonPoint);
					int orientation = mapConstraintToOrientation(constraint);
					this.dragWindow.setOrientation(orientation);
					this.dragWindow.setBorderColor(this.dockingBorderColor);
				}
				else
				{
					this.dragWindow.setBackground(getFloatingColor());
					this.dragWindow.setBorderColor(this.floatingBorderColor);
					this.dragWindow.setOrientation(this.toolBar.getOrientation());
				}

				this.dragWindow.setLocation(dragPoint.x, dragPoint.y);
				if (this.dragWindow.isVisible() == false)
				{
					Dimension size = this.toolBar.getPreferredSize();
					this.dragWindow.setSize(size.width, size.height);
					this.dragWindow.setVisible(true);
				}
			}
			catch (IllegalComponentStateException e)
			{}
		}
	}

	protected void floatAt(Point position, Point origin)
	{
		if (this.toolBar.isFloatable())
		{
			try
			{
				Point offset = this.dragWindow.getOffset();
				if (offset == null)
				{
					offset = position;
					this.dragWindow.setOffset(offset);
				}
				Point global = new Point(origin.x + position.x, origin.y + position.y);
				setFloatingLocation(global.x - offset.x, global.y - offset.y);
				if (this.dockingSource != null)
				{
					Point dockingPosition = this.dockingSource.getLocationOnScreen();
					Point comparisonPoint = new Point(global.x - dockingPosition.x, global.y - dockingPosition.y);
					if (canDock(this.dockingSource, comparisonPoint))
					{
						setFloating(false, comparisonPoint);
					}
					else
					{
						setFloating(true, null);
					}
				}
				else
				{
					setFloating(true, null);
				}
				this.dragWindow.setOffset(null);
			}
			catch (IllegalComponentStateException e)
			{}
		}
	}

	@SuppressWarnings("synthetic-access")
	private Handler getHandler()
	{
		if (this.handler == null)
		{
			this.handler = new Handler();
		}
		return this.handler;
	}

	protected ContainerListener createToolBarContListener()
	{
		return getHandler();
	}

	protected FocusListener createToolBarFocusListener()
	{
		return getHandler();
	}

	protected PropertyChangeListener createPropertyListener()
	{
		return getHandler();
	}

	protected MouseInputListener createDockingListener()
	{
		getHandler().tb = this.toolBar;
		return getHandler();
	}

	protected WindowListener createFrameListener()
	{
		return new FrameListener();
	}

	/**
	 * Paints the contents of the window used for dragging.
	 *
	 * @param g
	 *            Graphics to paint to.
	 * @throws NullPointerException
	 *             is <code>g</code> is null
	 * @since 1.5
	 */
	protected void paintDragWindow(Graphics g)
	{
		g.setColor(this.dragWindow.getBackground());
		int w = this.dragWindow.getWidth();
		int h = this.dragWindow.getHeight();
		g.fillRect(0, 0, w, h);
		g.setColor(this.dragWindow.getBorderColor());
		g.drawRect(0, 0, w - 1, h - 1);
	}

	private static class Actions extends UIAction
	{
		private static final String NAVIGATE_RIGHT = "navigateRight";
		private static final String NAVIGATE_LEFT = "navigateLeft";
		private static final String NAVIGATE_UP = "navigateUp";
		private static final String NAVIGATE_DOWN = "navigateDown";

		public Actions(String name)
		{
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent evt)
		{
			String key = getName();
			JToolBar toolBar = (JToolBar) evt.getSource();
			MyToolBarUI ui = (MyToolBarUI) FamilyLookAndFeel.getUIOfType(toolBar.getUI(), MyToolBarUI.class);

			if (NAVIGATE_RIGHT == key)
			{
				ui.navigateFocusedComp(EAST);
			}
			else if (NAVIGATE_LEFT == key)
			{
				ui.navigateFocusedComp(WEST);
			}
			else if (NAVIGATE_UP == key)
			{
				ui.navigateFocusedComp(NORTH);
			}
			else if (NAVIGATE_DOWN == key)
			{
				ui.navigateFocusedComp(SOUTH);
			}
		}
	}

	private class Handler implements ContainerListener, FocusListener, MouseInputListener, PropertyChangeListener
	{

		//
		// ContainerListener
		//
		@Override
		public void componentAdded(ContainerEvent evt)
		{
			Component c = evt.getChild();

			if (MyToolBarUI.this.toolBarFocusListener != null)
			{
				c.addFocusListener(MyToolBarUI.this.toolBarFocusListener);
			}

			if (isRolloverBorders())
			{
				setBorderToRollover(c);
			}
			else
			{
				setBorderToNonRollover(c);
			}
		}

		@Override
		public void componentRemoved(ContainerEvent evt)
		{
			Component c = evt.getChild();

			if (MyToolBarUI.this.toolBarFocusListener != null)
			{
				c.removeFocusListener(MyToolBarUI.this.toolBarFocusListener);
			}

			// Revert the button border
			setBorderToNormal(c);
		}

		//
		// FocusListener
		//
		@Override
		public void focusGained(FocusEvent evt)
		{
			Component c = evt.getComponent();
			MyToolBarUI.this.focusedCompIndex = MyToolBarUI.this.toolBar.getComponentIndex(c);
		}

		@Override
		public void focusLost(@SuppressWarnings("unused") FocusEvent evt)
		{}

		//
		// MouseInputListener (DockingListener)
		//
		MyToolBar tb;
		boolean isDragging = false;
		Point origin = null;

		@Override
		public void mousePressed(@SuppressWarnings("unused") MouseEvent evt)
		{
			if (!this.tb.isEnabled()) { return; }
			this.isDragging = false;
		}

		@Override
		public void mouseReleased(MouseEvent evt)
		{
			if (!this.tb.isEnabled()) { return; }
			if (this.isDragging)
			{
				Point position = evt.getPoint();
				if (this.origin == null) this.origin = evt.getComponent().getLocationOnScreen();
				floatAt(position, this.origin);
			}
			this.origin = null;
			this.isDragging = false;
		}

		@Override
		public void mouseDragged(MouseEvent evt)
		{
			if (!this.tb.isEnabled()) { return; }
			this.isDragging = true;
			Point position = evt.getPoint();
			if (this.origin == null)
			{
				this.origin = evt.getComponent().getLocationOnScreen();
			}
			dragTo(position, this.origin);
		}

		@Override
		public void mouseClicked(@SuppressWarnings("unused") MouseEvent evt)
		{}

		@Override
		public void mouseEntered(@SuppressWarnings("unused") MouseEvent evt)
		{}

		@Override
		public void mouseExited(@SuppressWarnings("unused") MouseEvent evt)
		{}

		@Override
		public void mouseMoved(@SuppressWarnings("unused") MouseEvent evt)
		{}

		//
		@SuppressWarnings("synthetic-access")
		// PropertyChangeListener
		//
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			String propertyName = evt.getPropertyName();
			if (propertyName == "lookAndFeel")
			{
				MyToolBarUI.this.toolBar.updateUI();
			}
			else if (propertyName == "orientation")
			{
				// Search for JSeparator components and change it's orientation
				// to match the toolbar and flip it's orientation.
				Component[] components = MyToolBarUI.this.toolBar.getComponents();
				int orientation = ((Integer) evt.getNewValue()).intValue();
				JToolBar.Separator separator;

				for (int i = 0; i < components.length; ++i)
				{
					if (components[i] instanceof JToolBar.Separator)
					{
						separator = (JToolBar.Separator) components[i];
						if ((orientation == HORIZONTAL))
						{
							separator.setOrientation(VERTICAL);
						}
						else
						{
							separator.setOrientation(HORIZONTAL);
						}
						Dimension size = separator.getSeparatorSize();
						if (size != null && size.width != size.height)
						{
							// Flip the orientation.
							Dimension newSize = new Dimension(size.height, size.width);
							separator.setSeparatorSize(newSize);
						}
					}
				}
			}
			else if (propertyName == IS_ROLLOVER)
			{
				installNormalBorders(MyToolBarUI.this.toolBar);
				setRolloverBorders(((Boolean) evt.getNewValue()).booleanValue());
			}
		}
	}

	protected class FrameListener extends WindowAdapter
	{
		@SuppressWarnings("synthetic-access")
		@Override
		public void windowClosing(@SuppressWarnings("unused") WindowEvent w)
		{
			if (MyToolBarUI.this.toolBar.isFloatable())
			{
				if (MyToolBarUI.this.dragWindow != null) MyToolBarUI.this.dragWindow.setVisible(false);
				MyToolBarUI.this.floating = false;
				if (MyToolBarUI.this.floatingToolBar == null) MyToolBarUI.this.floatingToolBar = createFloatingWindow(MyToolBarUI.this.toolBar);
				if (MyToolBarUI.this.floatingToolBar instanceof Window) ((Window) MyToolBarUI.this.floatingToolBar).setVisible(false);
				MyToolBarUI.this.floatingToolBar.getContentPane().remove(MyToolBarUI.this.toolBar);
				String constraint = MyToolBarUI.this.constraintBeforeFloating;
				if (MyToolBarUI.this.toolBar.getOrientation() == HORIZONTAL)
				{
					if (constraint == "West" || constraint == "East")
					{
						constraint = "North";
					}
				}
				else
				{
					if (constraint == "North" || constraint == "South")
					{
						constraint = "West";
					}
				}
				if (MyToolBarUI.this.dockingSource == null) MyToolBarUI.this.dockingSource = MyToolBarUI.this.toolBar.getParent();
				if (MyToolBarUI.this.propertyListener != null) UIManager.removePropertyChangeListener(MyToolBarUI.this.propertyListener);
				MyToolBarUI.this.dockingSource.add(MyToolBarUI.this.toolBar, constraint);
				MyToolBarUI.this.dockingSource.invalidate();
				Container dockingSourceParent = MyToolBarUI.this.dockingSource.getParent();
				if (dockingSourceParent != null) dockingSourceParent.validate();
				MyToolBarUI.this.dockingSource.repaint();
			}
		}

	}

	protected class ToolBarContListener implements ContainerListener
	{
		// NOTE: This class exists only for backward compatibility. All
		// its functionality has been moved into Handler. If you need to add
		// new functionality add it to the Handler, but make sure this
		// class calls into the Handler.
		@SuppressWarnings("synthetic-access")
		@Override
		public void componentAdded(ContainerEvent e)
		{
			getHandler().componentAdded(e);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void componentRemoved(ContainerEvent e)
		{
			getHandler().componentRemoved(e);
		}

	}

	protected class ToolBarFocusListener implements FocusListener
	{
		// NOTE: This class exists only for backward compatibility. All
		// its functionality has been moved into Handler. If you need to add
		// new functionality add it to the Handler, but make sure this
		// class calls into the Handler.
		@SuppressWarnings("synthetic-access")
		@Override
		public void focusGained(FocusEvent e)
		{
			getHandler().focusGained(e);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void focusLost(FocusEvent e)
		{
			getHandler().focusLost(e);
		}
	}

	protected class PropertyListener implements PropertyChangeListener
	{
		// NOTE: This class exists only for backward compatibility. All
		// its functionality has been moved into Handler. If you need to add
		// new functionality add it to the Handler, but make sure this
		// class calls into the Handler.
		@SuppressWarnings("synthetic-access")
		@Override
		public void propertyChange(PropertyChangeEvent e)
		{
			getHandler().propertyChange(e);
		}
	}

	/**
	 * This class should be treated as a &quot;protected&quot; inner class.
	 * Instantiate it only within subclasses of BasicToolBarUI.
	 */
	public class DockingListener implements MouseInputListener
	{
		// NOTE: This class exists only for backward compatibility. All
		// its functionality has been moved into Handler. If you need to add
		// new functionality add it to the Handler, but make sure this
		// class calls into the Handler.
		@SuppressWarnings("hiding")
		protected MyToolBar toolBar;
		protected boolean isDragging = false;
		protected Point origin = null;

		@SuppressWarnings("synthetic-access")
		public DockingListener(MyToolBar t)
		{
			this.toolBar = t;
			getHandler().tb = t;
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseClicked(MouseEvent e)
		{
			getHandler().mouseClicked(e);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mousePressed(MouseEvent e)
		{
			getHandler().tb = this.toolBar;
			getHandler().mousePressed(e);
			this.isDragging = getHandler().isDragging;
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseReleased(MouseEvent e)
		{
			getHandler().tb = this.toolBar;
			getHandler().isDragging = this.isDragging;
			getHandler().origin = this.origin;
			getHandler().mouseReleased(e);
			this.isDragging = getHandler().isDragging;
			this.origin = getHandler().origin;
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseEntered(MouseEvent e)
		{
			getHandler().mouseEntered(e);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseExited(MouseEvent e)
		{
			getHandler().mouseExited(e);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseDragged(MouseEvent e)
		{
			getHandler().tb = this.toolBar;
			getHandler().origin = this.origin;
			getHandler().mouseDragged(e);
			this.isDragging = getHandler().isDragging;
			this.origin = getHandler().origin;
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseMoved(MouseEvent e)
		{
			getHandler().mouseMoved(e);
		}
	}

	@SuppressWarnings("serial")
	protected class DragWindow extends Window
	{
		Color borderColor = Color.gray;
		int orientation = MyToolBarUI.this.toolBar.getOrientation();
		Point offset; // offset of the mouse cursor inside the DragWindow

		DragWindow(Window w)
		{
			super(w);
		}

		/**
		 * Returns the orientation of the toolbar window when the toolbar is
		 * floating. The orientation is either one of
		 * <code>JToolBar.HORIZONTAL</code> or <code>JToolBar.VERTICAL</code>.
		 *
		 * @return the orientation of the toolbar window
		 * @since 1.6
		 */
		public int getOrientation()
		{
			return this.orientation;
		}

		public void setOrientation(int o)
		{
			if (isShowing())
			{
				if (o == this.orientation) return;
				this.orientation = o;
				Dimension size = getSize();
				setSize(new Dimension(size.height, size.width));
				if (this.offset != null)
				{
					if (MyGraphicsUtils.isLeftToRight(MyToolBarUI.this.toolBar))
					{
						setOffset(new Point(this.offset.y, this.offset.x));
					}
					else if (o == HORIZONTAL)
					{
						setOffset(new Point(size.height - this.offset.y, this.offset.x));
					}
					else
					{
						setOffset(new Point(this.offset.y, size.width - this.offset.x));
					}
				}
				repaint();
			}
		}

		public Point getOffset()
		{
			return this.offset;
		}

		public void setOffset(Point p)
		{
			this.offset = p;
		}

		public void setBorderColor(Color c)
		{
			if (this.borderColor == c) return;
			this.borderColor = c;
			repaint();
		}

		public Color getBorderColor()
		{
			return this.borderColor;
		}

		@Override
		public void paint(Graphics g)
		{
			paintDragWindow(g);
			// Paint the children
			super.paint(g);
		}

		@Override
		public Insets getInsets()
		{
			return new Insets(1, 1, 1, 1);
		}
	}
}
