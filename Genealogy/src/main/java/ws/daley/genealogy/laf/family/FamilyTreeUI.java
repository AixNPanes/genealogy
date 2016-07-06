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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.TreePath;

/**
 * The family look and feel implementation of <code>TreeUI</code>.
 * <p>
 * <code>FamilyTreeUI</code> allows for configuring how to visually render the
 * spacing and delineation between nodes. The following hints are supported:
 *
 * <table summary=
 * "Descriptions of supported hints: Angled, Horizontal, and None">
 * <tr>
 * <th>
 * <p style="text-align:left">
 * Angled
 * </p>
 * </th>
 * <td>A line is drawn connecting the child to the parent. For handling of the
 * root node refer to {@link javax.swing.JTree#setRootVisible} and
 * {@link javax.swing.JTree#setShowsRootHandles}.</td>
 * </tr>
 * <tr>
 * <th>
 * <p style="text-align:left">
 * Horizontal
 * </p>
 * </th>
 * <td>A horizontal line is drawn dividing the children of the root node.</td>
 * </tr>
 * <tr>
 * <th>
 * <p style="text-align:left">
 * None
 * </p>
 * </th>
 * <td>Do not draw any visual indication between nodes.</td>
 * </tr>
 * </table>
 *
 * <p>
 * As it is typically impractical to obtain the <code>TreeUI</code> from the
 * <code>JTree</code> and cast to an instance of <code>FamilyTreeUI</code> you
 * enable this property via the client property <code>JTree.lineStyle</code>.
 * For example, to switch to <code>Horizontal</code> style you would do:
 * <code>tree.putClientProperty("JTree.lineStyle", "Horizontal");</code>
 * <p>
 * The default is <code>Angled</code>.
 *
 * @author Tom Santos
 * @author Steve Wilson (value add stuff)
 */
public class FamilyTreeUI extends BasicTreeUI
{

	private static Color lineColor;

	private static final String LINE_STYLE = "JTree.lineStyle";

	private static final String LEG_LINE_STYLE_STRING = "Angled";
	private static final String HORIZ_STYLE_STRING = "Horizontal";
	private static final String NO_STYLE_STRING = "None";

	private static final int LEG_LINE_STYLE = 2;
	private static final int HORIZ_LINE_STYLE = 1;
	private static final int NO_LINE_STYLE = 0;

	private int lineStyle = LEG_LINE_STYLE;
	private PropertyChangeListener lineStyleListener = new LineListener();

	// Boilerplate
	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent x)
	{
		return new FamilyTreeUI();
	}

	public FamilyTreeUI()
	{
		super();
	}

	@Override
	protected int getHorizontalLegBuffer()
	{
		return 3;
	}

	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);
		lineColor = UIManager.getColor("Tree.line");

		Object lineStyleFlag = c.getClientProperty(LINE_STYLE);
		decodeLineStyle(lineStyleFlag);
		c.addPropertyChangeListener(this.lineStyleListener);

	}

	@Override
	public void uninstallUI(JComponent c)
	{
		c.removePropertyChangeListener(this.lineStyleListener);
		super.uninstallUI(c);
	}

	/**
	 * this function converts between the string passed into the client property
	 * and the internal representation (currently and int)
	 *
	 */
	protected void decodeLineStyle(Object lineStyleFlag)
	{
		if (lineStyleFlag == null || lineStyleFlag.equals(LEG_LINE_STYLE_STRING))
			this.lineStyle = LEG_LINE_STYLE; // default case
		else
			if (lineStyleFlag.equals(NO_STYLE_STRING))
				this.lineStyle = NO_LINE_STYLE;
			else if (lineStyleFlag.equals(HORIZ_STYLE_STRING))
				this.lineStyle = HORIZ_LINE_STYLE;
	}

	protected boolean isLocationInExpandControl(int row, int rowLevel, int mouseX,
	        @SuppressWarnings("unused") int mouseY)
	{
		if (this.tree != null && !isLeaf(row))
		{
			int boxWidth;

			if (getExpandedIcon() != null)
				boxWidth = getExpandedIcon().getIconWidth() + 6;
			else
				boxWidth = 8;

			Insets i = this.tree.getInsets();
			int boxLeftX = (i != null) ? i.left : 0;

			boxLeftX += (((rowLevel + this.depthOffset - 1) * this.totalChildIndent) + getLeftChildIndent()) - boxWidth / 2;

			int boxRightX = boxLeftX + boxWidth;

			return mouseX >= boxLeftX && mouseX <= boxRightX;
		}
		return false;
	}

	@Override
	public void paint(Graphics g, JComponent c)
	{
		super.paint(g, c);

		// Paint the lines
		if (this.lineStyle == HORIZ_LINE_STYLE && !this.largeModel)
			paintHorizontalSeparators(g, c);
	}

	protected void paintHorizontalSeparators(Graphics g, @SuppressWarnings("unused") JComponent c)
	{
		g.setColor(lineColor);

		Rectangle clipBounds = g.getClipBounds();

		int beginRow = getRowForPath(this.tree, getClosestPathForLocation(this.tree, 0, clipBounds.y));
		int endRow = getRowForPath(this.tree,
		        getClosestPathForLocation(this.tree, 0, clipBounds.y + clipBounds.height - 1));

		if (beginRow <= -1 || endRow <= -1)
			return;

		for (int i = beginRow; i <= endRow; ++i)
		{
			TreePath path = getPathForRow(this.tree, i);

			if (path != null && path.getPathCount() == 2)
			{
				Rectangle rowBounds = getPathBounds(this.tree, getPathForRow(this.tree, i));

				// Draw a line at the top
				if (rowBounds != null)
				    g.drawLine(clipBounds.x, rowBounds.y, clipBounds.x + clipBounds.width, rowBounds.y);
			}
		}

	}

	@Override
	protected void paintVerticalPartOfLeg(Graphics g, Rectangle clipBounds, Insets insets, TreePath path)
	{
		if (this.lineStyle == LEG_LINE_STYLE)
			super.paintVerticalPartOfLeg(g, clipBounds, insets, path);
	}

	@Override
	protected void paintHorizontalPartOfLeg(Graphics g, Rectangle clipBounds, Insets insets, Rectangle bounds,
	        TreePath path, int row, boolean isExpanded, boolean hasBeenExpanded, boolean isLeaf)
	{
		if (this.lineStyle == LEG_LINE_STYLE)
			super.paintHorizontalPartOfLeg(g, clipBounds, insets, bounds, path, row, isExpanded, hasBeenExpanded, isLeaf);
	}

	/** This class listens for changes in line style */
	class LineListener implements PropertyChangeListener
	{
		@Override
		public void propertyChange(PropertyChangeEvent e)
		{
			String name = e.getPropertyName();
			if (name.equals(LINE_STYLE))
				decodeLineStyle(e.getNewValue());
		}
	} // end class PaletteListener
}
