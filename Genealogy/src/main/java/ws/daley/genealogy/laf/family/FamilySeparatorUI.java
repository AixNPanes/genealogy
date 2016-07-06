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

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;

/**
 * A Family L&amp;F implementation of SeparatorUI. This implementation is a
 * "combined" view/controller.
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Jeff Shapiro
 */

public class FamilySeparatorUI extends BasicSeparatorUI
{
	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		return new FamilySeparatorUI();
	}

	@Override
	protected void installDefaults(JSeparator s)
	{
		LookAndFeel.installColors(s, "Separator.background", "Separator.foreground");
	}

	@Override
	public void paint(Graphics g, JComponent c)
	{
		Dimension s = c.getSize();

		if (((JSeparator) c).getOrientation() == SwingConstants.VERTICAL)
		{
			g.setColor(c.getForeground());
			g.drawLine(0, 0, 0, s.height);

			g.setColor(c.getBackground());
			g.drawLine(1, 0, 1, s.height);
		}
		else // HORIZONTAL
		{
			g.setColor(c.getForeground());
			g.drawLine(0, 0, s.width, 0);

			g.setColor(c.getBackground());
			g.drawLine(0, 1, s.width, 1);
		}
	}

	@Override
	public Dimension getPreferredSize(JComponent c)
	{
		if (((JSeparator) c).getOrientation() == SwingConstants.VERTICAL)
			return new Dimension(2, 0);
		return new Dimension(0, 2);
	}
}
