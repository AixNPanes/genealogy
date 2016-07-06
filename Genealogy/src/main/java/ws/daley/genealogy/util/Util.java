package ws.daley.genealogy.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Util
{
	public static Rectangle2D.Float SMALL_RECTANGLE = new Rectangle2D.Float(0, 0, 0, 0);
	public static Dimension SMALL_DIMENSION = new Dimension((int)Math.ceil(SMALL_RECTANGLE.getWidth()), (int)Math.ceil(SMALL_RECTANGLE.getHeight()));

	public static void setSize(Component c, Dimension d)
	{
		c.setSize(d);
		c.setMinimumSize(d);
		c.setPreferredSize(d);
		c.setMaximumSize(d);
	}

	public static Dimension getSize(Container container)
	{
		Dimension d = new Dimension(0, 0);
		for(Component component:container.getComponents())
		{
			Rectangle newRect = new Rectangle(component.getLocation(), component.getSize());
			newRect.width += newRect.x;
			newRect.height += newRect.y;
			d.width = Math.max(d.width, newRect.width);
			d.height = Math.max(d.height, newRect.width);
		}
		return d;
	}

	public static void setSize(Container c)
	{
		setSize(c, getSize(c));
	}

	public static int getMax(Integer... integers)
	{
		int i = 0;
		for(Integer integer:integers)
			i = Math.max(i, integer);
		return i;
	}

	public static Dimension getMaxSize(Component...components)
	{
		Dimension d = new Dimension(0, 0);
		for(Component c:components)
			d.setSize(Math.max(d.getWidth(),c.getWidth()), Math.max(d.getHeight(), c.getHeight()));
		return d;
	}

	public static BufferedImage scaleImage(BufferedImage image)
	{
		BufferedImage bufferedImage = new BufferedImage((image.getWidth() + 1) / 2, (image.getHeight() + 1) / 2, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.createGraphics();
		g.drawImage(image, 0, 0, (image.getWidth() + 1) / 2, (image.getHeight() + 1) / 2, null);
		g.dispose();
		return bufferedImage;
	}
}
