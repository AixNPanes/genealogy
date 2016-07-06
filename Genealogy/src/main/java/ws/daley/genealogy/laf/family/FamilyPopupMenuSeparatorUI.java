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
import javax.swing.plaf.ComponentUI;

/**
 * A Family L&amp;F implementation of PopupMenuSeparatorUI. This implementation
 * is a "combined" view/controller.
 *
 * @author Jeff Shapiro
 */

public class FamilyPopupMenuSeparatorUI extends FamilySeparatorUI
{
	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		return new FamilyPopupMenuSeparatorUI();
	}

	@Override
	public void paint(Graphics g, JComponent c)
	{
		Dimension s = c.getSize();

		g.setColor(c.getForeground());
		g.drawLine(0, 1, s.width, 1);

		g.setColor(c.getBackground());
		g.drawLine(0, 2, s.width, 2);
		g.drawLine(0, 0, 0, 0);
		g.drawLine(0, 3, 0, 3);
	}

	@Override
	public Dimension getPreferredSize(@SuppressWarnings("unused") JComponent c)
	{
		return new Dimension(0, 4);
	}
}
