/*
 * Copyright (c) 1998, 2009, Oracle and/or its affiliates. All rights reserved.
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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import sun.awt.AppContext;

/**
 * Implements the bumps used throughout the Family Look and Feel.
 *
 * @author Tom Santos
 * @author Steve Wilson
 */

class FamilyBumps implements Icon
{

	static final Color ALPHA = new Color(0, 0, 0, 0);

	protected int xBumps;
	protected int yBumps;
	protected Color topColor;
	protected Color shadowColor;
	protected Color backColor;

	private static final Object fAMILY_BUMPS = new Object();
	protected BumpBuffer buffer;

	/**
	 * Creates FamilyBumps of the specified size with the specified colors. If
	 * <code>newBackColor</code> is null, the background will be transparent.
	 */
	public FamilyBumps(int width, int height, Color newTopColor, Color newShadowColor, Color newBackColor)
	{
		setBumpArea(width, height);
		setBumpColors(newTopColor, newShadowColor, newBackColor);
	}

	private static BumpBuffer createBuffer(GraphicsConfiguration gc, Color topColor, Color shadowColor, Color backColor)
	{
		AppContext context = AppContext.getAppContext();
		@SuppressWarnings("unchecked")
		List<BumpBuffer> buffers = (List<BumpBuffer>) context.get(fAMILY_BUMPS);
		if (buffers == null)
		{
			buffers = new ArrayList<BumpBuffer>();
			context.put(fAMILY_BUMPS, buffers);
		}
		for (BumpBuffer buffer: buffers)
			if (buffer.hasSameConfiguration(gc, topColor, shadowColor, backColor))
				return buffer; 
		BumpBuffer buffer = new BumpBuffer(gc, topColor, shadowColor, backColor);
		buffers.add(buffer);
		return buffer;
	}

	public void setBumpArea(Dimension bumpArea)
	{
		setBumpArea(bumpArea.width, bumpArea.height);
	}

	public void setBumpArea(int width, int height)
	{
		this.xBumps = width / 2;
		this.yBumps = height / 2;
	}

	public void setBumpColors(Color newTopColor, Color newShadowColor,
	        Color newBackColor)
	{
		this.topColor = newTopColor;
		this.shadowColor = newShadowColor;
		if (newBackColor == null)
			this.backColor = ALPHA;
		else
			this.backColor = newBackColor;
	}

	@Override
	public void paintIcon(@SuppressWarnings("unused") Component c, Graphics g, int x, int y)
	{
		GraphicsConfiguration gc = (g instanceof Graphics2D) ? ((Graphics2D) g).getDeviceConfiguration() : null;

		if ((this.buffer == null) || !this.buffer.hasSameConfiguration(gc, this.topColor, this.shadowColor, this.backColor))
			this.buffer = createBuffer(gc, this.topColor, this.shadowColor, this.backColor);

		int bufferWidth = BumpBuffer.IMAGE_SIZE;
		int bufferHeight = BumpBuffer.IMAGE_SIZE;
		int iconWidth = getIconWidth();
		int iconHeight = getIconHeight();
		int x2 = x + iconWidth;
		int y2 = y + iconHeight;
		int savex = x;

		while (y < y2)
		{
			int h = Math.min(y2 - y, bufferHeight);
			for (x = savex; x < x2; x += bufferWidth)
			{
				int w = Math.min(x2 - x, bufferWidth);
				g.drawImage(this.buffer.getImage(), x, y, x + w, y + h, 0, 0, w, h, null);
			}
			y += bufferHeight;
		}
	}

	@Override
	public int getIconWidth()
	{
		return this.xBumps * 2;
	}

	@Override
	public int getIconHeight()
	{
		return this.yBumps * 2;
	}
}

class BumpBuffer
{

	static final int IMAGE_SIZE = 64;

	transient Image image;
	Color topColor;
	Color shadowColor;
	Color backColor;
	private GraphicsConfiguration gc;

	public BumpBuffer(GraphicsConfiguration gc, Color aTopColor, Color aShadowColor, Color aBackColor)
	{
		this.gc = gc;
		this.topColor = aTopColor;
		this.shadowColor = aShadowColor;
		this.backColor = aBackColor;
		createImage();
		fillBumpBuffer();
	}

	public boolean hasSameConfiguration(@SuppressWarnings("hiding") GraphicsConfiguration gc, Color aTopColor, Color aShadowColor, Color aBackColor)
	{
		if (this.gc != null)
		{
			if (!this.gc.equals(gc))
				return false;
		}
		else if (gc != null)
			return false;
		return this.topColor.equals(aTopColor) && this.shadowColor.equals(aShadowColor) && this.backColor.equals(aBackColor);
	}

	/**
	 * Returns the Image containing the bumps appropriate for the passed in
	 * <code>GraphicsConfiguration</code>.
	 */
	public Image getImage()
	{
		return this.image;
	}

	/**
	 * Paints the bumps into the current image.
	 */
	private void fillBumpBuffer()
	{
		Graphics g = this.image.getGraphics();

		g.setColor(this.backColor);
		g.fillRect(0, 0, IMAGE_SIZE, IMAGE_SIZE);

		g.setColor(this.topColor);
		for (int x = 0; x < IMAGE_SIZE; x += 4)
			for (int y = 0; y < IMAGE_SIZE; y += 4)
			{
				g.drawLine(x, y, x, y);
				g.drawLine(x + 2, y + 2, x + 2, y + 2);
			}

		g.setColor(this.shadowColor);
		for (int x = 0; x < IMAGE_SIZE; x += 4)
			for (int y = 0; y < IMAGE_SIZE; y += 4)
			{
				g.drawLine(x + 1, y + 1, x + 1, y + 1);
				g.drawLine(x + 3, y + 3, x + 3, y + 3);
			}
		g.dispose();
	}

	/**
	 * Creates the image appropriate for the passed in
	 * <code>GraphicsConfiguration</code>, which may be null.
	 */
	private void createImage()
	{
		if (this.gc != null)
			this.image = this.gc.createCompatibleImage(IMAGE_SIZE, IMAGE_SIZE, (this.backColor != FamilyBumps.ALPHA)
					? Transparency.OPAQUE
			        : Transparency.BITMASK);
		else
		{
			int cmap[] = { this.backColor.getRGB(), this.topColor.getRGB(), this.shadowColor.getRGB() };
			IndexColorModel icm = new IndexColorModel(8, 3, cmap, 0, false, (this.backColor == FamilyBumps.ALPHA) ? 0 : -1, DataBuffer.TYPE_BYTE);
			this.image = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_BYTE_INDEXED, icm);
		}
	}
}
