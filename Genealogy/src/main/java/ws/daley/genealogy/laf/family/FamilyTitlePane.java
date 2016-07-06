/*
 * Copyright (c) 2000, 2006, Oracle and/or its affiliates. All rights reserved.
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

package ws.daley.genealogy.laf.family;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import javax.accessibility.AccessibleContext;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.UIResource;

import sun.awt.SunToolkit;
import sun.swing.SwingUtilities2;

/**
 * Class that manages a JLF awt.Window-descendant class's title bar.
 * <p>
 * This class assumes it will be created with a particular window decoration
 * style, and that if the style changes, a new one will be created.
 *
 * @author Terry Kellerman
 * @since 1.4
 */
@SuppressWarnings({ "serial" })
class FamilyTitlePane extends JComponent
{
	private static final Border handyEmptyBorder = new EmptyBorder(0, 0, 0, 0);
	private static final int IMAGE_HEIGHT = 16;
	private static final int IMAGE_WIDTH = 16;

	/**
	 * PropertyChangeListener added to the JRootPane.
	 */
	private PropertyChangeListener propertyChangeListener;

	/**
	 * JMenuBar, typically renders the system menu items.
	 */
	private JMenuBar menuBar;
	/**
	 * Action used to close the Window.
	 */
	private Action closeAction;

	/**
	 * Action used to iconify the Frame.
	 */
	private Action iconifyAction;

	/**
	 * Action to restore the Frame size.
	 */
	private Action restoreAction;

	/**
	 * Action to restore the Frame size.
	 */
	private Action maximizeAction;

	/**
	 * Button used to maximize or restore the Frame.
	 */
	private JButton toggleButton;

	/**
	 * Button used to maximize or restore the Frame.
	 */
	private JButton iconifyButton;

	/**
	 * Button used to maximize or restore the Frame.
	 */
	private JButton closeButton;

	/**
	 * Icon used for toggleButton when window is normal size.
	 */
	private Icon maximizeIcon;

	/**
	 * Icon used for toggleButton when window is maximized.
	 */
	private Icon minimizeIcon;

	/**
	 * Image used for the system menu icon
	 */
	private Image systemIcon;

	/**
	 * Listens for changes in the state of the Window listener to update the
	 * state of the widgets.
	 */
	private WindowListener windowListener;

	/**
	 * Window we're currently in.
	 */
	private Window window;

	/**
	 * JRootPane rendering for.
	 */
	private JRootPane rootPane;

	/**
	 * Room remaining in title for bumps.
	 */
	private int buttonsWidth;

	/**
	 * Buffered Frame.state property. As state isn't bound, this is kept to
	 * determine when to avoid updating widgets.
	 */
	private int state;

	/**
	 * FamilyRootPaneUI that created us.
	 */
	private FamilyRootPaneUI rootPaneUI;

	// Colors
	private Color inactiveBackground = UIManager.getColor("inactiveCaption");
	private Color inactiveForeground = UIManager.getColor("inactiveCaptionText");
	private Color inactiveShadow = UIManager.getColor("inactiveCaptionBorder");
	private Color activeBumpsHighlight = FamilyLookAndFeel.getPrimaryControlHighlight();
	private Color activeBumpsShadow = FamilyLookAndFeel.getPrimaryControlDarkShadow();
	private Color activeBackground = null;
	private Color activeForeground = null;
	private Color activeShadow = null;

	// Bumps
	private FamilyBumps activeBumps = new FamilyBumps(0, 0, this.activeBumpsHighlight, this.activeBumpsShadow,
	        FamilyLookAndFeel.getPrimaryControl());
	private FamilyBumps inactiveBumps = new FamilyBumps(0, 0, FamilyLookAndFeel.getControlHighlight(),
	        FamilyLookAndFeel.getControlDarkShadow(), FamilyLookAndFeel.getControl());

	public FamilyTitlePane(JRootPane root, FamilyRootPaneUI ui)
	{
		this.rootPane = root;
		this.rootPaneUI = ui;

		this.state = -1;

		installSubcomponents();
		determineColors();
		installDefaults();

		setLayout(createLayout());
	}

	/**
	 * Uninstalls the necessary state.
	 */
	@SuppressWarnings("unused")
	private void uninstall()
	{
		uninstallListeners();
		this.window = null;
		removeAll();
	}

	/**
	 * Installs the necessary listeners.
	 */
	private void installListeners()
	{
		if (this.window != null)
		{
			this.windowListener = createWindowListener();
			this.window.addWindowListener(this.windowListener);
			this.propertyChangeListener = createWindowPropertyChangeListener();
			this.window.addPropertyChangeListener(this.propertyChangeListener);
		}
	}

	/**
	 * Uninstalls the necessary listeners.
	 */
	private void uninstallListeners()
	{
		if (this.window != null)
		{
			this.window.removeWindowListener(this.windowListener);
			this.window.removePropertyChangeListener(this.propertyChangeListener);
		}
	}

	/**
	 * Returns the <code>WindowListener</code> to add to the <code>Window</code>
	 * .
	 */
	@SuppressWarnings("synthetic-access")
	private WindowListener createWindowListener()
	{
		return new WindowHandler();
	}

	/**
	 * Returns the <code>PropertyChangeListener</code> to install on the
	 * <code>Window</code>.
	 */
	@SuppressWarnings("synthetic-access")
	private PropertyChangeListener createWindowPropertyChangeListener()
	{
		return new PropertyChangeHandler();
	}

	/**
	 * Returns the <code>JRootPane</code> this was created for.
	 */
	@Override
	public JRootPane getRootPane()
	{
		return this.rootPane;
	}

	/**
	 * Returns the decoration style of the <code>JRootPane</code>.
	 */
	private int getWindowDecorationStyle()
	{
		return getRootPane().getWindowDecorationStyle();
	}

	@Override
	public void addNotify()
	{
		super.addNotify();

		uninstallListeners();

		this.window = SwingUtilities.getWindowAncestor(this);
		if (this.window != null)
		{
			if (this.window instanceof Frame)
				setState(((Frame) this.window).getExtendedState());
			else
				setState(0);
			setActive(this.window.isActive());
			installListeners();
			updateSystemIcon();
		}
	}

	@Override
	public void removeNotify()
	{
		super.removeNotify();

		uninstallListeners();
		this.window = null;
	}

	/**
	 * Adds any sub-Components contained in the <code>FamilyTitlePane</code>.
	 */
	private void installSubcomponents()
	{
		int decorationStyle = getWindowDecorationStyle();
		if (decorationStyle == JRootPane.FRAME)
		{
			createActions();
			this.menuBar = createMenuBar();
			add(this.menuBar);
			createButtons();
			add(this.iconifyButton);
			add(this.toggleButton);
			add(this.closeButton);
		}
		else if (decorationStyle == JRootPane.PLAIN_DIALOG || decorationStyle == JRootPane.INFORMATION_DIALOG
		        || decorationStyle == JRootPane.ERROR_DIALOG || decorationStyle == JRootPane.COLOR_CHOOSER_DIALOG
		        || decorationStyle == JRootPane.FILE_CHOOSER_DIALOG || decorationStyle == JRootPane.QUESTION_DIALOG
		        || decorationStyle == JRootPane.WARNING_DIALOG)
		{
			createActions();
			createButtons();
			add(this.closeButton);
		}
	}

	/**
	 * Determines the Colors to draw with.
	 */
	private void determineColors()
	{
		switch(getWindowDecorationStyle())
		{
			case JRootPane.FRAME:
				this.activeBackground = UIManager.getColor("activeCaption");
				this.activeForeground = UIManager.getColor("activeCaptionText");
				this.activeShadow = UIManager.getColor("activeCaptionBorder");
				break;
			case JRootPane.ERROR_DIALOG:
				this.activeBackground = UIManager.getColor("OptionPane.errorDialog.titlePane.background");
				this.activeForeground = UIManager.getColor("OptionPane.errorDialog.titlePane.foreground");
				this.activeShadow = UIManager.getColor("OptionPane.errorDialog.titlePane.shadow");
				break;
			case JRootPane.QUESTION_DIALOG:
			case JRootPane.COLOR_CHOOSER_DIALOG:
			case JRootPane.FILE_CHOOSER_DIALOG:
				this.activeBackground = UIManager.getColor("OptionPane.questionDialog.titlePane.background");
				this.activeForeground = UIManager.getColor("OptionPane.questionDialog.titlePane.foreground");
				this.activeShadow = UIManager.getColor("OptionPane.questionDialog.titlePane.shadow");
				break;
			case JRootPane.WARNING_DIALOG:
				this.activeBackground = UIManager.getColor("OptionPane.warningDialog.titlePane.background");
				this.activeForeground = UIManager.getColor("OptionPane.warningDialog.titlePane.foreground");
				this.activeShadow = UIManager.getColor("OptionPane.warningDialog.titlePane.shadow");
				break;
			case JRootPane.PLAIN_DIALOG:
			case JRootPane.INFORMATION_DIALOG:
			default:
				this.activeBackground = UIManager.getColor("activeCaption");
				this.activeForeground = UIManager.getColor("activeCaptionText");
				this.activeShadow = UIManager.getColor("activeCaptionBorder");
				break;
		}
		this.activeBumps.setBumpColors(this.activeBumpsHighlight, this.activeBumpsShadow, this.activeBackground);
	}

	/**
	 * Installs the fonts and necessary properties on the FamilyTitlePane.
	 */
	private void installDefaults()
	{
		setFont(UIManager.getFont("InternalFrame.titleFont", getLocale()));
	}

	/**
	 * Uninstalls any previously installed UI values.
	 */
	@SuppressWarnings("unused")
	private void uninstallDefaults()
	{}

	/**
	 * Returns the <code>JMenuBar</code> displaying the appropriate system menu
	 * items.
	 */
	@SuppressWarnings("synthetic-access")
	protected JMenuBar createMenuBar()
	{
		this.menuBar = new SystemMenuBar();
		this.menuBar.setFocusable(false);
		this.menuBar.setBorderPainted(true);
		this.menuBar.add(createMenu());
		return this.menuBar;
	}

	/**
	 * Closes the Window.
	 */
	@SuppressWarnings("hiding")
	private void close()
	{
		Window window = getWindow();

		if (window != null)
			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Iconifies the Frame.
	 */
	private void iconify()
	{
		Frame frame = getFrame();
		if (frame != null)
			frame.setExtendedState(this.state | Frame.ICONIFIED);
	}

	/**
	 * Maximizes the Frame.
	 */
	private void maximize()
	{
		Frame frame = getFrame();
		if (frame != null)
			frame.setExtendedState(this.state | Frame.MAXIMIZED_BOTH);
	}

	/**
	 * Restores the Frame size.
	 */
	private void restore()
	{
		Frame frame = getFrame();

		if (frame == null) { return; }

		if ((this.state & Frame.ICONIFIED) != 0)
			frame.setExtendedState(this.state & ~Frame.ICONIFIED);
		else
			frame.setExtendedState(this.state & ~Frame.MAXIMIZED_BOTH);
	}

	/**
	 * Create the <code>Action</code>s that get associated with the buttons and
	 * menu items.
	 */
	private void createActions()
	{
		this.closeAction = new CloseAction();
		if (getWindowDecorationStyle() == JRootPane.FRAME)
		{
			this.iconifyAction = new IconifyAction();
			this.restoreAction = new RestoreAction();
			this.maximizeAction = new MaximizeAction();
		}
	}

	/**
	 * Returns the <code>JMenu</code> displaying the appropriate menu items for
	 * manipulating the Frame.
	 */
	private JMenu createMenu()
	{
		JMenu menu = new JMenu("");
		if (getWindowDecorationStyle() == JRootPane.FRAME)
			addMenuItems(menu);
		return menu;
	}

	/**
	 * Adds the necessary <code>JMenuItem</code>s to the passed in menu.
	 */
	private void addMenuItems(JMenu menu)
	{
		@SuppressWarnings("unused")
		Locale locale = getRootPane().getLocale();
		JMenuItem mi = menu.add(this.restoreAction);
		int mnemonic = FamilyUtils.getInt("FamilyTitlePane.restoreMnemonic", -1);

		if (mnemonic != -1)
			mi.setMnemonic(mnemonic);

		mi = menu.add(this.iconifyAction);
		mnemonic = FamilyUtils.getInt("FamilyTitlePane.iconifyMnemonic", -1);
		if (mnemonic != -1)
			mi.setMnemonic(mnemonic);

		if (Toolkit.getDefaultToolkit().isFrameStateSupported(Frame.MAXIMIZED_BOTH))
		{
			mi = menu.add(this.maximizeAction);
			mnemonic = FamilyUtils.getInt("FamilyTitlePane.maximizeMnemonic", -1);
			if (mnemonic != -1)
				mi.setMnemonic(mnemonic);
		}

		menu.add(new JSeparator());

		mi = menu.add(this.closeAction);
		mnemonic = FamilyUtils.getInt("FamilyTitlePane.closeMnemonic", -1);
		if (mnemonic != -1)
			mi.setMnemonic(mnemonic);
	}

	/**
	 * Returns a <code>JButton</code> appropriate for placement on the
	 * TitlePane.
	 */
	private JButton createTitleButton()
	{
		JButton button = new JButton();

		button.setFocusPainted(false);
		button.setFocusable(false);
		button.setOpaque(true);
		return button;
	}

	/**
	 * Creates the Buttons that will be placed on the TitlePane.
	 */
	private void createButtons()
	{
		this.closeButton = createTitleButton();
		this.closeButton.setAction(this.closeAction);
		this.closeButton.setText(null);
		this.closeButton.putClientProperty("paintActive", Boolean.TRUE);
		this.closeButton.setBorder(handyEmptyBorder);
		this.closeButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY, "Close");
		this.closeButton.setIcon(UIManager.getIcon("InternalFrame.closeIcon"));

		if (getWindowDecorationStyle() == JRootPane.FRAME)
		{
			this.maximizeIcon = UIManager.getIcon("InternalFrame.maximizeIcon");
			this.minimizeIcon = UIManager.getIcon("InternalFrame.minimizeIcon");

			this.iconifyButton = createTitleButton();
			this.iconifyButton.setAction(this.iconifyAction);
			this.iconifyButton.setText(null);
			this.iconifyButton.putClientProperty("paintActive", Boolean.TRUE);
			this.iconifyButton.setBorder(handyEmptyBorder);
			this.iconifyButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY, "Iconify");
			this.iconifyButton.setIcon(UIManager.getIcon("InternalFrame.iconifyIcon"));

			this.toggleButton = createTitleButton();
			this.toggleButton.setAction(this.restoreAction);
			this.toggleButton.putClientProperty("paintActive", Boolean.TRUE);
			this.toggleButton.setBorder(handyEmptyBorder);
			this.toggleButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY, "Maximize");
			this.toggleButton.setIcon(this.maximizeIcon);
		}
	}

	/**
	 * Returns the <code>LayoutManager</code> that should be installed on the
	 * <code>FamilyTitlePane</code>.
	 */
	@SuppressWarnings("synthetic-access")
	private LayoutManager createLayout()
	{
		return new TitlePaneLayout();
	}

	/**
	 * Updates state dependant upon the Window's active state.
	 */
	private void setActive(boolean isActive)
	{
		Boolean activeB = isActive ? Boolean.TRUE : Boolean.FALSE;

		this.closeButton.putClientProperty("paintActive", activeB);
		if (getWindowDecorationStyle() == JRootPane.FRAME)
		{
			this.iconifyButton.putClientProperty("paintActive", activeB);
			this.toggleButton.putClientProperty("paintActive", activeB);
		}
		// Repaint the whole thing as the Borders that are used have
		// different colors for active vs inactive
		getRootPane().repaint();
	}

	/**
	 * Sets the state of the Window.
	 */
	private void setState(int state)
	{
		setState(state, false);
	}

	/**
	 * Sets the state of the window. If <code>updateRegardless</code> is true
	 * and the state has not changed, this will update anyway.
	 */
	private void setState(int state, boolean updateRegardless)
	{
		Window w = getWindow();

		if (w != null && getWindowDecorationStyle() == JRootPane.FRAME)
		{
			if (this.state == state && !updateRegardless) { return; }
			Frame frame = getFrame();

			if (frame != null)
			{
				@SuppressWarnings("hiding")
				JRootPane rootPane = getRootPane();

				if (((state & Frame.MAXIMIZED_BOTH) != 0)&& (rootPane.getBorder() == null
						|| (rootPane.getBorder() instanceof UIResource)) && frame.isShowing())
					rootPane.setBorder(null);
				else if ((state & Frame.MAXIMIZED_BOTH) == 0)
					// This is a croak, if state becomes bound, this can
					// be nuked.
					this.rootPaneUI.installBorder(rootPane);
				if (frame.isResizable())
				{
					if ((state & Frame.MAXIMIZED_BOTH) != 0)
					{
						updateToggleButton(this.restoreAction, this.minimizeIcon);
						this.maximizeAction.setEnabled(false);
						this.restoreAction.setEnabled(true);
					}
					else
					{
						updateToggleButton(this.maximizeAction, this.maximizeIcon);
						this.maximizeAction.setEnabled(true);
						this.restoreAction.setEnabled(false);
					}
					if (this.toggleButton.getParent() == null || this.iconifyButton.getParent() == null)
					{
						add(this.toggleButton);
						add(this.iconifyButton);
						revalidate();
						repaint();
					}
					this.toggleButton.setText(null);
				}
				else
				{
					this.maximizeAction.setEnabled(false);
					this.restoreAction.setEnabled(false);
					if (this.toggleButton.getParent() != null)
					{
						remove(this.toggleButton);
						revalidate();
						repaint();
					}
				}
			}
			else
			{
				// Not contained in a Frame
				this.maximizeAction.setEnabled(false);
				this.restoreAction.setEnabled(false);
				this.iconifyAction.setEnabled(false);
				remove(this.toggleButton);
				remove(this.iconifyButton);
				revalidate();
				repaint();
			}
			this.closeAction.setEnabled(true);
			this.state = state;
		}
	}

	/**
	 * Updates the toggle button to contain the Icon <code>icon</code>, and
	 * Action <code>action</code>.
	 */
	private void updateToggleButton(Action action, Icon icon)
	{
		this.toggleButton.setAction(action);
		this.toggleButton.setIcon(icon);
		this.toggleButton.setText(null);
	}

	/**
	 * Returns the Frame rendering in. This will return null if the
	 * <code>JRootPane</code> is not contained in a <code>Frame</code>.
	 */
	private Frame getFrame()
	{
		@SuppressWarnings("hiding")
		Window window = getWindow();

		if (window instanceof Frame) { return (Frame) window; }
		return null;
	}

	/**
	 * Returns the <code>Window</code> the <code>JRootPane</code> is contained
	 * in. This will return null if there is no parent ancestor of the
	 * <code>JRootPane</code>.
	 */
	private Window getWindow()
	{
		return this.window;
	}

	/**
	 * Returns the String to display as the title.
	 */
	private String getTitle()
	{
		Window w = getWindow();

		if (w instanceof Frame)
			return ((Frame) w).getTitle();
		else if (w instanceof Dialog)
			return ((Dialog) w).getTitle();
		return null;
	}

	/**
	 * Renders the TitlePane.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		// As state isn't bound, we need a convenience place to check
		// if it has changed. Changing the state typically changes the
		if (getFrame() != null)
			setState(getFrame().getExtendedState());
		@SuppressWarnings("hiding")
		JRootPane rootPane = getRootPane();
		@SuppressWarnings("hiding")
		Window window = getWindow();
		boolean leftToRight = (window == null) ? rootPane.getComponentOrientation().isLeftToRight()
		        : window.getComponentOrientation().isLeftToRight();
		boolean isSelected = (window == null) ? true : window.isActive();
		int width = getWidth();
		int height = getHeight();

		Color background;
		Color foreground;
		Color darkShadow;

		FamilyBumps bumps;

		if (isSelected)
		{
			background = this.activeBackground;
			foreground = this.activeForeground;
			darkShadow = this.activeShadow;
			bumps = this.activeBumps;
		}
		else
		{
			background = this.inactiveBackground;
			foreground = this.inactiveForeground;
			darkShadow = this.inactiveShadow;
			bumps = this.inactiveBumps;
		}

		g.setColor(background);
		g.fillRect(0, 0, width, height);

		g.setColor(darkShadow);
		g.drawLine(0, height - 1, width, height - 1);
		g.drawLine(0, 0, 0, 0);
		g.drawLine(width - 1, 0, width - 1, 0);

		int xOffset = leftToRight ? 5 : width - 5;

		if (getWindowDecorationStyle() == JRootPane.FRAME)
			xOffset += leftToRight ? IMAGE_WIDTH + 5 : -IMAGE_WIDTH - 5;

		String theTitle = getTitle();
		if (theTitle != null)
		{
			FontMetrics fm = SwingUtilities2.getFontMetrics(rootPane, g);

			g.setColor(foreground);

			int yOffset = ((height - fm.getHeight()) / 2) + fm.getAscent();

			Rectangle rect = new Rectangle(0, 0, 0, 0);
			if (this.iconifyButton != null && this.iconifyButton.getParent() != null)
				rect = this.iconifyButton.getBounds();
			int titleW;

			if (leftToRight)
			{
				if (rect.x == 0)
					rect.x = window.getWidth() - window.getInsets().right - 2;
				titleW = rect.x - xOffset - 4;
				theTitle = SwingUtilities2.clipStringIfNecessary(rootPane, fm, theTitle, titleW);
			}
			else
			{
				titleW = xOffset - rect.x - rect.width - 4;
				theTitle = SwingUtilities2.clipStringIfNecessary(rootPane, fm, theTitle, titleW);
				xOffset -= SwingUtilities2.stringWidth(rootPane, fm, theTitle);
			}
			int titleLength = SwingUtilities2.stringWidth(rootPane, fm, theTitle);
			SwingUtilities2.drawString(rootPane, g, theTitle, xOffset, yOffset);
			xOffset += leftToRight ? titleLength + 5 : -5;
		}

		int bumpXOffset;
		int bumpLength;
		if (leftToRight)
		{
			bumpLength = width - this.buttonsWidth - xOffset - 5;
			bumpXOffset = xOffset;
		}
		else
		{
			bumpLength = xOffset - this.buttonsWidth - 5;
			bumpXOffset = this.buttonsWidth + 5;
		}
		int bumpYOffset = 3;
		int bumpHeight = getHeight() - (2 * bumpYOffset);
		bumps.setBumpArea(bumpLength, bumpHeight);
		bumps.paintIcon(this, g, bumpXOffset, bumpYOffset);
	}

	/**
	 * Actions used to <code>close</code> the <code>Window</code>.
	 */
	private class CloseAction extends AbstractAction
	{
		public CloseAction()
		{
			super(UIManager.getString("FamilyTitlePane.closeTitle", getLocale()));
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(@SuppressWarnings("unused") ActionEvent e)
		{
			close();
		}
	}

	/**
	 * Actions used to <code>iconfiy</code> the <code>Frame</code>.
	 */
	private class IconifyAction extends AbstractAction
	{
		public IconifyAction()
		{
			super(UIManager.getString("FamilyTitlePane.iconifyTitle", getLocale()));
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(@SuppressWarnings("unused") ActionEvent e)
		{
			iconify();
		}
	}

	/**
	 * Actions used to <code>restore</code> the <code>Frame</code>.
	 */
	private class RestoreAction extends AbstractAction
	{
		public RestoreAction()
		{
			super(UIManager.getString("FamilyTitlePane.restoreTitle", getLocale()));
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(@SuppressWarnings("unused") ActionEvent e)
		{
			restore();
		}
	}

	/**
	 * Actions used to <code>restore</code> the <code>Frame</code>.
	 */
	private class MaximizeAction extends AbstractAction
	{
		public MaximizeAction()
		{
			super(UIManager.getString("FamilyTitlePane.maximizeTitle", getLocale()));
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(@SuppressWarnings("unused") ActionEvent e)
		{
			maximize();
		}
	}

	/**
	 * Class responsible for drawing the system menu. Looks up the image to draw
	 * from the Frame associated with the <code>JRootPane</code>.
	 */
	private class SystemMenuBar extends JMenuBar
	{
		@SuppressWarnings("synthetic-access")
		@Override
		public void paint(Graphics g)
		{
			if (isOpaque())
			{
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
			}

			if (FamilyTitlePane.this.systemIcon != null)
				g.drawImage(FamilyTitlePane.this.systemIcon, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
			else
			{
				Icon icon = UIManager.getIcon("InternalFrame.icon");

				if (icon != null)
					icon.paintIcon(this, g, 0, 0);
			}
		}

		@Override
		public Dimension getMinimumSize()
		{
			return getPreferredSize();
		}

		@Override
		public Dimension getPreferredSize()
		{
			Dimension size = super.getPreferredSize();

			return new Dimension(Math.max(IMAGE_WIDTH, size.width), Math.max(size.height, IMAGE_HEIGHT));
		}
	}

	private class TitlePaneLayout implements LayoutManager
	{
		@Override
		public void addLayoutComponent(@SuppressWarnings("unused") String name, @SuppressWarnings("unused") Component c)
		{}

		@Override
		public void removeLayoutComponent(@SuppressWarnings("unused") Component c)
		{}

		@Override
		public Dimension preferredLayoutSize(@SuppressWarnings("unused") Container c)
		{
			int height = computeHeight();
			return new Dimension(height, height);
		}

		@Override
		public Dimension minimumLayoutSize(Container c)
		{
			return preferredLayoutSize(c);
		}

		@SuppressWarnings("synthetic-access")
		private int computeHeight()
		{
			FontMetrics fm = FamilyTitlePane.this.rootPane.getFontMetrics(getFont());
			int fontHeight = fm.getHeight();
			fontHeight += 7;
			int iconHeight = 0;
			if (getWindowDecorationStyle() == JRootPane.FRAME)
				iconHeight = IMAGE_HEIGHT;

			int finalHeight = Math.max(fontHeight, iconHeight);
			return finalHeight;
		}

		@SuppressWarnings({ "synthetic-access", "unqualified-field-access" })
		@Override
		public void layoutContainer(@SuppressWarnings("unused") Container c)
		{
			boolean leftToRight = (FamilyTitlePane.this.window == null)
			        ? getRootPane().getComponentOrientation().isLeftToRight()
			        : window.getComponentOrientation().isLeftToRight();

			int w = getWidth();
			int x;
			int y = 3;
			int spacing;
			int buttonHeight;
			int buttonWidth;

			if (closeButton != null && FamilyTitlePane.this.closeButton.getIcon() != null)
			{
				buttonHeight = closeButton.getIcon().getIconHeight();
				buttonWidth = closeButton.getIcon().getIconWidth();
			}
			else
			{
				buttonHeight = IMAGE_HEIGHT;
				buttonWidth = IMAGE_WIDTH;
			}

			// assumes all buttons have the same dimensions
			// these dimensions include the borders

			x = leftToRight ? w : 0;

			spacing = 5;
			x = leftToRight ? spacing : w - buttonWidth - spacing;
			if (menuBar != null)
				menuBar.setBounds(x, y, buttonWidth, buttonHeight);

			x = leftToRight ? w : 0;
			spacing = 4;
			x += leftToRight ? -spacing - buttonWidth : spacing;
			if (closeButton != null)
				closeButton.setBounds(x, y, buttonWidth, buttonHeight);

			if (!leftToRight) x += buttonWidth;

			if (getWindowDecorationStyle() == JRootPane.FRAME)
			{
				if (Toolkit.getDefaultToolkit().isFrameStateSupported(Frame.MAXIMIZED_BOTH))
					if (toggleButton.getParent() != null)
					{
						spacing = 10;
						x += leftToRight ? -spacing - buttonWidth : spacing;
						toggleButton.setBounds(x, y, buttonWidth, buttonHeight);
						if (!leftToRight)
							x += buttonWidth;
					}

				if (iconifyButton != null && iconifyButton.getParent() != null)
				{
					spacing = 2;
					x += leftToRight ? -spacing - buttonWidth : spacing;
					iconifyButton.setBounds(x, y, buttonWidth, buttonHeight);
					if (!leftToRight)
						x += buttonWidth;
				}
			}
			buttonsWidth = leftToRight ? w - x : x;
		}
	}

	/**
	 * PropertyChangeListener installed on the Window. Updates the necessary
	 * state as the state of the Window changes.
	 */
	private class PropertyChangeHandler implements PropertyChangeListener
	{
		@SuppressWarnings("synthetic-access")
		@Override
		public void propertyChange(PropertyChangeEvent pce)
		{
			String name = pce.getPropertyName();

			// Frame.state isn't currently bound.
			if ("resizable".equals(name) || "state".equals(name))
			{
				Frame frame = getFrame();

				if (frame != null)
					setState(frame.getExtendedState(), true);
				if ("resizable".equals(name))
					getRootPane().repaint();
			}
			else if ("title".equals(name))
				repaint();
			else if ("componentOrientation" == name)
			{
				revalidate();
				repaint();
			}
			else if ("iconImage" == name)
			{
				updateSystemIcon();
				revalidate();
				repaint();
			}
		}
	}

	/**
	 * Update the image used for the system icon
	 */
	private void updateSystemIcon()
	{
		@SuppressWarnings("hiding")
		Window window = getWindow();
		if (window == null)
		{
			this.systemIcon = null;
			return;
		}
		java.util.List<Image> icons = window.getIconImages();
		assert icons != null;

		if (icons.size() == 0)
			this.systemIcon = null;
		else if (icons.size() == 1)
			this.systemIcon = icons.get(0);
		else
			this.systemIcon = SunToolkit.getScaledIconImage(icons, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	/**
	 * WindowListener installed on the Window, updates the state as necessary.
	 */
	private class WindowHandler extends WindowAdapter
	{
		@SuppressWarnings("synthetic-access")
		@Override
		public void windowActivated(@SuppressWarnings("unused") WindowEvent ev)
		{
			setActive(true);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void windowDeactivated(@SuppressWarnings("unused") WindowEvent ev)
		{
			setActive(false);
		}
	}
}
