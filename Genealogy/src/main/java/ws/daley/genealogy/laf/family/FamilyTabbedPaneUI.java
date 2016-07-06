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
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * The Family subclass of BasicTabbedPaneUI.
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Tom Santos
 */

public class FamilyTabbedPaneUI extends BasicTabbedPaneUI
{

	protected int minTabWidth = 40;
	// Background color for unselected tabs that don't have an explicitly
	// set color.
	private Color unselectedBackground;
	protected Color tabAreaBackground;
	protected Color selectColor;
	protected Color selectHighlight;
	private boolean tabsOpaque = true;

	// Whether or not we're using ocean. This is cached as it is used
	// extensively during painting.
	private boolean ocean;
	// Selected border color for ocean.
	private Color oceanSelectedBorderColor;

	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent x)
	{
		return new FamilyTabbedPaneUI();
	}

	@Override
	protected LayoutManager createLayoutManager()
	{
		if (this.tabPane.getTabLayoutPolicy() == JTabbedPane.SCROLL_TAB_LAYOUT) { return super.createLayoutManager(); }
		return new TabbedPaneLayout();
	}

	@Override
	protected void installDefaults()
	{
		super.installDefaults();

		this.tabAreaBackground = UIManager.getColor("TabbedPane.tabAreaBackground");
		this.selectColor = UIManager.getColor("TabbedPane.selected");
		this.selectHighlight = UIManager.getColor("TabbedPane.selectHighlight");
		this.tabsOpaque = UIManager.getBoolean("TabbedPane.tabsOpaque");
		this.unselectedBackground = UIManager.getColor("TabbedPane.unselectedBackground");
		this.ocean = FamilyLookAndFeel.usingOcean();
		if (this.ocean)
			this.oceanSelectedBorderColor = UIManager.getColor("TabbedPane.borderHightlightColor");
	}

	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
	        boolean isSelected)
	{
		int bottom = y + (h - 1);
		int right = x + (w - 1);

		switch(tabPlacement)
		{
			case LEFT:
				paintLeftTabBorder(tabIndex, g, x, y, w, h, bottom, right, isSelected);
				break;
			case BOTTOM:
				paintBottomTabBorder(tabIndex, g, x, y, w, h, bottom, right, isSelected);
				break;
			case RIGHT:
				paintRightTabBorder(tabIndex, g, x, y, w, h, bottom, right, isSelected);
				break;
			case TOP:
			default:
				paintTopTabBorder(tabIndex, g, x, y, w, h, bottom, right, isSelected);
		}
	}

	protected void paintTopTabBorder(int tabIndex, Graphics g, int x, int y, int w, int h,
	        @SuppressWarnings("unused") int btm, @SuppressWarnings("unused") int rght, boolean isSelected)
	{
		int currentRun = getRunForTab(this.tabPane.getTabCount(), tabIndex);
		int lastIndex = lastTabInRun(this.tabPane.getTabCount(), currentRun);
		@SuppressWarnings("unqualified-field-access")
		int firstIndex = tabRuns[currentRun];
		boolean leftToRight = FamilyUtils.isLeftToRight(this.tabPane);
		int selectedIndex = this.tabPane.getSelectedIndex();
		int bottom = h - 1;
		int right = w - 1;

		//
		// Paint Gap
		//

		if (shouldFillGap(currentRun, tabIndex, x, y))
		{
			g.translate(x, y);

			if (leftToRight)
			{
				g.setColor(getColorForGap(currentRun, x, y + 1));
				g.fillRect(1, 0, 5, 3);
				g.fillRect(1, 3, 2, 2);
			}
			else
			{
				g.setColor(getColorForGap(currentRun, x + w - 1, y + 1));
				g.fillRect(right - 5, 0, 5, 3);
				g.fillRect(right - 2, 3, 2, 2);
			}

			g.translate(-x, -y);
		}

		g.translate(x, y);

		//
		// Paint Border
		//

		if (this.ocean && isSelected)
			g.setColor(this.oceanSelectedBorderColor);
		else
			g.setColor(this.darkShadow);

		if (leftToRight)
		{

			// Paint slant
			g.drawLine(1, 5, 6, 0);

			// Paint top
			g.drawLine(6, 0, right, 0);

			// Paint right
			if (tabIndex == lastIndex)
				// last tab in run
				g.drawLine(right, 1, right, bottom);

			if (this.ocean && tabIndex - 1 == selectedIndex && currentRun == getRunForTab(this.tabPane.getTabCount(), selectedIndex))
				g.setColor(this.oceanSelectedBorderColor);

			// Paint left
			if (tabIndex != this.tabRuns[this.runCount - 1])
			{
				// not the first tab in the last run
				if (this.ocean && isSelected)
				{
					g.drawLine(0, 6, 0, bottom);
					g.setColor(this.darkShadow);
					g.drawLine(0, 0, 0, 5);
				}
				else
					g.drawLine(0, 0, 0, bottom);
			}
			else
				// the first tab in the last run
				g.drawLine(0, 6, 0, bottom);
		}
		else
		{

			// Paint slant
			g.drawLine(right - 1, 5, right - 6, 0);

			// Paint top
			g.drawLine(right - 6, 0, 0, 0);

			// Paint left
			if (tabIndex == lastIndex)
				// last tab in run
				g.drawLine(0, 1, 0, bottom);

			// Paint right
			if (this.ocean && tabIndex - 1 == selectedIndex && currentRun == getRunForTab(this.tabPane.getTabCount(), selectedIndex))
			{
				g.setColor(this.oceanSelectedBorderColor);
				g.drawLine(right, 0, right, bottom);
			}
			else if (this.ocean && isSelected)
			{
				g.drawLine(right, 6, right, bottom);
				if (tabIndex != 0)
				{
					g.setColor(this.darkShadow);
					g.drawLine(right, 0, right, 5);
				}
			}
			else
				if (tabIndex != this.tabRuns[this.runCount - 1])
					// not the first tab in the last run
					g.drawLine(right, 0, right, bottom);
				else
					// the first tab in the last run
					g.drawLine(right, 6, right, bottom);
		}

		//
		// Paint Highlight
		//

		g.setColor(isSelected ? this.selectHighlight : this.highlight);

		if (leftToRight)
		{

			// Paint slant
			g.drawLine(1, 6, 6, 1);

			// Paint top
			g.drawLine(6, 1, (tabIndex == lastIndex) ? right - 1 : right, 1);

			// Paint left
			g.drawLine(1, 6, 1, bottom);

			// paint highlight in the gap on tab behind this one
			// on the left end (where they all line up)
			if (tabIndex == firstIndex && tabIndex != this.tabRuns[this.runCount - 1])
			{
				// first tab in run but not first tab in last run
				if (this.tabPane.getSelectedIndex() == this.tabRuns[currentRun + 1])
					// tab in front of selected tab
					g.setColor(this.selectHighlight);
				else
					// tab in front of normal tab
					g.setColor(this.highlight);
				g.drawLine(1, 0, 1, 4);
			}
		}
		else
		{

			// Paint slant
			g.drawLine(right - 1, 6, right - 6, 1);

			// Paint top
			g.drawLine(right - 6, 1, 1, 1);

			// Paint left
			if (tabIndex == lastIndex)
				// last tab in run
				g.drawLine(1, 1, 1, bottom);
			else
				g.drawLine(0, 1, 0, bottom);
		}

		g.translate(-x, -y);
	}

	protected boolean shouldFillGap(int currentRun, int tabIndex, @SuppressWarnings("unused") int x,
	        @SuppressWarnings("unused") int y)
	{
		boolean result = false;

		if (!this.tabsOpaque)
			return false;

		if (currentRun == this.runCount - 2)
		{ // If it's the second to last row.
			Rectangle lastTabBounds = getTabBounds(this.tabPane, this.tabPane.getTabCount() - 1);
			Rectangle tabBounds = getTabBounds(this.tabPane, tabIndex);
			if (FamilyUtils.isLeftToRight(this.tabPane))
			{
				int lastTabRight = lastTabBounds.x + lastTabBounds.width - 1;

				// is the right edge of the last tab to the right
				// of the left edge of the current tab?
				if (lastTabRight > tabBounds.x + 2)
					return true;
			}
			else
			{
				int lastTabLeft = lastTabBounds.x;
				int currentTabRight = tabBounds.x + tabBounds.width - 1;

				// is the left edge of the last tab to the left
				// of the right edge of the current tab?
				if (lastTabLeft < currentTabRight - 2)
					return true;
			}
		}
		else
			// fill in gap for all other rows except last row
			result = currentRun != this.runCount - 1;

		return result;
	}

	protected Color getColorForGap(int currentRun, int x, @SuppressWarnings("unused") int y)
	{
		final int shadowWidth = 4;
		int selectedIndex = this.tabPane.getSelectedIndex();
		int startIndex = this.tabRuns[currentRun + 1];
		int endIndex = lastTabInRun(this.tabPane.getTabCount(), currentRun + 1);
		@SuppressWarnings("unused")
		int tabOverGap = -1;
		// Check each tab in the row that is 'on top' of this row
		for (int i = startIndex; i <= endIndex; ++i)
		{
			Rectangle tabBounds = getTabBounds(this.tabPane, i);
			int tabLeft = tabBounds.x;
			int tabRight = (tabBounds.x + tabBounds.width) - 1;
			// Check to see if this tab is over the gap
			if (FamilyUtils.isLeftToRight(this.tabPane))
			{
				if (tabLeft <= x && tabRight - shadowWidth > x)
					return selectedIndex == i ? this.selectColor : getUnselectedBackgroundAt(i);
			}
			else
				if (tabLeft + shadowWidth < x && tabRight >= x)
					return selectedIndex == i ? this.selectColor : getUnselectedBackgroundAt(i);
		}

		return this.tabPane.getBackground();
	}

	protected void paintLeftTabBorder(int tabIndex, Graphics g, int x, int y, int w, int h,
	        @SuppressWarnings("unused") int btm, @SuppressWarnings("unused") int rght, boolean isSelected)
	{
		int tabCount = this.tabPane.getTabCount();
		int currentRun = getRunForTab(tabCount, tabIndex);
		int lastIndex = lastTabInRun(tabCount, currentRun);
		int firstIndex = this.tabRuns[currentRun];

		g.translate(x, y);

		int bottom = h - 1;
		int right = w - 1;

		//
		// Paint part of the tab above
		//

		if (tabIndex != firstIndex && this.tabsOpaque)
		{
			g.setColor(this.tabPane.getSelectedIndex() == tabIndex - 1 ? this.selectColor
			        : getUnselectedBackgroundAt(tabIndex - 1));
			g.fillRect(2, 0, 4, 3);
			g.drawLine(2, 3, 2, 3);
		}

		//
		// Paint Highlight
		//

		if (this.ocean)
			g.setColor(isSelected ? this.selectHighlight : FamilyLookAndFeel.getWhite());
		else
			g.setColor(isSelected ? this.selectHighlight : this.highlight);

		// Paint slant
		g.drawLine(1, 6, 6, 1);

		// Paint left
		g.drawLine(1, 6, 1, bottom);

		// Paint top
		g.drawLine(6, 1, right, 1);

		if (tabIndex != firstIndex)
		{
			if (this.tabPane.getSelectedIndex() == tabIndex - 1)
				g.setColor(this.selectHighlight);
			else
				g.setColor(this.ocean ? FamilyLookAndFeel.getWhite() : this.highlight);

			g.drawLine(1, 0, 1, 4);
		}

		//
		// Paint Border
		//

		if (this.ocean)
		{
			if (isSelected)
				g.setColor(this.oceanSelectedBorderColor);
			else
				g.setColor(this.darkShadow);
		}
		else
			g.setColor(this.darkShadow);

		// Paint slant
		g.drawLine(1, 5, 6, 0);

		// Paint top
		g.drawLine(6, 0, right, 0);

		// Paint bottom
		if (tabIndex == lastIndex)
			g.drawLine(0, bottom, right, bottom);

		// Paint left
		if (this.ocean)
			if (this.tabPane.getSelectedIndex() == tabIndex - 1)
			{
				g.drawLine(0, 5, 0, bottom);
				g.setColor(this.oceanSelectedBorderColor);
				g.drawLine(0, 0, 0, 5);
			}
			else if (isSelected)
			{
				g.drawLine(0, 6, 0, bottom);
				if (tabIndex != 0)
				{
					g.setColor(this.darkShadow);
					g.drawLine(0, 0, 0, 5);
				}
			}
			else if (tabIndex != firstIndex)
				g.drawLine(0, 0, 0, bottom);
			else
				g.drawLine(0, 6, 0, bottom);
		else
			// family
			if (tabIndex != firstIndex)
				g.drawLine(0, 0, 0, bottom);
			else
				g.drawLine(0, 6, 0, bottom);

		g.translate(-x, -y);
	}

	protected void paintBottomTabBorder(int tabIndex, Graphics g, int x, int y, int w, int h,
	        @SuppressWarnings("unused") int btm, @SuppressWarnings("unused") int rght, boolean isSelected)
	{
		int tabCount = this.tabPane.getTabCount();
		int currentRun = getRunForTab(tabCount, tabIndex);
		int lastIndex = lastTabInRun(tabCount, currentRun);
		int firstIndex = this.tabRuns[currentRun];
		boolean leftToRight = FamilyUtils.isLeftToRight(this.tabPane);

		int bottom = h - 1;
		int right = w - 1;

		//
		// Paint Gap
		//

		if (shouldFillGap(currentRun, tabIndex, x, y))
		{
			g.translate(x, y);

			if (leftToRight)
			{
				g.setColor(getColorForGap(currentRun, x, y));
				g.fillRect(1, bottom - 4, 3, 5);
				g.fillRect(4, bottom - 1, 2, 2);
			}
			else
			{
				g.setColor(getColorForGap(currentRun, x + w - 1, y));
				g.fillRect(right - 3, bottom - 3, 3, 4);
				g.fillRect(right - 5, bottom - 1, 2, 2);
				g.drawLine(right - 1, bottom - 4, right - 1, bottom - 4);
			}

			g.translate(-x, -y);
		}

		g.translate(x, y);

		//
		// Paint Border
		//

		if (this.ocean && isSelected)
			g.setColor(this.oceanSelectedBorderColor);
		else
			g.setColor(this.darkShadow);

		if (leftToRight)
		{

			// Paint slant
			g.drawLine(1, bottom - 5, 6, bottom);

			// Paint bottom
			g.drawLine(6, bottom, right, bottom);

			// Paint right
			if (tabIndex == lastIndex)
				g.drawLine(right, 0, right, bottom);

			// Paint left
			if (this.ocean && isSelected)
			{
				g.drawLine(0, 0, 0, bottom - 6);
				if ((currentRun == 0 && tabIndex != 0) || (currentRun > 0 && tabIndex != this.tabRuns[currentRun - 1]))
				{
					g.setColor(this.darkShadow);
					g.drawLine(0, bottom - 5, 0, bottom);
				}
			}
			else
			{
				if (this.ocean && tabIndex == this.tabPane.getSelectedIndex() + 1)
					g.setColor(this.oceanSelectedBorderColor);
				if (tabIndex != this.tabRuns[this.runCount - 1])
					g.drawLine(0, 0, 0, bottom);
				else
					g.drawLine(0, 0, 0, bottom - 6);
			}
		}
		else
		{

			// Paint slant
			g.drawLine(right - 1, bottom - 5, right - 6, bottom);

			// Paint bottom
			g.drawLine(right - 6, bottom, 0, bottom);

			// Paint left
			if (tabIndex == lastIndex)
				// last tab in run
				g.drawLine(0, 0, 0, bottom);

			// Paint right
			if (this.ocean && tabIndex == this.tabPane.getSelectedIndex() + 1)
			{
				g.setColor(this.oceanSelectedBorderColor);
				g.drawLine(right, 0, right, bottom);
			}
			else if (this.ocean && isSelected)
			{
				g.drawLine(right, 0, right, bottom - 6);
				if (tabIndex != firstIndex)
				{
					g.setColor(this.darkShadow);
					g.drawLine(right, bottom - 5, right, bottom);
				}
			}
			else if (tabIndex != this.tabRuns[this.runCount - 1])
				// not the first tab in the last run
				g.drawLine(right, 0, right, bottom);
			else
				// the first tab in the last run
				g.drawLine(right, 0, right, bottom - 6);
		}

		//
		// Paint Highlight
		//

		g.setColor(isSelected ? this.selectHighlight : this.highlight);

		if (leftToRight)
		{

			// Paint slant
			g.drawLine(1, bottom - 6, 6, bottom - 1);

			// Paint left
			g.drawLine(1, 0, 1, bottom - 6);

			// paint highlight in the gap on tab behind this one
			// on the left end (where they all line up)
			if (tabIndex == firstIndex && tabIndex != this.tabRuns[this.runCount - 1])
			{
				// first tab in run but not first tab in last run
				if (this.tabPane.getSelectedIndex() == this.tabRuns[currentRun + 1])
					// tab in front of selected tab
					g.setColor(this.selectHighlight);
				else
					// tab in front of normal tab
					g.setColor(this.highlight);
				g.drawLine(1, bottom - 4, 1, bottom);
			}
		}
		else

			// Paint left
			if (tabIndex == lastIndex)
				// last tab in run
				g.drawLine(1, 0, 1, bottom - 1);
			else
				g.drawLine(0, 0, 0, bottom - 1);

		g.translate(-x, -y);
	}

	protected void paintRightTabBorder(int tabIndex, Graphics g, int x, int y, int w, int h,
	        @SuppressWarnings("unused") int btm, @SuppressWarnings("unused") int rght, boolean isSelected)
	{
		@SuppressWarnings("unqualified-field-access")
		int tabCount = tabPane.getTabCount();
		int currentRun = getRunForTab(tabCount, tabIndex);
		int lastIndex = lastTabInRun(tabCount, currentRun);
		@SuppressWarnings("unqualified-field-access")
		int firstIndex = tabRuns[currentRun];

		g.translate(x, y);

		int bottom = h - 1;
		int right = w - 1;

		//
		// Paint part of the tab above
		//

		if (tabIndex != firstIndex && this.tabsOpaque)
		{
			g.setColor(this.tabPane.getSelectedIndex() == tabIndex - 1 ? this.selectColor
			        : getUnselectedBackgroundAt(tabIndex - 1));
			g.fillRect(right - 5, 0, 5, 3);
			g.fillRect(right - 2, 3, 2, 2);
		}

		//
		// Paint Highlight
		//

		g.setColor(isSelected ? this.selectHighlight : this.highlight);

		// Paint slant
		g.drawLine(right - 6, 1, right - 1, 6);

		// Paint top
		g.drawLine(0, 1, right - 6, 1);

		// Paint left
		if (!isSelected)
			g.drawLine(0, 1, 0, bottom);

		//
		// Paint Border
		//

		if (this.ocean && isSelected)
			g.setColor(this.oceanSelectedBorderColor);
		else
			g.setColor(this.darkShadow);

		// Paint bottom
		if (tabIndex == lastIndex)
			g.drawLine(0, bottom, right, bottom);

		// Paint slant
		if (this.ocean && this.tabPane.getSelectedIndex() == tabIndex - 1)
			g.setColor(this.oceanSelectedBorderColor);
		g.drawLine(right - 6, 0, right, 6);

		// Paint top
		g.drawLine(0, 0, right - 6, 0);

		// Paint right
		if (this.ocean && isSelected)
		{
			g.drawLine(right, 6, right, bottom);
			if (tabIndex != firstIndex)
			{
				g.setColor(this.darkShadow);
				g.drawLine(right, 0, right, 5);
			}
		}
		else if (this.ocean && this.tabPane.getSelectedIndex() == tabIndex - 1)
		{
			g.setColor(this.oceanSelectedBorderColor);
			g.drawLine(right, 0, right, 6);
			g.setColor(this.darkShadow);
			g.drawLine(right, 6, right, bottom);
		}
		else if (tabIndex != firstIndex)
			g.drawLine(right, 0, right, bottom);
		else
			g.drawLine(right, 6, right, bottom);

		g.translate(-x, -y);
	}

	@Override
	public void update(Graphics g, JComponent c)
	{
		if (c.isOpaque())
		{
			g.setColor(this.tabAreaBackground);
			g.fillRect(0, 0, c.getWidth(), c.getHeight());
		}
		paint(g, c);
	}

	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h,
	        boolean isSelected)
	{
		@SuppressWarnings("unused")
		int slantWidth = h / 2;
		if (isSelected)
			g.setColor(this.selectColor);
		else
			g.setColor(getUnselectedBackgroundAt(tabIndex));

		if (FamilyUtils.isLeftToRight(this.tabPane))
			switch(tabPlacement)
			{
				case LEFT:
					g.fillRect(x + 5, y + 1, w - 5, h - 1);
					g.fillRect(x + 2, y + 4, 3, h - 4);
					break;
				case BOTTOM:
					g.fillRect(x + 2, y, w - 2, h - 4);
					g.fillRect(x + 5, y + (h - 1) - 3, w - 5, 3);
					break;
				case RIGHT:
					g.fillRect(x, y + 2, w - 4, h - 2);
					g.fillRect(x + (w - 1) - 3, y + 5, 3, h - 5);
					break;
				case TOP:
				default:
					g.fillRect(x + 4, y + 2, (w - 1) - 3, (h - 1) - 1);
					g.fillRect(x + 2, y + 5, 2, h - 5);
			}
		else
			switch(tabPlacement)
			{
				case LEFT:
					g.fillRect(x + 5, y + 1, w - 5, h - 1);
					g.fillRect(x + 2, y + 4, 3, h - 4);
					break;
				case BOTTOM:
					g.fillRect(x, y, w - 5, h - 1);
					g.fillRect(x + (w - 1) - 4, y, 4, h - 5);
					g.fillRect(x + (w - 1) - 4, y + (h - 1) - 4, 2, 2);
					break;
				case RIGHT:
					g.fillRect(x + 1, y + 1, w - 5, h - 1);
					g.fillRect(x + (w - 1) - 3, y + 5, 3, h - 5);
					break;
				case TOP:
				default:
					g.fillRect(x, y + 2, (w - 1) - 3, (h - 1) - 1);
					g.fillRect(x + (w - 1) - 3, y + 5, 3, h - 3);
			}
	}

	/**
	 * Overridden to do nothing for the Java L&amp;F.
	 */
	@Override
	protected int getTabLabelShiftX(@SuppressWarnings("unused") int tabPlacement,
	        @SuppressWarnings("unused") int tabIndex, @SuppressWarnings("unused") boolean isSelected)
	{
		return 0;
	}

	/**
	 * Overridden to do nothing for the Java L&amp;F.
	 */
	@Override
	protected int getTabLabelShiftY(@SuppressWarnings("unused") int tabPlacement,
	        @SuppressWarnings("unused") int tabIndex, @SuppressWarnings("unused") boolean isSelected)
	{
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @since 1.6
	 */
	@Override
	protected int getBaselineOffset()
	{
		return 0;
	}

	@Override
	public void paint(Graphics g, JComponent c)
	{
		int tabPlacement = this.tabPane.getTabPlacement();

		Insets insets = c.getInsets();
		Dimension size = c.getSize();

		// Paint the background for the tab area
		if (this.tabPane.isOpaque())
		{
			if (!c.isBackgroundSet() && (this.tabAreaBackground != null))
				g.setColor(this.tabAreaBackground);
			else
				g.setColor(c.getBackground());
			switch(tabPlacement)
			{
				case LEFT:
					g.fillRect(insets.left, insets.top,
					        calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth),
					        size.height - insets.bottom - insets.top);
					break;
				case BOTTOM:
					int totalTabHeight = calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
					g.fillRect(insets.left, size.height - insets.bottom - totalTabHeight,
					        size.width - insets.left - insets.right, totalTabHeight);
					break;
				case RIGHT:
					int totalTabWidth = calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
					g.fillRect(size.width - insets.right - totalTabWidth, insets.top, totalTabWidth,
					        size.height - insets.top - insets.bottom);
					break;
				case TOP:
				default:
					g.fillRect(insets.left, insets.top, size.width - insets.right - insets.left,
					        calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight));
					paintHighlightBelowTab();
			}
		}

		super.paint(g, c);
	}

	protected void paintHighlightBelowTab()
	{

	}

	@Override
	protected void paintFocusIndicator(Graphics g, int tabPlacement, @SuppressWarnings("hiding") Rectangle[] rects,
	        int tabIndex, @SuppressWarnings("unused") Rectangle iconRect,
	        @SuppressWarnings("unused") Rectangle textRect, boolean isSelected)
	{
		if (this.tabPane.hasFocus() && isSelected)
		{
			Rectangle tabRect = rects[tabIndex];
			boolean lastInRun = isLastInRun(tabIndex);
			g.setColor(this.focus);
			g.translate(tabRect.x, tabRect.y);
			int right = tabRect.width - 1;
			int bottom = tabRect.height - 1;
			boolean leftToRight = FamilyUtils.isLeftToRight(this.tabPane);
			switch(tabPlacement)
			{
				case RIGHT:
					g.drawLine(right - 6, 2, right - 2, 6); // slant
					g.drawLine(1, 2, right - 6, 2); // top
					g.drawLine(right - 2, 6, right - 2, bottom); // right
					g.drawLine(1, 2, 1, bottom); // left
					g.drawLine(1, bottom, right - 2, bottom); // bottom
					break;
				case BOTTOM:
					if (leftToRight)
					{
						g.drawLine(2, bottom - 6, 6, bottom - 2); // slant
						g.drawLine(6, bottom - 2, right, bottom - 2); // bottom
						g.drawLine(2, 0, 2, bottom - 6); // left
						g.drawLine(2, 0, right, 0); // top
						g.drawLine(right, 0, right, bottom - 2); // right
					}
					else
					{
						g.drawLine(right - 2, bottom - 6, right - 6, bottom - 2); // slant
						g.drawLine(right - 2, 0, right - 2, bottom - 6); // right
						if (lastInRun)
						{
							// last tab in run
							g.drawLine(2, bottom - 2, right - 6, bottom - 2); // bottom
							g.drawLine(2, 0, right - 2, 0); // top
							g.drawLine(2, 0, 2, bottom - 2); // left
						}
						else
						{
							g.drawLine(1, bottom - 2, right - 6, bottom - 2); // bottom
							g.drawLine(1, 0, right - 2, 0); // top
							g.drawLine(1, 0, 1, bottom - 2); // left
						}
					}
					break;
				case LEFT:
					g.drawLine(2, 6, 6, 2); // slant
					g.drawLine(2, 6, 2, bottom - 1); // left
					g.drawLine(6, 2, right, 2); // top
					g.drawLine(right, 2, right, bottom - 1); // right
					g.drawLine(2, bottom - 1, right, bottom - 1); // bottom
					break;
				case TOP:
				default:
					if (leftToRight)
					{
						g.drawLine(2, 6, 6, 2); // slant
						g.drawLine(2, 6, 2, bottom - 1); // left
						g.drawLine(6, 2, right, 2); // top
						g.drawLine(right, 2, right, bottom - 1); // right
						g.drawLine(2, bottom - 1, right, bottom - 1); // bottom
					}
					else
					{
						g.drawLine(right - 2, 6, right - 6, 2); // slant
						g.drawLine(right - 2, 6, right - 2, bottom - 1); // right
						if (lastInRun)
						{
							// last tab in run
							g.drawLine(right - 6, 2, 2, 2); // top
							g.drawLine(2, 2, 2, bottom - 1); // left
							g.drawLine(right - 2, bottom - 1, 2, bottom - 1); // bottom
						}
						else
						{
							g.drawLine(right - 6, 2, 1, 2); // top
							g.drawLine(1, 2, 1, bottom - 1); // left
							g.drawLine(right - 2, bottom - 1, 1, bottom - 1); // bottom
						}
					}
			}
			g.translate(-tabRect.x, -tabRect.y);
		}
	}

	@Override
	protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w,
	        @SuppressWarnings("unused") int h)
	{
		boolean leftToRight = FamilyUtils.isLeftToRight(this.tabPane);
		int right = x + w - 1;
		Rectangle selRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, this.calcRect);
		if (this.ocean)
			g.setColor(this.oceanSelectedBorderColor);
		else
			g.setColor(this.selectHighlight);

		// Draw unbroken line if tabs are not on TOP, OR
		// selected tab is not in run adjacent to content, OR
		// selected tab is not visible (SCROLL_TAB_LAYOUT)
		//
		if (tabPlacement != TOP || selectedIndex < 0 || (selRect.y + selRect.height + 1 < y)
		        || (selRect.x < x || selRect.x > x + w))
		{
			g.drawLine(x, y, x + w - 2, y);
			if (this.ocean && tabPlacement == TOP)
			{
				g.setColor(FamilyLookAndFeel.getWhite());
				g.drawLine(x, y + 1, x + w - 2, y + 1);
			}
		}
		else
		{
			// Break line to show visual connection to selected tab
			boolean lastInRun = isLastInRun(selectedIndex);

			if (leftToRight || lastInRun)
				g.drawLine(x, y, selRect.x + 1, y);
			else
				g.drawLine(x, y, selRect.x, y);

			if (selRect.x + selRect.width < right - 1)
				if (leftToRight && !lastInRun)
					g.drawLine(selRect.x + selRect.width, y, right - 1, y);
				else
					g.drawLine(selRect.x + selRect.width - 1, y, right - 1, y);
			else
			{
				g.setColor(this.shadow);
				g.drawLine(x + w - 2, y, x + w - 2, y);
			}

			if (this.ocean)
			{
				g.setColor(FamilyLookAndFeel.getWhite());

				if (leftToRight || lastInRun)
					g.drawLine(x, y + 1, selRect.x + 1, y + 1);
				else
					g.drawLine(x, y + 1, selRect.x, y + 1);

				if (selRect.x + selRect.width < right - 1)
				{
					if (leftToRight && !lastInRun)
						g.drawLine(selRect.x + selRect.width, y + 1, right - 1, y + 1);
					else
						g.drawLine(selRect.x + selRect.width - 1, y + 1, right - 1, y + 1);
				}
				else
				{
					g.setColor(this.shadow);
					g.drawLine(x + w - 2, y + 1, x + w - 2, y + 1);
				}
			}
		}
	}

	@Override
	protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w,
	        int h)
	{
		boolean leftToRight = FamilyUtils.isLeftToRight(this.tabPane);
		int bottom = y + h - 1;
		int right = x + w - 1;
		Rectangle selRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, this.calcRect);

		g.setColor(this.darkShadow);

		// Draw unbroken line if tabs are not on BOTTOM, OR
		// selected tab is not in run adjacent to content, OR
		// selected tab is not visible (SCROLL_TAB_LAYOUT)
		//
		if (tabPlacement != BOTTOM || selectedIndex < 0 || (selRect.y - 1 > h) || (selRect.x < x || selRect.x > x + w))
		{
			if (this.ocean && tabPlacement == BOTTOM)
				g.setColor(this.oceanSelectedBorderColor);
			g.drawLine(x, y + h - 1, x + w - 1, y + h - 1);
		}
		else
		{
			// Break line to show visual connection to selected tab
			boolean lastInRun = isLastInRun(selectedIndex);

			if (this.ocean)
				g.setColor(this.oceanSelectedBorderColor);

			if (leftToRight || lastInRun)
				g.drawLine(x, bottom, selRect.x, bottom);
			else
				g.drawLine(x, bottom, selRect.x - 1, bottom);

			if (selRect.x + selRect.width < x + w - 2)
				if (leftToRight && !lastInRun)
					g.drawLine(selRect.x + selRect.width, bottom, right, bottom);
				else
					g.drawLine(selRect.x + selRect.width - 1, bottom, right, bottom);
		}
	}

	@Override
	protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y,
	        @SuppressWarnings("unused") int w, int h)
	{
		Rectangle selRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, this.calcRect);
		if (this.ocean)
			g.setColor(this.oceanSelectedBorderColor);
		else
			g.setColor(this.selectHighlight);

		// Draw unbroken line if tabs are not on LEFT, OR
		// selected tab is not in run adjacent to content, OR
		// selected tab is not visible (SCROLL_TAB_LAYOUT)
		//
		if (tabPlacement != LEFT || selectedIndex < 0 || (selRect.x + selRect.width + 1 < x) || (selRect.y < y || selRect.y > y + h))
		{
			g.drawLine(x, y + 1, x, y + h - 2);
			if (this.ocean && tabPlacement == LEFT)
			{
				g.setColor(FamilyLookAndFeel.getWhite());
				g.drawLine(x + 1, y, x + 1, y + h - 2);
			}
		}
		else
		{
			// Break line to show visual connection to selected tab
			g.drawLine(x, y, x, selRect.y + 1);
			if (selRect.y + selRect.height < y + h - 2)
				g.drawLine(x, selRect.y + selRect.height + 1, x, y + h + 2);
			if (this.ocean)
			{
				g.setColor(FamilyLookAndFeel.getWhite());
				g.drawLine(x + 1, y + 1, x + 1, selRect.y + 1);
				if (selRect.y + selRect.height < y + h - 2)
					g.drawLine(x + 1, selRect.y + selRect.height + 1, x + 1, y + h + 2);
			}
		}
	}

	@Override
	protected void paintContentBorderRightEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w,
	        int h)
	{
		Rectangle selRect = selectedIndex < 0 ? null : getTabBounds(selectedIndex, this.calcRect);

		g.setColor(this.darkShadow);
		// Draw unbroken line if tabs are not on RIGHT, OR
		// selected tab is not in run adjacent to content, OR
		// selected tab is not visible (SCROLL_TAB_LAYOUT)
		//
		if (tabPlacement != RIGHT || selectedIndex < 0 || (selRect.x - 1 > w) || (selRect.y < y || selRect.y > y + h))
		{
			if (this.ocean && tabPlacement == RIGHT)
				g.setColor(this.oceanSelectedBorderColor);
			g.drawLine(x + w - 1, y, x + w - 1, y + h - 1);
		}
		else
		{
			// Break line to show visual connection to selected tab
			if (this.ocean)
				g.setColor(this.oceanSelectedBorderColor);
			g.drawLine(x + w - 1, y, x + w - 1, selRect.y);

			if (selRect.y + selRect.height < y + h - 2)
				g.drawLine(x + w - 1, selRect.y + selRect.height, x + w - 1, y + h - 2);
		}
	}

	@Override
	protected int calculateMaxTabHeight(int tabPlacement)
	{
		FontMetrics metrics = getFontMetrics();
		int height = metrics.getHeight();
		boolean tallerIcons = false;

		for (int i = 0; i < this.tabPane.getTabCount(); ++i)
		{
			Icon icon = this.tabPane.getIconAt(i);
			if (icon != null)
				if (icon.getIconHeight() > height)
				{
					tallerIcons = true;
					break;
				}
		}
		return super.calculateMaxTabHeight(tabPlacement) - (tallerIcons ? (this.tabInsets.top + this.tabInsets.bottom) : 0);
	}

	@Override
	protected int getTabRunOverlay(int tabPlacement)
	{
		// Tab runs laid out vertically should overlap
		// at least as much as the largest slant
		if (tabPlacement == LEFT || tabPlacement == RIGHT)
		{
			@SuppressWarnings("hiding")
			int maxTabHeight = calculateMaxTabHeight(tabPlacement);
			return maxTabHeight / 2;
		}
		return 0;
	}

	// Don't rotate runs!
	protected boolean shouldRotateTabRuns(@SuppressWarnings("unused") int tabPlacement,
	        @SuppressWarnings({ "unused", "hiding" }) int selectedRun)
	{
		return false;
	}

	// Don't pad last run
	@Override
	protected boolean shouldPadTabRun(@SuppressWarnings("unused") int tabPlacement, int run)
	{
		return this.runCount > 1 && run < this.runCount - 1;
	}

	private boolean isLastInRun(int tabIndex)
	{
		int run = getRunForTab(this.tabPane.getTabCount(), tabIndex);
		int lastIndex = lastTabInRun(this.tabPane.getTabCount(), run);
		return tabIndex == lastIndex;
	}

	/**
	 * Returns the color to use for the specified tab.
	 */
	private Color getUnselectedBackgroundAt(int index)
	{
		Color color = this.tabPane.getBackgroundAt(index);
		if (color instanceof UIResource)
			if (this.unselectedBackground != null) { return this.unselectedBackground; }
		return color;
	}

	/**
	 * Returns the tab index of JTabbedPane the mouse is currently over
	 */
	int getRolloverTabIndex()
	{
		return getRolloverTab();
	}

	/**
	 * This class should be treated as a &quot;protected&quot; inner class.
	 * Instantiate it only within subclasses of {@code FamilyTabbedPaneUI}.
	 */
	public class TabbedPaneLayout extends BasicTabbedPaneUI.TabbedPaneLayout
	{

		public TabbedPaneLayout()
		{
			FamilyTabbedPaneUI.this.super();
		}

		@Override
		protected void normalizeTabRuns(int tabPlacement, int tabCount, int start, int max)
		{
			// Only normalize the runs for top & bottom; normalizing
			// doesn't look right for Family's vertical tabs
			// because the last run isn't padded and it looks odd to have
			// fat tabs in the first vertical runs, but slimmer ones in the
			// last (this effect isn't noticeable for horizontal tabs).
			if (tabPlacement == TOP || tabPlacement == BOTTOM)
			{
				super.normalizeTabRuns(tabPlacement, tabCount, start, max);
			}
		}

		// Don't rotate runs!
		@Override
		protected void rotateTabRuns(@SuppressWarnings("unused") int tabPlacement,
		        @SuppressWarnings({ "unused", "hiding" }) int selectedRun)
		{}

		// Don't pad selected tab
		@Override
		protected void padSelectedTab(@SuppressWarnings("unused") int tabPlacement,
		        @SuppressWarnings("unused") int selectedIndex)
		{}
	}

}
