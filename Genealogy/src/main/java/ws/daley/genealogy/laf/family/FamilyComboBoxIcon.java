/*
 * Copyright (c) 1998, 2000, Oracle and/or its affiliates. All rights reserved.
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
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.JComponent;

/**
 * This utility class draws the horizontal bars which indicate a FamilyComboBox
 *
 * @see FamilyComboBoxUI
 * @author Tom Santos
 */
@SuppressWarnings("serial")
public class FamilyComboBoxIcon implements Icon, Serializable
{
	/**
	 * Paints the horizontal bars for the
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		JComponent component = (JComponent) c;
		int iconWidth = getIconWidth();

		g.translate(x, y);

		g.setColor(component.isEnabled() ? FamilyLookAndFeel.getControlInfo() : FamilyLookAndFeel.getControlShadow());
		g.drawLine(0, 0, iconWidth - 1, 0);
		g.drawLine(1, 1, 1 + (iconWidth - 3), 1);
		g.drawLine(2, 2, 2 + (iconWidth - 5), 2);
		g.drawLine(3, 3, 3 + (iconWidth - 7), 3);
		g.drawLine(4, 4, 4 + (iconWidth - 9), 4);

		g.translate(-x, -y);
	}

	/**
	 * Created a stub to satisfy the interface.
	 */
	@Override
	public int getIconWidth()
	{
		return 10;
	}

	/**
	 * Created a stub to satisfy the interface.
	 */
	@Override
	public int getIconHeight()
	{
		return 5;
	}
}
