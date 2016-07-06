/*
 * Copyright (c) 2000, 2013, Oracle and/or its affiliates. All rights reserved.
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
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRootPaneUI;

/**
 * Provides the family look and feel implementation of <code>RootPaneUI</code>.
 * <p>
 * <code>FamilyRootPaneUI</code> provides support for the
 * <code>windowDecorationStyle</code> property of <code>JRootPane</code>.
 * <code>FamilyRootPaneUI</code> does this by way of installing a custom
 * <code>LayoutManager</code>, a private <code>Component</code> to render the
 * appropriate widgets, and a private <code>Border</code>. The
 * <code>LayoutManager</code> is always installed, regardless of the value of
 * the <code>windowDecorationStyle</code> property, but the <code>Border</code>
 * and <code>Component</code> are only installed/added if the
 * <code>windowDecorationStyle</code> is other than <code>JRootPane.NONE</code>.
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Terry Kellerman
 * @since 1.4
 */
public class FamilyRootPaneUI extends BasicRootPaneUI
{
	/**
	 * Keys to lookup borders in defaults table.
	 */
	private static final String[] borderKeys = new String[] { null, "RootPane.frameBorder",
	        "RootPane.plainDialogBorder", "RootPane.informationDialogBorder", "RootPane.errorDialogBorder",
	        "RootPane.colorChooserDialogBorder", "RootPane.fileChooserDialogBorder", "RootPane.questionDialogBorder",
	        "RootPane.warningDialogBorder" };
	/**
	 * The amount of space (in pixels) that the cursor is changed on.
	 */
	private static final int CORNER_DRAG_WIDTH = 16;

	/**
	 * Region from edges that dragging is active from.
	 */
	private static final int BORDER_DRAG_THICKNESS = 5;

	/**
	 * Window the <code>JRootPane</code> is in.
	 */
	private Window window;

	/**
	 * <code>JComponent</code> providing window decorations. This will be null
	 * if not providing window decorations.
	 */
	private JComponent titlePane;

	/**
	 * <code>MouseInputListener</code> that is added to the parent
	 * <code>Window</code> the <code>JRootPane</code> is contained in.
	 */
	private MouseInputListener mouseInputListener;

	/**
	 * The <code>LayoutManager</code> that is set on the <code>JRootPane</code>.
	 */
	private LayoutManager layoutManager;

	/**
	 * <code>LayoutManager</code> of the <code>JRootPane</code> before we
	 * replaced it.
	 */
	private LayoutManager savedOldLayout;

	/**
	 * <code>JRootPane</code> providing the look and feel for.
	 */
	private JRootPane root;

	/**
	 * <code>Cursor</code> used to track the cursor set by the user. This is
	 * initially <code>Cursor.DEFAULT_CURSOR</code>.
	 */
	private Cursor lastCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	/**
	 * Creates a UI for a <code>JRootPane</code>.
	 *
	 * @param c
	 *            the JRootPane the RootPaneUI will be created for
	 * @return the RootPaneUI implementation for the passed in JRootPane
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new FamilyRootPaneUI();
	}

	/**
	 * Invokes supers implementation of <code>installUI</code> to install the
	 * necessary state onto the passed in <code>JRootPane</code> to render the
	 * family look and feel implementation of <code>RootPaneUI</code>. If the
	 * <code>windowDecorationStyle</code> property of the <code>JRootPane</code>
	 * is other than <code>JRootPane.NONE</code>, this will add a custom
	 * <code>Component</code> to render the widgets to <code>JRootPane</code>,
	 * as well as installing a custom <code>Border</code> and
	 * <code>LayoutManager</code> on the <code>JRootPane</code>.
	 *
	 * @param c
	 *            the JRootPane to install state onto
	 */
	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);
		this.root = (JRootPane) c;
		int style = this.root.getWindowDecorationStyle();
		if (style != JRootPane.NONE)
			installClientDecorations(this.root);
	}

	/**
	 * Invokes supers implementation to uninstall any of its state. This will
	 * also reset the <code>LayoutManager</code> of the <code>JRootPane</code>.
	 * If a <code>Component</code> has been added to the <code>JRootPane</code>
	 * to render the window decoration style, this method will remove it.
	 * Similarly, this will revert the Border and LayoutManager of the
	 * <code>JRootPane</code> to what it was before <code>installUI</code> was
	 * invoked.
	 *
	 * @param c
	 *            the JRootPane to uninstall state from
	 */
	@Override
	public void uninstallUI(JComponent c)
	{
		super.uninstallUI(c);
		uninstallClientDecorations(this.root);

		this.layoutManager = null;
		this.mouseInputListener = null;
		this.root = null;
	}

	/**
	 * Installs the appropriate <code>Border</code> onto the
	 * <code>JRootPane</code>.
	 */
	void installBorder(@SuppressWarnings("hiding") JRootPane root)
	{
		int style = root.getWindowDecorationStyle();

		if (style == JRootPane.NONE)
			LookAndFeel.uninstallBorder(root);
		else
			LookAndFeel.installBorder(root, borderKeys[style]);
	}

	/**
	 * Removes any border that may have been installed.
	 */
	private void uninstallBorder(@SuppressWarnings("hiding") JRootPane root)
	{
		LookAndFeel.uninstallBorder(root);
	}

	/**
	 * Installs the necessary Listeners on the parent <code>Window</code>, if
	 * there is one.
	 * <p>
	 * This takes the parent so that cleanup can be done from
	 * <code>removeNotify</code>, at which point the parent hasn't been reset
	 * yet.
	 *
	 * @param parent
	 *            The parent of the JRootPane
	 */
	private void installWindowListeners(@SuppressWarnings("hiding") JRootPane root, Component parent)
	{
		if (parent instanceof Window)
			this.window = (Window) parent;
		else
			this.window = SwingUtilities.getWindowAncestor(parent);
		if (this.window != null)
		{
			if (this.mouseInputListener == null)
				this.mouseInputListener = createWindowMouseInputListener(root);
			this.window.addMouseListener(this.mouseInputListener);
			this.window.addMouseMotionListener(this.mouseInputListener);
		}
	}

	/**
	 * Uninstalls the necessary Listeners on the <code>Window</code> the
	 * Listeners were last installed on.
	 */
	private void uninstallWindowListeners(@SuppressWarnings({ "unused", "hiding" }) JRootPane root)
	{
		if (this.window != null)
		{
			this.window.removeMouseListener(this.mouseInputListener);
			this.window.removeMouseMotionListener(this.mouseInputListener);
		}
	}

	/**
	 * Installs the appropriate LayoutManager on the <code>JRootPane</code> to
	 * render the window decorations.
	 */
	private void installLayout(@SuppressWarnings("hiding") JRootPane root)
	{
		if (this.layoutManager == null)
			this.layoutManager = createLayoutManager();
		this.savedOldLayout = root.getLayout();
		root.setLayout(this.layoutManager);
	}

	/**
	 * Uninstalls the previously installed <code>LayoutManager</code>.
	 */
	private void uninstallLayout(@SuppressWarnings("hiding") JRootPane root)
	{
		if (this.savedOldLayout != null)
		{
			root.setLayout(this.savedOldLayout);
			this.savedOldLayout = null;
		}
	}

	/**
	 * Installs the necessary state onto the JRootPane to render client
	 * decorations. This is ONLY invoked if the <code>JRootPane</code> has a
	 * decoration style other than <code>JRootPane.NONE</code>.
	 */
	@SuppressWarnings("unqualified-field-access")
	private void installClientDecorations(@SuppressWarnings("hiding") JRootPane root)
	{
		installBorder(root);

		@SuppressWarnings("hiding")
		JComponent titlePane = createTitlePane(root);

		setTitlePane(root, titlePane);
		installWindowListeners(root, root.getParent());
		installLayout(root);
		if (window != null)
		{
			root.revalidate();
			root.repaint();
		}
	}

	/**
	 * Uninstalls any state that <code>installClientDecorations</code> has
	 * installed.
	 * <p>
	 * NOTE: This may be called if you haven't installed client decorations yet
	 * (ie before <code>installClientDecorations</code> has been invoked).
	 */
	private void uninstallClientDecorations(@SuppressWarnings("hiding") JRootPane root)
	{
		uninstallBorder(root);
		uninstallWindowListeners(root);
		setTitlePane(root, null);
		uninstallLayout(root);
		// We have to revalidate/repaint root if the style is JRootPane.NONE
		// only. When we needs to call revalidate/repaint with other styles
		// the installClientDecorations is always called after this method
		// imediatly and it will cause the revalidate/repaint at the proper
		// time.
		int style = root.getWindowDecorationStyle();
		if (style == JRootPane.NONE)
		{
			root.repaint();
			root.revalidate();
		}
		// Reset the cursor, as we may have changed it to a resize cursor
		if (this.window != null)
			this.window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.window = null;
	}

	/**
	 * Returns the <code>JComponent</code> to render the window decoration
	 * style.
	 */
	private JComponent createTitlePane(@SuppressWarnings("hiding") JRootPane root)
	{
		return new FamilyTitlePane(root, this);
	}

	/**
	 * Returns a <code>MouseListener</code> that will be added to the
	 * <code>Window</code> containing the <code>JRootPane</code>.
	 */
	@SuppressWarnings("synthetic-access")
	private MouseInputListener createWindowMouseInputListener(@SuppressWarnings({ "hiding", "unused" }) JRootPane root)
	{
		return new MouseInputHandler();
	}

	/**
	 * Returns a <code>LayoutManager</code> that will be set on the
	 * <code>JRootPane</code>.
	 */
	@SuppressWarnings("synthetic-access")
	private LayoutManager createLayoutManager()
	{
		return new FamilyRootLayout();
	}

	/**
	 * Sets the window title pane -- the JComponent used to provide a plaf a way
	 * to override the native operating system's window title pane with one
	 * whose look and feel are controlled by the plaf. The plaf creates and sets
	 * this value; the default is null, implying a native operating system
	 * window title pane.
	 *
	 * @param content
	 *            the <code>JComponent</code> to use for the window title pane.
	 */
	private void setTitlePane(JRootPane root, JComponent titlePane)
	{
		JLayeredPane layeredPane = root.getLayeredPane();
		JComponent oldTitlePane = getTitlePane();

		if (oldTitlePane != null)
		{
			oldTitlePane.setVisible(false);
			layeredPane.remove(oldTitlePane);
		}
		if (titlePane != null)
		{
			layeredPane.add(titlePane, JLayeredPane.FRAME_CONTENT_LAYER);
			titlePane.setVisible(true);
		}
		this.titlePane = titlePane;
	}

	/**
	 * Returns the <code>JComponent</code> rendering the title pane. If this
	 * returns null, it implies there is no need to render window decorations.
	 *
	 * @return the current window title pane, or null
	 * @see #setTitlePane
	 */
	private JComponent getTitlePane()
	{
		return this.titlePane;
	}

	/**
	 * Returns the <code>JRootPane</code> we're providing the look and feel for.
	 */
	private JRootPane getRootPane()
	{
		return this.root;
	}

	/**
	 * Invoked when a property changes. <code>FamilyRootPaneUI</code> is
	 * primarily interested in events originating from the
	 * <code>JRootPane</code> it has been installed on identifying the property
	 * <code>windowDecorationStyle</code>. If the
	 * <code>windowDecorationStyle</code> has changed to a value other than
	 * <code>JRootPane.NONE</code>, this will add a <code>Component</code> to
	 * the <code>JRootPane</code> to render the window decorations, as well as
	 * installing a <code>Border</code> on the <code>JRootPane</code>. On the
	 * other hand, if the <code>windowDecorationStyle</code> has changed to
	 * <code>JRootPane.NONE</code>, this will remove the <code>Component</code>
	 * that has been added to the <code>JRootPane</code> as well resetting the
	 * Border to what it was before <code>installUI</code> was invoked.
	 *
	 * @param e
	 *            A PropertyChangeEvent object describing the event source and
	 *            the property that has changed.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent e)
	{
		super.propertyChange(e);

		String propertyName = e.getPropertyName();
		if (propertyName == null)
			return;

		if (propertyName.equals("windowDecorationStyle"))
		{
			@SuppressWarnings("hiding")
			JRootPane root = (JRootPane) e.getSource();
			int style = root.getWindowDecorationStyle();

			// This is potentially more than needs to be done,
			// but it rarely happens and makes the install/uninstall process
			// simpler. FamilyTitlePane also assumes it will be recreated if
			// the decoration style changes.
			uninstallClientDecorations(root);
			if (style != JRootPane.NONE)
				installClientDecorations(root);
		}
		else if (propertyName.equals("ancestor"))
		{
			uninstallWindowListeners(this.root);
			if (((JRootPane) e.getSource()).getWindowDecorationStyle() != JRootPane.NONE)
				installWindowListeners(this.root, this.root.getParent());
		}
		return;
	}

	/**
	 * A custom layout manager that is responsible for the layout of
	 * layeredPane, glassPane, myMmenuBar and titlePane, if one has been installed.
	 */
	// NOTE: Ideally this would extends JRootPane.RootLayout, but that
	// would force this to be non-static.
	private static class FamilyRootLayout implements LayoutManager2
	{
		/**
		 * Returns the amount of space the layout would like to have.
		 *
		 * @param the
		 *            Container for which this layout manager is being used
		 * @return a Dimension object containing the layout's preferred size
		 */
		@SuppressWarnings("deprecation")
		@Override
		public Dimension preferredLayoutSize(Container parent)
		{
			Dimension cpd, mbd, tpd;
			int cpWidth = 0;
			int cpHeight = 0;
			int mbWidth = 0;
			int mbHeight = 0;
			int tpWidth = 0;
			@SuppressWarnings("unused")
			int tpHeight = 0;
			Insets i = parent.getInsets();
			JRootPane root = (JRootPane) parent;

			if (root.getContentPane() != null)
				cpd = root.getContentPane().getPreferredSize();
			else
				cpd = root.getSize();
			if (cpd != null)
			{
				cpWidth = cpd.width;
				cpHeight = cpd.height;
			}

			if (root.getMenuBar() != null)
			{
				mbd = root.getMenuBar().getPreferredSize();
				if (mbd != null)
				{
					mbWidth = mbd.width;
					mbHeight = mbd.height;
				}
			}

			if (root.getWindowDecorationStyle() != JRootPane.NONE && (root.getUI() instanceof FamilyRootPaneUI))
			{
				@SuppressWarnings("synthetic-access")
				JComponent titlePane = ((FamilyRootPaneUI) root.getUI()).getTitlePane();
				if (titlePane != null)
				{
					tpd = titlePane.getPreferredSize();
					if (tpd != null)
					{
						tpWidth = tpd.width;
						tpHeight = tpd.height;
					}
				}
			}

			return new Dimension(Math.max(Math.max(cpWidth, mbWidth), tpWidth) + i.left + i.right,
			        cpHeight + mbHeight + tpWidth + i.top + i.bottom);
		}

		/**
		 * Returns the minimum amount of space the layout needs.
		 *
		 * @param the
		 *            Container for which this layout manager is being used
		 * @return a Dimension object containing the layout's minimum size
		 */
		@SuppressWarnings("deprecation")
		@Override
		public Dimension minimumLayoutSize(Container parent)
		{
			Dimension cpd, mbd, tpd;
			int cpWidth = 0;
			int cpHeight = 0;
			int mbWidth = 0;
			int mbHeight = 0;
			int tpWidth = 0;
			@SuppressWarnings("unused")
			int tpHeight = 0;
			Insets i = parent.getInsets();
			JRootPane root = (JRootPane) parent;

			if (root.getContentPane() != null)
				cpd = root.getContentPane().getMinimumSize();
			else
				cpd = root.getSize();
			if (cpd != null)
			{
				cpWidth = cpd.width;
				cpHeight = cpd.height;
			}

			if (root.getMenuBar() != null)
			{
				mbd = root.getMenuBar().getMinimumSize();
				if (mbd != null)
				{
					mbWidth = mbd.width;
					mbHeight = mbd.height;
				}
			}
			if (root.getWindowDecorationStyle() != JRootPane.NONE && (root.getUI() instanceof FamilyRootPaneUI))
			{
				@SuppressWarnings("synthetic-access")
				JComponent titlePane = ((FamilyRootPaneUI) root.getUI()).getTitlePane();
				if (titlePane != null)
				{
					tpd = titlePane.getMinimumSize();
					if (tpd != null)
					{
						tpWidth = tpd.width;
						tpHeight = tpd.height;
					}
				}
			}

			return new Dimension(Math.max(Math.max(cpWidth, mbWidth), tpWidth) + i.left + i.right,
			        cpHeight + mbHeight + tpWidth + i.top + i.bottom);
		}

		/**
		 * Returns the maximum amount of space the layout can use.
		 *
		 * @param the
		 *            Container for which this layout manager is being used
		 * @return a Dimension object containing the layout's maximum size
		 */
		@SuppressWarnings("deprecation")
		@Override
		public Dimension maximumLayoutSize(Container target)
		{
			Dimension cpd, mbd, tpd;
			int cpWidth = Integer.MAX_VALUE;
			int cpHeight = Integer.MAX_VALUE;
			int mbWidth = Integer.MAX_VALUE;
			int mbHeight = Integer.MAX_VALUE;
			int tpWidth = Integer.MAX_VALUE;
			int tpHeight = Integer.MAX_VALUE;
			Insets i = target.getInsets();
			JRootPane root = (JRootPane) target;

			if (root.getContentPane() != null)
			{
				cpd = root.getContentPane().getMaximumSize();
				if (cpd != null)
				{
					cpWidth = cpd.width;
					cpHeight = cpd.height;
				}
			}

			if (root.getMenuBar() != null)
			{
				mbd = root.getMenuBar().getMaximumSize();
				if (mbd != null)
				{
					mbWidth = mbd.width;
					mbHeight = mbd.height;
				}
			}

			if (root.getWindowDecorationStyle() != JRootPane.NONE && (root.getUI() instanceof FamilyRootPaneUI))
			{
				@SuppressWarnings("synthetic-access")
				JComponent titlePane = ((FamilyRootPaneUI) root.getUI()).getTitlePane();
				if (titlePane != null)
				{
					tpd = titlePane.getMaximumSize();
					if (tpd != null)
					{
						tpWidth = tpd.width;
						tpHeight = tpd.height;
					}
				}
			}

			int maxHeight = Math.max(Math.max(cpHeight, mbHeight), tpHeight);
			// Only overflows if 3 real non-MAX_VALUE heights, sum to >
			// MAX_VALUE
			// Only will happen if sums to more than 2 billion units. Not
			// likely.
			if (maxHeight != Integer.MAX_VALUE)
				maxHeight = cpHeight + mbHeight + tpHeight + i.top + i.bottom;

			int maxWidth = Math.max(Math.max(cpWidth, mbWidth), tpWidth);
			// Similar overflow comment as above
			if (maxWidth != Integer.MAX_VALUE)
				maxWidth += i.left + i.right;

			return new Dimension(maxWidth, maxHeight);
		}

		/**
		 * Instructs the layout manager to perform the layout for the specified
		 * container.
		 *
		 * @param the
		 *            Container for which this layout manager is being used
		 */
		@SuppressWarnings("deprecation")
		@Override
		public void layoutContainer(Container parent)
		{
			JRootPane root = (JRootPane) parent;
			Rectangle b = root.getBounds();
			Insets i = root.getInsets();
			int nextY = 0;
			int w = b.width - i.right - i.left;
			int h = b.height - i.top - i.bottom;

			if (root.getLayeredPane() != null)
				root.getLayeredPane().setBounds(i.left, i.top, w, h);
			if (root.getGlassPane() != null)
				root.getGlassPane().setBounds(i.left, i.top, w, h);
			// Note: This is laying out the children in the layeredPane,
			// technically, these are not our children.
			if (root.getWindowDecorationStyle() != JRootPane.NONE && (root.getUI() instanceof FamilyRootPaneUI))
			{
				@SuppressWarnings("synthetic-access")
				JComponent titlePane = ((FamilyRootPaneUI) root.getUI()).getTitlePane();
				if (titlePane != null)
				{
					Dimension tpd = titlePane.getPreferredSize();
					if (tpd != null)
					{
						int tpHeight = tpd.height;
						titlePane.setBounds(0, 0, w, tpHeight);
						nextY += tpHeight;
					}
				}
			}
			if (root.getMenuBar() != null)
			{
				Dimension mbd = root.getMenuBar().getPreferredSize();
				root.getMenuBar().setBounds(0, nextY, w, mbd.height);
				nextY += mbd.height;
			}
			if (root.getContentPane() != null)
			{
				@SuppressWarnings("unused")
				Dimension cpd = root.getContentPane().getPreferredSize();
				root.getContentPane().setBounds(0, nextY, w, h < nextY ? 0 : h - nextY);
			}
		}

		@Override
		public void addLayoutComponent(@SuppressWarnings("unused") String name,
		        @SuppressWarnings("unused") Component comp)
		{}

		@Override
		public void removeLayoutComponent(@SuppressWarnings("unused") Component comp)
		{}

		@Override
		public void addLayoutComponent(@SuppressWarnings("unused") Component comp,
		        @SuppressWarnings("unused") Object constraints)
		{}

		@Override
		public float getLayoutAlignmentX(@SuppressWarnings("unused") Container target)
		{
			return 0.0f;
		}

		@Override
		public float getLayoutAlignmentY(@SuppressWarnings("unused") Container target)
		{
			return 0.0f;
		}

		@Override
		public void invalidateLayout(@SuppressWarnings("unused") Container target)
		{}
	}

	/**
	 * Maps from positions to cursor level. Refer to calculateCorner and
	 * calculatePosition for details of this.
	 */
	private static final int[] cursorMapping = new int[] { Cursor.NW_RESIZE_CURSOR, Cursor.NW_RESIZE_CURSOR,
	        Cursor.N_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR, Cursor.NW_RESIZE_CURSOR, 0, 0, 0,
	        Cursor.NE_RESIZE_CURSOR, Cursor.W_RESIZE_CURSOR, 0, 0, 0, Cursor.E_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR,
	        0, 0, 0, Cursor.SE_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR,
	        Cursor.SE_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR };

	/**
	 * MouseInputHandler is responsible for handling resize/moving of the
	 * Window. It sets the cursor directly on the Window when then mouse moves
	 * over a hot spot.
	 */
	private class MouseInputHandler implements MouseInputListener
	{
		/**
		 * Set to true if the drag operation is moving the window.
		 */
		private boolean isMovingWindow;

		/**
		 * Used to determine the corner the resize is occurring from.
		 */
		private int dragCursor;

		/**
		 * X location the mouse went down on for a drag operation.
		 */
		private int dragOffsetX;

		/**
		 * Y location the mouse went down on for a drag operation.
		 */
		private int dragOffsetY;

		/**
		 * Width of the window when the drag started.
		 */
		private int dragWidth;

		/**
		 * Height of the window when the drag started.
		 */
		private int dragHeight;

		@SuppressWarnings("synthetic-access")
		@Override
		public void mousePressed(MouseEvent ev)
		{
			JRootPane rootPane = getRootPane();

			if (rootPane.getWindowDecorationStyle() == JRootPane.NONE)
				return;
			Point dragWindowOffset = ev.getPoint();
			Window w = (Window) ev.getSource();
			if (w != null)
				w.toFront();
			Point convertedDragWindowOffset = SwingUtilities.convertPoint(w, dragWindowOffset, getTitlePane());

			Frame f = null;
			Dialog d = null;

			if (w instanceof Frame)
				f = (Frame) w;
			else if (w instanceof Dialog)
				d = (Dialog) w;

			int frameState = (f != null) ? f.getExtendedState() : 0;

			if (getTitlePane() != null && getTitlePane().contains(convertedDragWindowOffset))
			{
				if ((f != null && ((frameState & Frame.MAXIMIZED_BOTH) == 0) || (d != null))
				        && dragWindowOffset.y >= BORDER_DRAG_THICKNESS && dragWindowOffset.x >= BORDER_DRAG_THICKNESS
				        && dragWindowOffset.x < w.getWidth() - BORDER_DRAG_THICKNESS)
				{
					this.isMovingWindow = true;
					this.dragOffsetX = dragWindowOffset.x;
					this.dragOffsetY = dragWindowOffset.y;
				}
			}
			else if (f != null && f.isResizable() && ((frameState & Frame.MAXIMIZED_BOTH) == 0)
			        || (d != null && d.isResizable()))
			{
				this.dragOffsetX = dragWindowOffset.x;
				this.dragOffsetY = dragWindowOffset.y;
				this.dragWidth = w.getWidth();
				this.dragHeight = w.getHeight();
				this.dragCursor = getCursor(calculateCorner(w, dragWindowOffset.x, dragWindowOffset.y));
			}
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseReleased(@SuppressWarnings("unused") MouseEvent ev)
		{
			if (this.dragCursor != 0 && FamilyRootPaneUI.this.window != null && !FamilyRootPaneUI.this.window.isValid())
			{
				// Some Window systems validate as you resize, others won't,
				// thus the check for validity before repainting.
				FamilyRootPaneUI.this.window.validate();
				getRootPane().repaint();
			}
			this.isMovingWindow = false;
			this.dragCursor = 0;
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseMoved(MouseEvent ev)
		{
			@SuppressWarnings({ "hiding" })
			JRootPane root = getRootPane();

			if (root.getWindowDecorationStyle() == JRootPane.NONE)
				return;

			Window w = (Window) ev.getSource();

			Frame f = null;
			Dialog d = null;

			if (w instanceof Frame)
				f = (Frame) w;
			else if (w instanceof Dialog)
				d = (Dialog) w;

			// Update the cursor
			int cursor = getCursor(calculateCorner(w, ev.getX(), ev.getY()));

			if (cursor != 0 && ((f != null && (f.isResizable() && (f.getExtendedState() & Frame.MAXIMIZED_BOTH) == 0))
			        || (d != null && d.isResizable())))
				w.setCursor(Cursor.getPredefinedCursor(cursor));
			else
				w.setCursor(FamilyRootPaneUI.this.lastCursor);
		}

		private void adjust(Rectangle bounds, Dimension min, int deltaX, int deltaY, int deltaWidth, int deltaHeight)
		{
			bounds.x += deltaX;
			bounds.y += deltaY;
			bounds.width += deltaWidth;
			bounds.height += deltaHeight;
			if (min != null)
			{
				if (bounds.width < min.width)
				{
					int correction = min.width - bounds.width;
					if (deltaX != 0)
						bounds.x -= correction;
					bounds.width = min.width;
				}
				if (bounds.height < min.height)
				{
					int correction = min.height - bounds.height;
					if (deltaY != 0)
						bounds.y -= correction;
					bounds.height = min.height;
				}
			}
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseDragged(MouseEvent ev)
		{
			Window w = (Window) ev.getSource();
			Point pt = ev.getPoint();

			if (this.isMovingWindow)
			{
				Point eventLocationOnScreen = ev.getLocationOnScreen();
				w.setLocation(eventLocationOnScreen.x - this.dragOffsetX, eventLocationOnScreen.y - this.dragOffsetY);
			}
			else if (this.dragCursor != 0)
			{
				Rectangle r = w.getBounds();
				Rectangle startBounds = new Rectangle(r);
				Dimension min = w.getMinimumSize();

				switch(this.dragCursor)
				{
					case Cursor.E_RESIZE_CURSOR:
						adjust(r, min, 0, 0, pt.x + (this.dragWidth - this.dragOffsetX) - r.width, 0);
						break;
					case Cursor.S_RESIZE_CURSOR:
						adjust(r, min, 0, 0, 0, pt.y + (this.dragHeight - this.dragOffsetY) - r.height);
						break;
					case Cursor.N_RESIZE_CURSOR:
						adjust(r, min, 0, pt.y - this.dragOffsetY, 0, -(pt.y - this.dragOffsetY));
						break;
					case Cursor.W_RESIZE_CURSOR:
						adjust(r, min, pt.x - this.dragOffsetX, 0, -(pt.x - this.dragOffsetX), 0);
						break;
					case Cursor.NE_RESIZE_CURSOR:
						adjust(r, min, 0, pt.y - this.dragOffsetY, pt.x + (this.dragWidth - this.dragOffsetX) - r.width,
						        -(pt.y - this.dragOffsetY));
						break;
					case Cursor.SE_RESIZE_CURSOR:
						adjust(r, min, 0, 0, pt.x + (this.dragWidth - this.dragOffsetX) - r.width,
						        pt.y + (this.dragHeight - this.dragOffsetY) - r.height);
						break;
					case Cursor.NW_RESIZE_CURSOR:
						adjust(r, min, pt.x - this.dragOffsetX, pt.y - this.dragOffsetY, -(pt.x - this.dragOffsetX),
						        -(pt.y - this.dragOffsetY));
						break;
					case Cursor.SW_RESIZE_CURSOR:
						adjust(r, min, pt.x - this.dragOffsetX, 0, -(pt.x - this.dragOffsetX),
						        pt.y + (this.dragHeight - this.dragOffsetY) - r.height);
						break;
					default:
						break;
				}
				if (!r.equals(startBounds))
				{
					w.setBounds(r);
					// Defer repaint/validate on mouseReleased unless dynamic
					// layout is active.
					if (Toolkit.getDefaultToolkit().isDynamicLayoutActive())
					{
						w.validate();
						getRootPane().repaint();
					}
				}
			}
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseEntered(MouseEvent ev)
		{
			Window w = (Window) ev.getSource();
			FamilyRootPaneUI.this.lastCursor = w.getCursor();
			mouseMoved(ev);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseExited(MouseEvent ev)
		{
			Window w = (Window) ev.getSource();
			w.setCursor(FamilyRootPaneUI.this.lastCursor);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseClicked(MouseEvent ev)
		{
			Window w = (Window) ev.getSource();
			Frame f = null;

			if (w instanceof Frame)
				f = (Frame) w;
			else
				return;

			Point convertedPoint = SwingUtilities.convertPoint(w, ev.getPoint(), getTitlePane());

			int state = f.getExtendedState();
			if (getTitlePane() != null && getTitlePane().contains(convertedPoint))
			{
				if ((ev.getClickCount() % 2) == 0 && ((ev.getModifiers() & InputEvent.BUTTON1_MASK) != 0))
				{
					if (f.isResizable())
					{
						if ((state & Frame.MAXIMIZED_BOTH) != 0)
							f.setExtendedState(state & ~Frame.MAXIMIZED_BOTH);
						else
							f.setExtendedState(state | Frame.MAXIMIZED_BOTH);
						return;
					}
				}
			}
		}

		/**
		 * Returns the corner that contains the point <code>x</code>,
		 * <code>y</code>, or -1 if the position doesn't match a corner.
		 */
		private int calculateCorner(Window w, int x, int y)
		{
			Insets insets = w.getInsets();
			int xPosition = calculatePosition(x - insets.left, w.getWidth() - insets.left - insets.right);
			int yPosition = calculatePosition(y - insets.top, w.getHeight() - insets.top - insets.bottom);

			if (xPosition == -1 || yPosition == -1)
				return -1;
			return yPosition * 5 + xPosition;
		}

		/**
		 * Returns the Cursor to render for the specified corner. This returns 0
		 * if the corner doesn't map to a valid Cursor
		 */
		@SuppressWarnings("synthetic-access")
		private int getCursor(int corner)
		{
			if (corner == -1)
				return 0;
			return cursorMapping[corner];
		}

		/**
		 * Returns an integer indicating the position of <code>spot</code> in
		 * <code>width</code>. The return value will be: 0 if <
		 * BORDER_DRAG_THICKNESS 1 if < CORNER_DRAG_WIDTH 2 if >=
		 * CORNER_DRAG_WIDTH && < width - BORDER_DRAG_THICKNESS 3 if >= width -
		 * CORNER_DRAG_WIDTH 4 if >= width - BORDER_DRAG_THICKNESS 5 otherwise
		 */
		private int calculatePosition(int spot, int width)
		{
			if (spot < BORDER_DRAG_THICKNESS)
				return 0;
			if (spot < CORNER_DRAG_WIDTH)
				return 1;
			if (spot >= (width - BORDER_DRAG_THICKNESS))
				return 4;
			if (spot >= (width - CORNER_DRAG_WIDTH))
				return 3;
			return 2;
		}
	}
}
