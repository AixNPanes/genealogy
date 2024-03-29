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
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 * Family's split pane divider
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Steve Wilson
 * @author Ralph kar
 */
@SuppressWarnings("serial")
class FamilySplitPaneDivider extends BasicSplitPaneDivider
{
	private FamilyBumps bumps = new FamilyBumps(10, 10, FamilyLookAndFeel.getControlHighlight(),
	        FamilyLookAndFeel.getControlDarkShadow(), FamilyLookAndFeel.getControl());

	private FamilyBumps focusBumps = new FamilyBumps(10, 10, FamilyLookAndFeel.getPrimaryControlHighlight(),
	        FamilyLookAndFeel.getPrimaryControlDarkShadow(), UIManager.getColor("SplitPane.dividerFocusColor"));

	private int inset = 2;

	private Color controlColor = FamilyLookAndFeel.getControl();
	private Color primaryControlColor = UIManager.getColor("SplitPane.dividerFocusColor");

	public FamilySplitPaneDivider(BasicSplitPaneUI ui)
	{
		super(ui);
	}

	@Override
	public void paint(Graphics g)
	{
		FamilyBumps usedBumps;
		if (this.splitPane.hasFocus())
		{
			usedBumps = this.focusBumps;
			g.setColor(this.primaryControlColor);
		}
		else
		{
			usedBumps = this.bumps;
			g.setColor(this.controlColor);
		}
		Rectangle clip = g.getClipBounds();
		Insets insets = getInsets();
		g.fillRect(clip.x, clip.y, clip.width, clip.height);
		Dimension size = getSize();
		size.width -= this.inset * 2;
		size.height -= this.inset * 2;
		int drawX = this.inset;
		int drawY = this.inset;
		if (insets != null)
		{
			size.width -= (insets.left + insets.right);
			size.height -= (insets.top + insets.bottom);
			drawX += insets.left;
			drawY += insets.top;
		}
		usedBumps.setBumpArea(size);
		usedBumps.paintIcon(this, g, drawX, drawY);
		super.paint(g);
	}

	/**
	 * Creates and return an instance of JButton that can be used to collapse
	 * the left component in the family split pane.
	 */
	@Override
	protected JButton createLeftOneTouchButton()
	{
		JButton b = new JButton()
		{
			// Sprite buffer for the arrow image of the left button
			int[][] buffer = { { 0, 0, 0, 2, 2, 0, 0, 0, 0 }, { 0, 0, 2, 1, 1, 1, 0, 0, 0 },
		            { 0, 2, 1, 1, 1, 1, 1, 0, 0 }, { 2, 1, 1, 1, 1, 1, 1, 1, 0 }, { 0, 3, 3, 3, 3, 3, 3, 3, 3 } };

			@Override
			public void setBorder(@SuppressWarnings({ "unused", "hiding" }) Border b)
			{}

			@SuppressWarnings("unqualified-field-access")
			@Override
			public void paint(Graphics g)
			{
				@SuppressWarnings("hiding")
				JSplitPane splitPane = getSplitPaneFromSuper();
				if (splitPane != null)
				{
					int oneTouchSize = getOneTouchSizeFromSuper();
					@SuppressWarnings("hiding")
					int orientation = getOrientationFromSuper();
					int blockSize = Math.min(getDividerSize(), oneTouchSize);

					// Initialize the color array
					Color[] colors = { this.getBackground(), FamilyLookAndFeel.getPrimaryControlDarkShadow(),
		                    FamilyLookAndFeel.getPrimaryControlInfo(), FamilyLookAndFeel.getPrimaryControlHighlight() };

					// Fill the background first ...
					g.setColor(this.getBackground());
					if (isOpaque())
						g.fillRect(0, 0, this.getWidth(), this.getHeight());

					// ... then draw the arrow.
					if (getModel().isPressed())
						// Adjust color mapping for pressed button state
						colors[1] = colors[2];
					if (orientation == JSplitPane.VERTICAL_SPLIT)
						// Draw the image for a vertical split
						for (int i = 1; i <= this.buffer[0].length; i++)
							for (int j = 1; j < blockSize; j++)
							{
								if (this.buffer[j - 1][i - 1] == 0)
									continue;
								g.setColor(colors[buffer[j - 1][i - 1]]);
								g.drawLine(i, j, i, j);
							}
					else
						// Draw the image for a horizontal split
		                // by simply swaping the i and j axis.
		                // Except the drawLine() call this code is
		                // identical to the code block above. This was done
		                // in order to remove the additional orientation
		                // check for each pixel.
						for (int i = 1; i <= buffer[0].length; i++)
							for (int j = 1; j < blockSize; j++)
							{
								if (buffer[j - 1][i - 1] == 0)
									// Nothing needs
		                            // to be drawn
									continue;
								// Set the color from the
		                        // color map
								g.setColor(colors[buffer[j - 1][i - 1]]);
								// Draw a pixel
								g.drawLine(j, i, j, i);
							}
				}
			}

			// Don't want the button to participate in focus traversable.
			@Override
			public boolean isFocusTraversable()
			{
				return false;
			}
		};
		b.setRequestFocusEnabled(false);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		maybeMakeButtonOpaque(b);
		return b;
	}

	/**
	 * If necessary <code>c</code> is made opaque.
	 */
	private void maybeMakeButtonOpaque(JComponent c)
	{
		Object opaque = UIManager.get("SplitPane.oneTouchButtonsOpaque");
		if (opaque != null)
			c.setOpaque(((Boolean) opaque).booleanValue());
	}

	/**
	 * Creates and return an instance of JButton that can be used to collapse
	 * the right component in the family split pane.
	 */
	@Override
	protected JButton createRightOneTouchButton()
	{
		JButton b = new JButton()
		{
			// Sprite buffer for the arrow image of the right button
			int[][] buffer = { { 2, 2, 2, 2, 2, 2, 2, 2 }, { 0, 1, 1, 1, 1, 1, 1, 3 }, { 0, 0, 1, 1, 1, 1, 3, 0 },
		            { 0, 0, 0, 1, 1, 3, 0, 0 }, { 0, 0, 0, 0, 3, 0, 0, 0 } };

			@Override
			public void setBorder(@SuppressWarnings("unused") Border border)
			{}

			@SuppressWarnings("unqualified-field-access")
			@Override
			public void paint(Graphics g)
			{
				@SuppressWarnings("hiding")
				JSplitPane splitPane = getSplitPaneFromSuper();
				if (splitPane != null)
				{
					int oneTouchSize = getOneTouchSizeFromSuper();
					@SuppressWarnings("hiding")
					int orientation = getOrientationFromSuper();
					int blockSize = Math.min(getDividerSize(), oneTouchSize);

					// Initialize the color array
					Color[] colors = { this.getBackground(), FamilyLookAndFeel.getPrimaryControlDarkShadow(),
		                    FamilyLookAndFeel.getPrimaryControlInfo(), FamilyLookAndFeel.getPrimaryControlHighlight() };

					// Fill the background first ...
					g.setColor(this.getBackground());
					if (isOpaque())
						g.fillRect(0, 0, this.getWidth(), this.getHeight());

					// ... then draw the arrow.
					if (getModel().isPressed())
						// Adjust color mapping for pressed button state
						colors[1] = colors[2];
					if (orientation == JSplitPane.VERTICAL_SPLIT)
						// Draw the image for a vertical split
						for (int i = 1; i <= this.buffer[0].length; i++)
							for (int j = 1; j < blockSize; j++)
							{
								if (this.buffer[j - 1][i - 1] == 0)
									continue;
								g.setColor(colors[buffer[j - 1][i - 1]]);
								g.drawLine(i, j, i, j);
							}
					else
						// Draw the image for a horizontal split
		                // by simply swaping the i and j axis.
		                // Except the drawLine() call this code is
		                // identical to the code block above. This was done
		                // in order to remove the additional orientation
		                // check for each pixel.
						for (int i = 1; i <= buffer[0].length; i++)
							for (int j = 1; j < blockSize; j++)
							{
								if (buffer[j - 1][i - 1] == 0)
									// Nothing needs
		                            // to be drawn
									continue;
								// Set the color from the
		                        // color map
								g.setColor(colors[buffer[j - 1][i - 1]]);
								// Draw a pixel
								g.drawLine(j, i, j, i);
							}
				}
			}

			// Don't want the button to participate in focus traversable.
			@Override
			public boolean isFocusTraversable()
			{
				return false;
			}
		};
		b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		b.setFocusPainted(false);
		b.setBorderPainted(false);
		b.setRequestFocusEnabled(false);
		maybeMakeButtonOpaque(b);
		return b;
	}

	/**
	 * Used to layout a FamilySplitPaneDivider. Layout for the divider involves
	 * appropriately moving the left/right buttons around.
	 * <p>
	 * This class should be treated as a &quot;protected&quot; inner class.
	 * Instantiate it only within subclasses of FamilySplitPaneDivider.
	 */
	public class FamilyDividerLayout implements LayoutManager
	{

		// NOTE NOTE NOTE NOTE NOTE
		// This class is no longer used, the functionality has
		// been rolled into BasicSplitPaneDivider.DividerLayout as a
		// defaults property

		@Override
		public void layoutContainer(Container c)
		{
			@SuppressWarnings("hiding")
			JButton leftButton = getLeftButtonFromSuper();
			@SuppressWarnings("hiding")
			JButton rightButton = getRightButtonFromSuper();
			@SuppressWarnings("hiding")
			JSplitPane splitPane = getSplitPaneFromSuper();
			@SuppressWarnings("hiding")
			int orientation = getOrientationFromSuper();
			int oneTouchSize = getOneTouchSizeFromSuper();
			int oneTouchOffset = getOneTouchOffsetFromSuper();
			Insets insets = getInsets();

			// This layout differs from the one used in BasicSplitPaneDivider.
			// It does not center justify the oneTouchExpadable buttons.
			// This was necessary in order to meet the spec of the Family
			// splitpane divider.
			if (leftButton != null && rightButton != null && c == FamilySplitPaneDivider.this)
			{
				if (splitPane.isOneTouchExpandable())
					if (orientation == JSplitPane.VERTICAL_SPLIT)
					{
						int extraY = (insets != null) ? insets.top : 0;
						int blockSize = getDividerSize();

						if (insets != null)
							blockSize -= (insets.top + insets.bottom);
						blockSize = Math.min(blockSize, oneTouchSize);
						leftButton.setBounds(oneTouchOffset, extraY, blockSize * 2, blockSize);
						rightButton.setBounds(oneTouchOffset + oneTouchSize * 2, extraY, blockSize * 2, blockSize);
					}
					else
					{
						int blockSize = getDividerSize();
						int extraX = (insets != null) ? insets.left : 0;

						if (insets != null)
							blockSize -= (insets.left + insets.right);
						blockSize = Math.min(blockSize, oneTouchSize);
						leftButton.setBounds(extraX, oneTouchOffset, blockSize, blockSize * 2);
						rightButton.setBounds(extraX, oneTouchOffset + oneTouchSize * 2, blockSize, blockSize * 2);
					}
				else
				{
					leftButton.setBounds(-5, -5, 1, 1);
					rightButton.setBounds(-5, -5, 1, 1);
				}
			}
		}

		@Override
		public Dimension minimumLayoutSize(@SuppressWarnings("unused") Container c)
		{
			return new Dimension(0, 0);
		}

		@Override
		public Dimension preferredLayoutSize(@SuppressWarnings("unused") Container c)
		{
			return new Dimension(0, 0);
		}

		@Override
		public void removeLayoutComponent(@SuppressWarnings("unused") Component c)
		{}

		@Override
		public void addLayoutComponent(@SuppressWarnings("unused") String string, @SuppressWarnings("unused") Component c)
		{}
	}

	/*
	 * The following methods only exist in order to be able to access protected
	 * members in the superclass, because these are otherwise not available in
	 * any inner class.
	 */

	@SuppressWarnings("static-access")
	int getOneTouchSizeFromSuper()
	{
		return super.ONE_TOUCH_SIZE;
	}

	@SuppressWarnings("static-access")
	int getOneTouchOffsetFromSuper()
	{
		return super.ONE_TOUCH_OFFSET;
	}

	int getOrientationFromSuper()
	{
		return super.orientation;
	}

	JSplitPane getSplitPaneFromSuper()
	{
		return super.splitPane;
	}

	JButton getLeftButtonFromSuper()
	{
		return super.leftButton;
	}

	JButton getRightButtonFromSuper()
	{
		return super.rightButton;
	}
}
