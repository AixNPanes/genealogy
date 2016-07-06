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
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 * A Java L&amp;F implementation of SliderUI.
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
public class FamilySliderUI extends BasicSliderUI
{

	protected final int TICK_BUFFER = 4;
	protected boolean filledSlider = false;
	// NOTE: these next five variables are currently unused.
	protected static Color thumbColor;
	protected static Color highlightColor;
	protected static Color darkShadowColor;
	protected static int trackWidth;
	protected static int tickLength;
	private int safeLength;

	/**
	 * A default horizontal thumb <code>Icon</code>. This field might not be
	 * used. To change the <code>Icon</code> used by this delegate directly set
	 * it using the <code>Slider.horizontalThumbIcon</code> UIManager property.
	 */
	protected static Icon horizThumbIcon;

	/**
	 * A default vertical thumb <code>Icon</code>. This field might not be used.
	 * To change the <code>Icon</code> used by this delegate directly set it
	 * using the <code>Slider.verticalThumbIcon</code> UIManager property.
	 */
	protected static Icon vertThumbIcon;

	private static Icon SAFE_HORIZ_THUMB_ICON;
	private static Icon SAFE_VERT_THUMB_ICON;

	protected final String SLIDER_FILL = "JSlider.isFilled";

	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		return new FamilySliderUI();
	}

	public FamilySliderUI()
	{
		super(null);
	}

	private static Icon getHorizThumbIcon()
	{
		if (System.getSecurityManager() != null)
			return SAFE_HORIZ_THUMB_ICON;
		return horizThumbIcon;
	}

	private static Icon getVertThumbIcon()
	{
		if (System.getSecurityManager() != null)
			return SAFE_VERT_THUMB_ICON;
		return vertThumbIcon;
	}

	@Override
	public void installUI(JComponent c)
	{
		trackWidth = ((Integer) UIManager.get("Slider.trackWidth")).intValue();
		tickLength = this.safeLength = ((Integer) UIManager.get("Slider.majorTickLength")).intValue();
		horizThumbIcon = SAFE_HORIZ_THUMB_ICON = UIManager.getIcon("Slider.horizontalThumbIcon");
		vertThumbIcon = SAFE_VERT_THUMB_ICON = UIManager.getIcon("Slider.verticalThumbIcon");

		super.installUI(c);

		thumbColor = UIManager.getColor("Slider.thumb");
		highlightColor = UIManager.getColor("Slider.highlight");
		darkShadowColor = UIManager.getColor("Slider.darkShadow");

		this.scrollListener.setScrollByBlock(false);

		prepareFilledSliderField();
	}

	@SuppressWarnings("hiding")
	@Override
	protected PropertyChangeListener createPropertyChangeListener(@SuppressWarnings("unused") JSlider slider)
	{
		return new FamilyPropertyListener();
	}

	protected class FamilyPropertyListener extends BasicSliderUI.PropertyChangeHandler
	{
		@SuppressWarnings({ "unqualified-field-access", "synthetic-access" })
		@Override
		public void propertyChange(PropertyChangeEvent e)
		{ // listen for slider fill
			super.propertyChange(e);

			if (e.getPropertyName().equals(SLIDER_FILL))
				prepareFilledSliderField();
		}
	}

	private void prepareFilledSliderField()
	{
		// Use true for Ocean theme
		this.filledSlider = FamilyLookAndFeel.usingOcean();

		Object sliderFillProp = this.slider.getClientProperty(this.SLIDER_FILL);

		if (sliderFillProp != null)
			this.filledSlider = ((Boolean) sliderFillProp).booleanValue();
	}

	@Override
	public void paintThumb(Graphics g)
	{
		Rectangle knobBounds = this.thumbRect;

		g.translate(knobBounds.x, knobBounds.y);

		if (this.slider.getOrientation() == SwingConstants.HORIZONTAL)
			getHorizThumbIcon().paintIcon(this.slider, g, 0, 0);
		else
			getVertThumbIcon().paintIcon(this.slider, g, 0, 0);

		g.translate(-knobBounds.x, -knobBounds.y);
	}

	/**
	 * Returns a rectangle enclosing the track that will be painted.
	 */
	private Rectangle getPaintTrackRect()
	{
		int trackLeft = 0, trackRight, trackTop = 0, trackBottom;
		if (this.slider.getOrientation() == SwingConstants.HORIZONTAL)
		{
			trackBottom = (this.trackRect.height - 1) - getThumbOverhang();
			trackTop = trackBottom - (getTrackWidth() - 1);
			trackRight = this.trackRect.width - 1;
		}
		else
		{
			if (FamilyUtils.isLeftToRight(this.slider))
			{
				trackLeft = (this.trackRect.width - getThumbOverhang()) - getTrackWidth();
				trackRight = (this.trackRect.width - getThumbOverhang()) - 1;
			}
			else
			{
				trackLeft = getThumbOverhang();
				trackRight = getThumbOverhang() + getTrackWidth() - 1;
			}
			trackBottom = this.trackRect.height - 1;
		}
		return new Rectangle(this.trackRect.x + trackLeft, this.trackRect.y + trackTop, trackRight - trackLeft, trackBottom - trackTop);
	}

	@Override
	public void paintTrack(Graphics g)
	{
		if (FamilyLookAndFeel.usingOcean())
		{
			oceanPaintTrack(g);
			return;
		}
		@SuppressWarnings("unused")
		Color trackColor = !this.slider.isEnabled() ? FamilyLookAndFeel.getControlShadow() : this.slider.getForeground();

		boolean leftToRight = FamilyUtils.isLeftToRight(this.slider);

		g.translate(this.trackRect.x, this.trackRect.y);

		int trackLeft = 0;
		int trackTop = 0;
		int trackRight;
		int trackBottom;

		// Draw the track
		if (this.slider.getOrientation() == SwingConstants.HORIZONTAL)
		{
			trackBottom = (this.trackRect.height - 1) - getThumbOverhang();
			trackTop = trackBottom - (getTrackWidth() - 1);
			trackRight = this.trackRect.width - 1;
		}
		else
		{
			if (leftToRight)
			{
				trackLeft = (this.trackRect.width - getThumbOverhang()) - getTrackWidth();
				trackRight = (this.trackRect.width - getThumbOverhang()) - 1;
			}
			else
			{
				trackLeft = getThumbOverhang();
				trackRight = getThumbOverhang() + getTrackWidth() - 1;
			}
			trackBottom = this.trackRect.height - 1;
		}

		if (this.slider.isEnabled())
		{
			g.setColor(FamilyLookAndFeel.getControlDarkShadow());
			g.drawRect(trackLeft, trackTop, (trackRight - trackLeft) - 1, (trackBottom - trackTop) - 1);

			g.setColor(FamilyLookAndFeel.getControlHighlight());
			g.drawLine(trackLeft + 1, trackBottom, trackRight, trackBottom);
			g.drawLine(trackRight, trackTop + 1, trackRight, trackBottom);

			g.setColor(FamilyLookAndFeel.getControlShadow());
			g.drawLine(trackLeft + 1, trackTop + 1, trackRight - 2, trackTop + 1);
			g.drawLine(trackLeft + 1, trackTop + 1, trackLeft + 1, trackBottom - 2);
		}
		else
		{
			g.setColor(FamilyLookAndFeel.getControlShadow());
			g.drawRect(trackLeft, trackTop, (trackRight - trackLeft) - 1, (trackBottom - trackTop) - 1);
		}

		// Draw the fill
		if (this.filledSlider)
		{
			int middleOfThumb;
			int fillTop;
			int fillLeft;
			int fillBottom;
			int fillRight;

			if (this.slider.getOrientation() == SwingConstants.HORIZONTAL)
			{
				middleOfThumb = this.thumbRect.x + (this.thumbRect.width / 2);
				middleOfThumb -= this.trackRect.x; // To compensate for the
				// g.translate()
				fillTop = !this.slider.isEnabled() ? trackTop : trackTop + 1;
				fillBottom = !this.slider.isEnabled() ? trackBottom - 1 : trackBottom - 2;

				if (!drawInverted())
				{
					fillLeft = !this.slider.isEnabled() ? trackLeft : trackLeft + 1;
					fillRight = middleOfThumb;
				}
				else
				{
					fillLeft = middleOfThumb;
					fillRight = !this.slider.isEnabled() ? trackRight - 1 : trackRight - 2;
				}
			}
			else
			{
				middleOfThumb = this.thumbRect.y + (this.thumbRect.height / 2);
				middleOfThumb -= this.trackRect.y; // To compensate for the
				// g.translate()
				fillLeft = !this.slider.isEnabled() ? trackLeft : trackLeft + 1;
				fillRight = !this.slider.isEnabled() ? trackRight - 1 : trackRight - 2;

				if (!drawInverted())
				{
					fillTop = middleOfThumb;
					fillBottom = !this.slider.isEnabled() ? trackBottom - 1 : trackBottom - 2;
				}
				else
				{
					fillTop = !this.slider.isEnabled() ? trackTop : trackTop + 1;
					fillBottom = middleOfThumb;
				}
			}

			if (this.slider.isEnabled())
			{
				g.setColor(this.slider.getBackground());
				g.drawLine(fillLeft, fillTop, fillRight, fillTop);
				g.drawLine(fillLeft, fillTop, fillLeft, fillBottom);

				g.setColor(FamilyLookAndFeel.getControlShadow());
				g.fillRect(fillLeft + 1, fillTop + 1, fillRight - fillLeft, fillBottom - fillTop);
			}
			else
			{
				g.setColor(FamilyLookAndFeel.getControlShadow());
				g.fillRect(fillLeft, fillTop, fillRight - fillLeft, fillBottom - fillTop);
			}
		}

		g.translate(-this.trackRect.x, -this.trackRect.y);
	}

	private void oceanPaintTrack(Graphics g)
	{
		boolean leftToRight = FamilyUtils.isLeftToRight(this.slider);
		boolean drawInverted = drawInverted();
		Color sliderAltTrackColor = (Color) UIManager.get("Slider.altTrackColor");

		// Translate to the origin of the painting rectangle
		Rectangle paintRect = getPaintTrackRect();
		g.translate(paintRect.x, paintRect.y);

		// Width and height of the painting rectangle.
		int w = paintRect.width;
		int h = paintRect.height;

		if (this.slider.getOrientation() == SwingConstants.HORIZONTAL)
		{
			int middleOfThumb = this.thumbRect.x + this.thumbRect.width / 2 - paintRect.x;

			if (this.slider.isEnabled())
			{
				int fillMinX;
				int fillMaxX;

				if (middleOfThumb > 0)
				{
					g.setColor(drawInverted ? FamilyLookAndFeel.getControlDarkShadow() : FamilyLookAndFeel.getPrimaryControlDarkShadow());
					g.drawRect(0, 0, middleOfThumb - 1, h - 1);
				}

				if (middleOfThumb < w)
				{
					g.setColor(drawInverted ? FamilyLookAndFeel.getPrimaryControlDarkShadow() : FamilyLookAndFeel.getControlDarkShadow());
					g.drawRect(middleOfThumb, 0, w - middleOfThumb - 1, h - 1);
				}

				if (this.filledSlider)
				{
					g.setColor(FamilyLookAndFeel.getPrimaryControlShadow());
					if (drawInverted)
					{
						fillMinX = middleOfThumb;
						fillMaxX = w - 2;
						g.drawLine(1, 1, middleOfThumb, 1);
					}
					else
					{
						fillMinX = 1;
						fillMaxX = middleOfThumb;
						g.drawLine(middleOfThumb, 1, w - 1, 1);
					}
					if (h == 6)
					{
						g.setColor(FamilyLookAndFeel.getWhite());
						g.drawLine(fillMinX, 1, fillMaxX, 1);
						g.setColor(sliderAltTrackColor);
						g.drawLine(fillMinX, 2, fillMaxX, 2);
						g.setColor(FamilyLookAndFeel.getControlShadow());
						g.drawLine(fillMinX, 3, fillMaxX, 3);
						g.setColor(FamilyLookAndFeel.getPrimaryControlShadow());
						g.drawLine(fillMinX, 4, fillMaxX, 4);
					}
				}
			}
			else
			{
				g.setColor(FamilyLookAndFeel.getControlShadow());

				if (middleOfThumb > 0)
					if (!drawInverted && this.filledSlider)
						g.fillRect(0, 0, middleOfThumb - 1, h - 1);
					else
						g.drawRect(0, 0, middleOfThumb - 1, h - 1);
				if (middleOfThumb < w)
					if (drawInverted && this.filledSlider)
						g.fillRect(middleOfThumb, 0, w - middleOfThumb - 1, h - 1);
					else
						g.drawRect(middleOfThumb, 0, w - middleOfThumb - 1, h - 1);
			}
		}
		else
		{
			int middleOfThumb = this.thumbRect.y + (this.thumbRect.height / 2) - paintRect.y;

			if (this.slider.isEnabled())
			{
				int fillMinY;
				int fillMaxY;

				if (middleOfThumb > 0)
				{
					g.setColor(drawInverted ? FamilyLookAndFeel.getPrimaryControlDarkShadow() : FamilyLookAndFeel.getControlDarkShadow());
					g.drawRect(0, 0, w - 1, middleOfThumb - 1);
				}
				if (middleOfThumb < h)
				{
					g.setColor(drawInverted ? FamilyLookAndFeel.getControlDarkShadow() : FamilyLookAndFeel.getPrimaryControlDarkShadow());
					g.drawRect(0, middleOfThumb, w - 1, h - middleOfThumb - 1);
				}
				if (this.filledSlider)
				{
					g.setColor(FamilyLookAndFeel.getPrimaryControlShadow());
					if (drawInverted())
					{
						fillMinY = 1;
						fillMaxY = middleOfThumb;
						if (leftToRight)
							g.drawLine(1, middleOfThumb, 1, h - 1);
						else
							g.drawLine(w - 2, middleOfThumb, w - 2, h - 1);
					}
					else
					{
						fillMinY = middleOfThumb;
						fillMaxY = h - 2;
						if (leftToRight)
							g.drawLine(1, 1, 1, middleOfThumb);
						else
							g.drawLine(w - 2, 1, w - 2, middleOfThumb);
					}
					if (w == 6)
					{
						g.setColor(leftToRight ? FamilyLookAndFeel.getWhite() : FamilyLookAndFeel.getPrimaryControlShadow());
						g.drawLine(1, fillMinY, 1, fillMaxY);
						g.setColor(leftToRight ? sliderAltTrackColor : FamilyLookAndFeel.getControlShadow());
						g.drawLine(2, fillMinY, 2, fillMaxY);
						g.setColor(leftToRight ? FamilyLookAndFeel.getControlShadow() : sliderAltTrackColor);
						g.drawLine(3, fillMinY, 3, fillMaxY);
						g.setColor(leftToRight ? FamilyLookAndFeel.getPrimaryControlShadow() : FamilyLookAndFeel.getWhite());
						g.drawLine(4, fillMinY, 4, fillMaxY);
					}
				}
			}
			else
			{
				g.setColor(FamilyLookAndFeel.getControlShadow());

				if (middleOfThumb > 0)
					if (drawInverted && this.filledSlider)
						g.fillRect(0, 0, w - 1, middleOfThumb - 1);
					else
						g.drawRect(0, 0, w - 1, middleOfThumb - 1);
				if (middleOfThumb < h)
					if (!drawInverted && this.filledSlider)
						g.fillRect(0, middleOfThumb, w - 1, h - middleOfThumb - 1);
					else
						g.drawRect(0, middleOfThumb, w - 1, h - middleOfThumb - 1);
			}
		}

		g.translate(-paintRect.x, -paintRect.y);
	}

	@Override
	public void paintFocus(@SuppressWarnings("unused") Graphics g)
	{}

	@Override
	protected Dimension getThumbSize()
	{
		Dimension size = new Dimension();

		if (this.slider.getOrientation() == SwingConstants.VERTICAL)
		{
			size.width = getVertThumbIcon().getIconWidth();
			size.height = getVertThumbIcon().getIconHeight();
		}
		else
		{
			size.width = getHorizThumbIcon().getIconWidth();
			size.height = getHorizThumbIcon().getIconHeight();
		}

		return size;
	}

	/**
	 * Gets the height of the tick area for horizontal sliders and the width of
	 * the tick area for vertical sliders. BasicSliderUI uses the returned value
	 * to determine the tick area rectangle.
	 */
	@Override
	public int getTickLength()
	{
		return this.slider.getOrientation() == SwingConstants.HORIZONTAL ? this.safeLength + this.TICK_BUFFER + 1
		        : this.safeLength + this.TICK_BUFFER + 3;
	}

	/**
	 * Returns the shorter dimension of the track.
	 */
	@SuppressWarnings("unqualified-field-access")
	protected int getTrackWidth()
	{
		// This strange calculation is here to keep the
		// track in proportion to the thumb.
		final double kIdealTrackWidth = 7.0;
		final double kIdealThumbHeight = 16.0;
		final double kWidthScalar = kIdealTrackWidth / kIdealThumbHeight;

		if (this.slider.getOrientation() == SwingConstants.HORIZONTAL)
			return (int) (kWidthScalar * this.thumbRect.height);
		return (int) (kWidthScalar * thumbRect.width);
	}

	/**
	 * Returns the longer dimension of the slide bar. (The slide bar is only the
	 * part that runs directly under the thumb)
	 */
	protected int getTrackLength()
	{
		if (this.slider.getOrientation() == SwingConstants.HORIZONTAL)
			return this.trackRect.width;
		return this.trackRect.height;
	}

	/**
	 * Returns the amount that the thumb goes past the slide bar.
	 */
	protected int getThumbOverhang()
	{
		return (int) (getThumbSize().getHeight() - getTrackWidth()) / 2;
	}

	@Override
	protected void scrollDueToClickInTrack(int dir)
	{
		scrollByUnit(dir);
	}

	@Override
	protected void paintMinorTickForHorizSlider(Graphics g, @SuppressWarnings("unused") Rectangle tickBounds, int x)
	{
		g.setColor(this.slider.isEnabled() ? this.slider.getForeground() : FamilyLookAndFeel.getControlShadow());
		g.drawLine(x, this.TICK_BUFFER, x, this.TICK_BUFFER + (this.safeLength / 2));
	}

	@Override
	protected void paintMajorTickForHorizSlider(Graphics g, @SuppressWarnings("unused") Rectangle tickBounds, int x)
	{
		g.setColor(this.slider.isEnabled() ? this.slider.getForeground() : FamilyLookAndFeel.getControlShadow());
		g.drawLine(x, this.TICK_BUFFER, x, this.TICK_BUFFER + (this.safeLength - 1));
	}

	@Override
	protected void paintMinorTickForVertSlider(Graphics g, @SuppressWarnings("unused") Rectangle tickBounds, int y)
	{
		g.setColor(this.slider.isEnabled() ? this.slider.getForeground() : FamilyLookAndFeel.getControlShadow());

		if (FamilyUtils.isLeftToRight(this.slider))
			g.drawLine(this.TICK_BUFFER, y, this.TICK_BUFFER + (this.safeLength / 2), y);
		else
			g.drawLine(0, y, this.safeLength / 2, y);
	}

	@Override
	protected void paintMajorTickForVertSlider(Graphics g, @SuppressWarnings("unused") Rectangle tickBounds, int y)
	{
		g.setColor(this.slider.isEnabled() ? this.slider.getForeground() : FamilyLookAndFeel.getControlShadow());

		if (FamilyUtils.isLeftToRight(this.slider))
			g.drawLine(this.TICK_BUFFER, y, this.TICK_BUFFER + this.safeLength, y);
		else
			g.drawLine(0, y, this.safeLength, y);
	}
}
