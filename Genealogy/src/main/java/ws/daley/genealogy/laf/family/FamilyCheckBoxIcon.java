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

import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.plaf.UIResource;

/**
 * CheckboxIcon implementation for OrganicCheckBoxUI
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Steve Wilson
 */
@SuppressWarnings("serial")
public class FamilyCheckBoxIcon implements Icon, UIResource, Serializable
{

	protected int getControlSize()
	{
		return 13;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		JCheckBox cb = (JCheckBox) c;
		ButtonModel model = cb.getModel();
		int controlSize = getControlSize();

		boolean drawCheck = model.isSelected();

		if (model.isEnabled())
		{
			if (cb.isBorderPaintedFlat())
			{
				g.setColor(FamilyLookAndFeel.getControlDarkShadow());
				g.drawRect(x + 1, y, controlSize - 1, controlSize - 1);
			}
			if (model.isPressed() && model.isArmed())
			{
				if (cb.isBorderPaintedFlat())
				{
					g.setColor(FamilyLookAndFeel.getControlShadow());
					g.fillRect(x + 2, y + 1, controlSize - 2, controlSize - 2);
				}
				else
				{
					g.setColor(FamilyLookAndFeel.getControlShadow());
					g.fillRect(x, y, controlSize - 1, controlSize - 1);
					FamilyUtils.drawPressed3DBorder(g, x, y, controlSize, controlSize);
				}
			}
			else if (!cb.isBorderPaintedFlat())
				FamilyUtils.drawFlush3DBorder(g, x, y, controlSize, controlSize);
			g.setColor(FamilyLookAndFeel.getControlInfo());
		}
		else
		{
			g.setColor(FamilyLookAndFeel.getControlShadow());
			g.drawRect(x, y, controlSize - 1, controlSize - 1);
		}

		if (drawCheck)
		{
			if (cb.isBorderPaintedFlat())
				x++;
			drawCheck(c, g, x, y);
		}
	}

	protected void drawCheck(@SuppressWarnings("unused") Component c, Graphics g, int x, int y)
	{
		int controlSize = getControlSize();
		g.fillRect(x + 3, y + 5, 2, controlSize - 8);
		g.drawLine(x + (controlSize - 4), y + 3, x + 5, y + (controlSize - 6));
		g.drawLine(x + (controlSize - 4), y + 4, x + 5, y + (controlSize - 5));
	}

	@Override
	public int getIconWidth()
	{
		return getControlSize();
	}

	@Override
	public int getIconHeight()
	{
		return getControlSize();
	}
}
