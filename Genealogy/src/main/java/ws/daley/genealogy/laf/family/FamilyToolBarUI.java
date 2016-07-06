/*
 * Copyright (c) 1998, 2013, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicToolBarUI;

/**
 * A Family Look and Feel implementation of ToolBarUI. This implementation is a
 * "combined" view/controller.
 * <p>
 *
 * @author Jeff Shapiro
 */
public class FamilyToolBarUI extends BasicToolBarUI
{
	/**
	 * An array of WeakReferences that point to JComponents. This will contain
	 * instances of JToolBars and JMenuBars and is used to find
	 * JToolBars/JMenuBars that border each other.
	 */
	private static List<WeakReference<JComponent>> components = new ArrayList<WeakReference<JComponent>>();

	/**
	 * This protected field is implementation specific. Do not access directly
	 * or override. Use the create method instead.
	 *
	 * @see #createContainerListener
	 */
	protected ContainerListener contListener;

	/**
	 * This protected field is implementation specific. Do not access directly
	 * or override. Use the create method instead.
	 *
	 * @see #createRolloverListener
	 */
	protected PropertyChangeListener rolloverListener;

	private static Border nonRolloverBorder;

	/**
	 * Last menubar the toolbar touched. This is only useful for ocean.
	 */
	private JMenuBar lastMenuBar;

	/**
	 * Registers the specified component.
	 */
	synchronized static void register(JComponent c)
	{
		if (c == null)
			// Exception is thrown as convenience for callers that are
			// typed to throw an NPE.
			throw new NullPointerException("JComponent must be non-null");
		components.add(new WeakReference<JComponent>(c));
	}

	/**
	 * Unregisters the specified component.
	 */
	synchronized static void unregister(JComponent c)
	{
		for (int counter = components.size() - 1; counter >= 0; counter--)
		{
			// Search for the component, removing any flushed references
			// along the way.
			JComponent target = components.get(counter).get();

			if (target == c || target == null)
				components.remove(counter);
		}
	}

	/**
	 * Finds a previously registered component of class <code>target</code> that
	 * shares the JRootPane ancestor of <code>from</code>.
	 */
	synchronized static Object findRegisteredComponentOfType(JComponent from,
	        @SuppressWarnings("rawtypes") Class target)
	{
		JRootPane rp = SwingUtilities.getRootPane(from);
		if (rp != null)
			for (int counter = components.size() - 1; counter >= 0; counter--)
			{
				@SuppressWarnings("rawtypes")
				Object component = ((WeakReference) components.get(counter)).get();

				if (component == null)
					// WeakReference has gone away, remove the WeakReference
					components.remove(counter);
				else if (target.isInstance(component) && SwingUtilities.getRootPane((Component) component) == rp)
					return component;
			}
		return null;
	}

	/**
	 * Returns true if the passed in JMenuBar is above a horizontal JToolBar.
	 */
	static boolean doesMenuBarBorderToolBar(JMenuBar c)
	{
		JToolBar tb = (JToolBar) FamilyToolBarUI.findRegisteredComponentOfType(c, JToolBar.class);
		if (tb != null && tb.getOrientation() == SwingConstants.HORIZONTAL)
		{
			JRootPane rp = SwingUtilities.getRootPane(c);
			Point point = new Point(0, 0);
			point = SwingUtilities.convertPoint(c, point, rp);
			int menuX = point.x;
			int menuY = point.y;
			point.x = point.y = 0;
			point = SwingUtilities.convertPoint(tb, point, rp);
			return (point.x == menuX && menuY + c.getHeight() == point.y && c.getWidth() == tb.getWidth());
		}
		return false;
	}

	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		return new FamilyToolBarUI();
	}

	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);
		register(c);
	}

	@Override
	public void uninstallUI(JComponent c)
	{
		super.uninstallUI(c);
		nonRolloverBorder = null;
		unregister(c);
	}

	@Override
	protected void installListeners()
	{
		super.installListeners();

		this.contListener = createContainerListener();
		if (this.contListener != null)
			this.toolBar.addContainerListener(this.contListener);
		this.rolloverListener = createRolloverListener();
		if (this.rolloverListener != null)
			this.toolBar.addPropertyChangeListener(this.rolloverListener);
	}

	@Override
	protected void uninstallListeners()
	{
		super.uninstallListeners();

		if (this.contListener != null)
			this.toolBar.removeContainerListener(this.contListener);
		this.rolloverListener = createRolloverListener();
		if (this.rolloverListener != null)
			this.toolBar.removePropertyChangeListener(this.rolloverListener);
	}

	@Override
	protected Border createRolloverBorder()
	{
		return super.createRolloverBorder();
	}

	@Override
	protected Border createNonRolloverBorder()
	{
		return super.createNonRolloverBorder();
	}

	/**
	 * Creates a non rollover border for Toggle buttons in the toolbar.
	 */
	private Border createNonRolloverToggleBorder()
	{
		return createNonRolloverBorder();
	}

	@Override
	protected void setBorderToNonRollover(Component c)
	{
		if (c instanceof JToggleButton && !(c instanceof JCheckBox))
		{
			// 4735514, 4886944: The method createNonRolloverToggleBorder() is
			// private in BasicToolBarUI so we can't override it. We still need
			// to call super from this method so that it can save away the
			// original border and then we install ours.

			// Before calling super we get a handle to the old border, because
			// super will install a non-UIResource border that we can't
			// distinguish from one provided by an application.
			JToggleButton b = (JToggleButton) c;
			Border border = b.getBorder();
			super.setBorderToNonRollover(c);
			if (border instanceof UIResource)
			{
				if (nonRolloverBorder == null)
					nonRolloverBorder = createNonRolloverToggleBorder();
				b.setBorder(nonRolloverBorder);
			}
		}
		else
			super.setBorderToNonRollover(c);
	}

	/**
	 * Creates a container listener that will be added to the JToolBar. If this
	 * method returns null then it will not be added to the toolbar.
	 *
	 * @return an instance of a <code>ContainerListener</code> or null
	 */
	protected ContainerListener createContainerListener()
	{
		return null;
	}

	/**
	 * Creates a property change listener that will be added to the JToolBar. If
	 * this method returns null then it will not be added to the toolbar.
	 *
	 * @return an instance of a <code>PropertyChangeListener</code> or null
	 */
	protected PropertyChangeListener createRolloverListener()
	{
		return null;
	}

	@Override
	protected MouseInputListener createDockingListener()
	{
		return new FamilyDockingListener(this.toolBar);
	}

	protected void setDragOffset(Point p)
	{
		if (!GraphicsEnvironment.isHeadless())
		{
			if (this.dragWindow == null)
				this.dragWindow = createDragWindow(this.toolBar);
			this.dragWindow.setOffset(p);
		}
	}

	/**
	 * If necessary paints the background of the component, then invokes
	 * <code>paint</code>.
	 *
	 * @param g
	 *            Graphics to paint to
	 * @param c
	 *            JComponent painting on
	 * @throws NullPointerException
	 *             if <code>g</code> or <code>c</code> is null
	 * @see javax.swing.plaf.ComponentUI#update
	 * @see javax.swing.plaf.ComponentUI#paint
	 * @since 1.5
	 */
	@Override
	public void update(Graphics g, JComponent c)
	{
		if (g == null)
			throw new NullPointerException("graphics must be non-null");
		if (c.isOpaque() && (c.getBackground() instanceof UIResource)
		        && ((JToolBar) c).getOrientation() == SwingConstants.HORIZONTAL
		        && UIManager.get("MenuBar.gradient") != null)
		{
			JRootPane rp = SwingUtilities.getRootPane(c);
			JMenuBar mb = (JMenuBar) findRegisteredComponentOfType(c, JMenuBar.class);
			if (mb != null && mb.isOpaque() && (mb.getBackground() instanceof UIResource))
			{
				Point point = new Point(0, 0);
				point = SwingUtilities.convertPoint(c, point, rp);
				int x = point.x;
				int y = point.y;
				point.x = point.y = 0;
				point = SwingUtilities.convertPoint(mb, point, rp);
				if (point.x == x && y == point.y + mb.getHeight() && mb.getWidth() == c.getWidth()
				        && FamilyUtils.drawGradient(c, g, "MenuBar.gradient", 0, -mb.getHeight(), c.getWidth(),
				                c.getHeight() + mb.getHeight(), true))
				{
					setLastMenuBar(mb);
					paint(g, c);
					return;
				}
			}
			if (FamilyUtils.drawGradient(c, g, "MenuBar.gradient", 0, 0, c.getWidth(), c.getHeight(), true))
			{
				setLastMenuBar(null);
				paint(g, c);
				return;
			}
		}
		setLastMenuBar(null);
		super.update(g, c);
	}

	private void setLastMenuBar(JMenuBar lastMenuBar)
	{
		if (FamilyLookAndFeel.usingOcean())
			if (this.lastMenuBar != lastMenuBar)
			{
				// The menubar we previously touched has changed, force it
				// to repaint.
				if (this.lastMenuBar != null)
					this.lastMenuBar.repaint();
				if (lastMenuBar != null)
					lastMenuBar.repaint();
				this.lastMenuBar = lastMenuBar;
			}
	}

	// No longer used. Cannot remove for compatibility reasons
	protected class FamilyContainerListener extends BasicToolBarUI.ToolBarContListener
	{}

	// No longer used. Cannot remove for compatibility reasons
	protected class FamilyRolloverListener extends BasicToolBarUI.PropertyListener
	{}

	protected class FamilyDockingListener extends DockingListener
	{
		private boolean pressedInBumps = false;

		public FamilyDockingListener(JToolBar t)
		{
			super(t);
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			super.mousePressed(e);
			if (!this.toolBar.isEnabled()) { return; }
			this.pressedInBumps = false;
			Rectangle bumpRect = new Rectangle();

			if (this.toolBar.getOrientation() == SwingConstants.HORIZONTAL)
			{
				int x = FamilyUtils.isLeftToRight(this.toolBar) ? 0 : this.toolBar.getSize().width - 14;
				bumpRect.setBounds(x, 0, 14, this.toolBar.getSize().height);
			}
			else
			{ // vertical
				bumpRect.setBounds(0, 0, this.toolBar.getSize().width, 14);
			}
			if (bumpRect.contains(e.getPoint()))
			{
				this.pressedInBumps = true;
				Point dragOffset = e.getPoint();
				if (!FamilyUtils.isLeftToRight(this.toolBar))
					dragOffset.x -= (this.toolBar.getSize().width - this.toolBar.getPreferredSize().width);
				setDragOffset(dragOffset);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e)
		{
			if (this.pressedInBumps)
				super.mouseDragged(e);
		}
	} // end class FamilyDockingListener
}
