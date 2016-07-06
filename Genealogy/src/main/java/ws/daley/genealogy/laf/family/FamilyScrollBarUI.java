/*
 * Copyright (c) 1998, 2014, Oracle and/or its affiliates. All rights reserved.
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

import static sun.swing.SwingUtilities2.drawHLine;
import static sun.swing.SwingUtilities2.drawRect;
import static sun.swing.SwingUtilities2.drawVLine;

import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * Implementation of ScrollBarUI for the Family Look and Feel
 * <p>
 *
 * @author Tom Santos
 * @author Steve Wilson
 */
public class FamilyScrollBarUI extends BasicScrollBarUI
{
	private static Color shadowColor;
	private static Color highlightColor;
	private static Color darkShadowColor;
	@SuppressWarnings("hiding")
	private static Color thumbColor;
	private static Color thumbShadow;
	@SuppressWarnings("hiding")
	private static Color thumbHighlightColor;

	protected FamilyBumps bumps;

	protected FamilyScrollButton increaseButton;
	protected FamilyScrollButton decreaseButton;

	@SuppressWarnings("hiding")
	protected int scrollBarWidth;

	public static final String FREE_STANDING_PROP = "JScrollBar.isFreeStanding";
	protected boolean isFreeStanding = true;

	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		return new FamilyScrollBarUI();
	}

	@Override
	protected void installDefaults()
	{
		this.scrollBarWidth = ((Integer) (UIManager.get("ScrollBar.width"))).intValue();
		super.installDefaults();
		this.bumps = new FamilyBumps(10, 10, thumbHighlightColor, thumbShadow, thumbColor);
	}

	@Override
	protected void installListeners()
	{
		super.installListeners();
		((ScrollBarListener) this.propertyChangeListener)
		        .handlePropertyChange(this.scrollbar.getClientProperty(FREE_STANDING_PROP));
	}

	@Override
	protected PropertyChangeListener createPropertyChangeListener()
	{
		return new ScrollBarListener();
	}

	@Override
	protected void configureScrollBarColors()
	{
		super.configureScrollBarColors();
		shadowColor = UIManager.getColor("ScrollBar.shadow");
		highlightColor = UIManager.getColor("ScrollBar.highlight");
		darkShadowColor = UIManager.getColor("ScrollBar.darkShadow");
		thumbColor = UIManager.getColor("ScrollBar.thumb");
		thumbShadow = UIManager.getColor("ScrollBar.thumbShadow");
		thumbHighlightColor = UIManager.getColor("ScrollBar.thumbHighlight");

	}

	@SuppressWarnings("unqualified-field-access")
	@Override
	public Dimension getPreferredSize(@SuppressWarnings("unused") JComponent c)
	{
		if (this.scrollbar.getOrientation() == Adjustable.VERTICAL)
		    return new Dimension(this.scrollBarWidth, this.scrollBarWidth * 3 + 10);
		return new Dimension(scrollBarWidth * 3 + 10, scrollBarWidth);

	}

	/**
	 * Returns the view that represents the decrease view.
	 */
	@Override
	protected JButton createDecreaseButton(int orientation)
	{
		this.decreaseButton = new FamilyScrollButton(orientation, this.scrollBarWidth, this.isFreeStanding);
		return this.decreaseButton;
	}

	/** Returns the view that represents the increase view. */
	@Override
	protected JButton createIncreaseButton(int orientation)
	{
		this.increaseButton = new FamilyScrollButton(orientation, this.scrollBarWidth, this.isFreeStanding);
		return this.increaseButton;
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)
	{
		g.translate(trackBounds.x, trackBounds.y);

		boolean leftToRight = FamilyUtils.isLeftToRight(c);

		if (this.scrollbar.getOrientation() == Adjustable.VERTICAL)
		{
			if (!this.isFreeStanding)
			{
				trackBounds.width += 2;
				if (!leftToRight) g.translate(-1, 0);
			}

			if (c.isEnabled())
			{
				g.setColor(darkShadowColor);
				drawVLine(g, 0, 0, trackBounds.height - 1);
				drawVLine(g, trackBounds.width - 2, 0, trackBounds.height - 1);
				drawHLine(g, 2, trackBounds.width - 1, trackBounds.height - 1);
				drawHLine(g, 2, trackBounds.width - 2, 0);

				g.setColor(shadowColor);
				// g.setColor( Color.red);
				drawVLine(g, 1, 1, trackBounds.height - 2);
				drawHLine(g, 1, trackBounds.width - 3, 1);
				if (this.scrollbar.getValue() != this.scrollbar.getMaximum())
				{ // thumb shadow
					int y = this.thumbRect.y + this.thumbRect.height - trackBounds.y;
					drawHLine(g, 1, trackBounds.width - 1, y);
				}
				g.setColor(highlightColor);
				drawVLine(g, trackBounds.width - 1, 0, trackBounds.height - 1);
			}
			else
				FamilyUtils.drawDisabledBorder(g, 0, 0, trackBounds.width, trackBounds.height);

			if (!this.isFreeStanding)
			{
				trackBounds.width -= 2;
				if (!leftToRight)
					g.translate(1, 0);
			}
		}
		else // HORIZONTAL
		{
			if (!this.isFreeStanding)
				trackBounds.height += 2;

			if (c.isEnabled())
			{
				g.setColor(darkShadowColor);
				drawHLine(g, 0, trackBounds.width - 1, 0); // top
				drawVLine(g, 0, 2, trackBounds.height - 2); // left
				drawHLine(g, 0, trackBounds.width - 1, trackBounds.height - 2); // bottom
				drawVLine(g, trackBounds.width - 1, 2, trackBounds.height - 1); // right

				g.setColor(shadowColor);
				// g.setColor( Color.red);
				drawHLine(g, 1, trackBounds.width - 2, 1); // top
				drawVLine(g, 1, 1, trackBounds.height - 3); // left
				drawHLine(g, 0, trackBounds.width - 1, trackBounds.height - 1); // bottom
				if (this.scrollbar.getValue() != this.scrollbar.getMaximum())
				{ // thumb shadow
					int x = this.thumbRect.x + this.thumbRect.width - trackBounds.x;
					drawVLine(g, x, 1, trackBounds.height - 1);
				}
			}
			else
				FamilyUtils.drawDisabledBorder(g, 0, 0, trackBounds.width, trackBounds.height);

			if (!this.isFreeStanding)
				trackBounds.height -= 2;
		}

		g.translate(-trackBounds.x, -trackBounds.y);
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
	{
		if (!c.isEnabled()) { return; }

		if (FamilyLookAndFeel.usingOcean())
		{
			oceanPaintThumb(g, c, thumbBounds);
			return;
		}

		boolean leftToRight = FamilyUtils.isLeftToRight(c);

		g.translate(thumbBounds.x, thumbBounds.y);

		if (this.scrollbar.getOrientation() == Adjustable.VERTICAL)
		{
			if (!this.isFreeStanding)
			{
				thumbBounds.width += 2;
				if (!leftToRight)
					g.translate(-1, 0);
			}

			g.setColor(thumbColor);
			g.fillRect(0, 0, thumbBounds.width - 2, thumbBounds.height - 1);

			g.setColor(thumbShadow);
			drawRect(g, 0, 0, thumbBounds.width - 2, thumbBounds.height - 1);

			g.setColor(thumbHighlightColor);
			drawHLine(g, 1, thumbBounds.width - 3, 1);
			drawVLine(g, 1, 1, thumbBounds.height - 2);

			this.bumps.setBumpArea(thumbBounds.width - 6, thumbBounds.height - 7);
			this.bumps.paintIcon(c, g, 3, 4);

			if (!this.isFreeStanding)
			{
				thumbBounds.width -= 2;
				if (!leftToRight)
					g.translate(1, 0);
			}
		}
		else // HORIZONTAL
		{
			if (!this.isFreeStanding)
				thumbBounds.height += 2;

			g.setColor(thumbColor);
			g.fillRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 2);

			g.setColor(thumbShadow);
			drawRect(g, 0, 0, thumbBounds.width - 1, thumbBounds.height - 2);

			g.setColor(thumbHighlightColor);
			drawHLine(g, 1, thumbBounds.width - 3, 1);
			drawVLine(g, 1, 1, thumbBounds.height - 3);

			this.bumps.setBumpArea(thumbBounds.width - 7, thumbBounds.height - 6);
			this.bumps.paintIcon(c, g, 4, 3);

			if (!this.isFreeStanding)
				thumbBounds.height -= 2;
		}

		g.translate(-thumbBounds.x, -thumbBounds.y);
	}

	private void oceanPaintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
	{
		boolean leftToRight = FamilyUtils.isLeftToRight(c);

		g.translate(thumbBounds.x, thumbBounds.y);

		if (this.scrollbar.getOrientation() == Adjustable.VERTICAL)
		{
			if (!this.isFreeStanding)
			{
				thumbBounds.width += 2;
				if (!leftToRight)
					g.translate(-1, 0);
			}

			if (thumbColor != null)
			{
				g.setColor(thumbColor);
				g.fillRect(0, 0, thumbBounds.width - 2, thumbBounds.height - 1);
			}

			g.setColor(thumbShadow);
			drawRect(g, 0, 0, thumbBounds.width - 2, thumbBounds.height - 1);

			g.setColor(thumbHighlightColor);
			drawHLine(g, 1, thumbBounds.width - 3, 1);
			drawVLine(g, 1, 1, thumbBounds.height - 2);

			FamilyUtils.drawGradient(c, g, "ScrollBar.gradient", 2, 2, thumbBounds.width - 4, thumbBounds.height - 3,
			        false);

			int gripSize = thumbBounds.width - 8;
			if (gripSize > 2 && thumbBounds.height >= 10)
			{
				g.setColor(FamilyLookAndFeel.getPrimaryControlDarkShadow());
				int gripY = thumbBounds.height / 2 - 2;
				for (int counter = 0; counter < 6; counter += 2)
					g.fillRect(4, counter + gripY, gripSize, 1);

				g.setColor(FamilyLookAndFeel.getWhite());
				gripY++;
				for (int counter = 0; counter < 6; counter += 2)
					g.fillRect(5, counter + gripY, gripSize, 1);
			}
			if (!this.isFreeStanding)
			{
				thumbBounds.width -= 2;
				if (!leftToRight)
					g.translate(1, 0);
			}
		}
		else
		{ // HORIZONTAL
			if (!this.isFreeStanding)
				thumbBounds.height += 2;

			if (thumbColor != null)
			{
				g.setColor(thumbColor);
				g.fillRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 2);
			}

			g.setColor(thumbShadow);
			drawRect(g, 0, 0, thumbBounds.width - 1, thumbBounds.height - 2);

			g.setColor(thumbHighlightColor);
			drawHLine(g, 1, thumbBounds.width - 2, 1);
			drawVLine(g, 1, 1, thumbBounds.height - 3);

			FamilyUtils.drawGradient(c, g, "ScrollBar.gradient", 2, 2, thumbBounds.width - 3, thumbBounds.height - 4, true);

			int gripSize = thumbBounds.height - 8;
			if (gripSize > 2 && thumbBounds.width >= 10)
			{
				g.setColor(FamilyLookAndFeel.getPrimaryControlDarkShadow());
				int gripX = thumbBounds.width / 2 - 2;
				for (int counter = 0; counter < 6; counter += 2)
					g.fillRect(gripX + counter, 4, 1, gripSize);

				g.setColor(FamilyLookAndFeel.getWhite());
				gripX++;
				for (int counter = 0; counter < 6; counter += 2)
					g.fillRect(gripX + counter, 5, 1, gripSize);
			}

			if (!this.isFreeStanding)
				thumbBounds.height -= 2;
		}

		g.translate(-thumbBounds.x, -thumbBounds.y);
	}

	@Override
	protected Dimension getMinimumThumbSize()
	{
		return new Dimension(this.scrollBarWidth, this.scrollBarWidth);
	}

	/**
	 * This is overridden only to increase the invalid area. This ensures that
	 * the "Shadow" below the thumb is invalidated
	 */
	@Override
	protected void setThumbBounds(int x, int y, int width, int height)
	{
		/*
		 * If the thumbs bounds haven't changed, we're done.
		 */
		if ((this.thumbRect.x == x) && (this.thumbRect.y == y) && (this.thumbRect.width == width)
		        && (this.thumbRect.height == height))
			return;

		/*
		 * Update thumbRect, and repaint the union of x,y,w,h and the old
		 * thumbRect.
		 */
		int minX = Math.min(x, this.thumbRect.x);
		int minY = Math.min(y, this.thumbRect.y);
		int maxX = Math.max(x + width, this.thumbRect.x + this.thumbRect.width);
		int maxY = Math.max(y + height, this.thumbRect.y + this.thumbRect.height);

		this.thumbRect.setBounds(x, y, width, height);
		this.scrollbar.repaint(minX, minY, (maxX - minX) + 1, (maxY - minY) + 1);
	}

	class ScrollBarListener extends BasicScrollBarUI.PropertyChangeHandler
	{
		@Override
		public void propertyChange(PropertyChangeEvent e)
		{
			String name = e.getPropertyName();
			if (name.equals(FREE_STANDING_PROP))
				handlePropertyChange(e.getNewValue());
			else
				super.propertyChange(e);
		}

		public void handlePropertyChange(Object newValue)
		{
			if (newValue != null)
			{
				boolean temp = ((Boolean) newValue).booleanValue();
				boolean becameFlush = temp == false && FamilyScrollBarUI.this.isFreeStanding == true;
				boolean becameNormal = temp == true && FamilyScrollBarUI.this.isFreeStanding == false;

				FamilyScrollBarUI.this.isFreeStanding = temp;

				if (becameFlush)
					toFlush();
				else if (becameNormal)
					toFreeStanding();
			}
			else
			{

				if (!FamilyScrollBarUI.this.isFreeStanding)
				{
					FamilyScrollBarUI.this.isFreeStanding = true;
					toFreeStanding();
				}

				// This commented-out block is used for testing flush
				// scrollbars.
				/*
				 * if ( isFreeStanding ) { isFreeStanding = false; toFlush(); }
				 */
			}

			if (FamilyScrollBarUI.this.increaseButton != null)
				FamilyScrollBarUI.this.increaseButton.setFreeStanding(FamilyScrollBarUI.this.isFreeStanding);
			if (FamilyScrollBarUI.this.decreaseButton != null)
				FamilyScrollBarUI.this.decreaseButton.setFreeStanding(FamilyScrollBarUI.this.isFreeStanding);
		}

		protected void toFlush()
		{
			FamilyScrollBarUI.this.scrollBarWidth -= 2;
		}

		protected void toFreeStanding()
		{
			FamilyScrollBarUI.this.scrollBarWidth += 2;
		}
	} // end class ScrollBarListener
}
