/*
 * Copyright (c) 2003, 2005, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 * Family implementation of <code>MenuBarUI</code>. This class is responsible
 * for providing the family look and feel for <code>JMenuBar</code>s.
 *
 * @see javax.swing.plaf.MenuBarUI
 * @since 1.5
 */
public class FamilyMenuBarUI extends BasicMenuBarUI
{
	/**
	 * Creates the <code>ComponentUI</code> implementation for the passed in
	 * component.
	 *
	 * @param x
	 *            JComponent to create the ComponentUI implementation for
	 * @return ComponentUI implementation for <code>x</code>
	 * @throws NullPointerException
	 *             if <code>x</code> is null
	 */
	public static ComponentUI createUI(JComponent x)
	{
		if (x == null)
			throw new NullPointerException("Must pass in a non-null component");
		return new FamilyMenuBarUI();
	}

	/**
	 * Configures the specified component appropriate for the family look and
	 * feel.
	 *
	 * @param c
	 *            the component where this UI delegate is being installed
	 * @throws NullPointerException
	 *             if <code>c</code> is null.
	 */
	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);
		FamilyToolBarUI.register(c);
	}

	/**
	 * Reverses configuration which was done on the specified component during
	 * <code>installUI</code>.
	 *
	 * @param c
	 *            the component where this UI delegate is being installed
	 * @throws NullPointerException
	 *             if <code>c</code> is null.
	 */
	@Override
	public void uninstallUI(JComponent c)
	{
		super.uninstallUI(c);
		FamilyToolBarUI.unregister(c);
	}

	/**
	 * If necessary paints the background of the component, then invokes
	 * <code>paint</code>.
	 *
	 * @param g
	 *            Graphics to paint to
	 * @param c
	 *            JComponent painting on
	 * @throws NullPointerException
	 *             if <code>g</code> or <code>c</code> is null
	 * @see javax.swing.plaf.ComponentUI#update
	 * @see javax.swing.plaf.ComponentUI#paint
	 * @since 1.5
	 */
	@Override
	public void update(Graphics g, JComponent c)
	{
		boolean isOpaque = c.isOpaque();
		if (g == null)
			throw new NullPointerException("Graphics must be non-null");
		if (isOpaque && (c.getBackground() instanceof UIResource) && UIManager.get("MenuBar.gradient") != null)
		{
			if (FamilyToolBarUI.doesMenuBarBorderToolBar((JMenuBar) c))
			{
				JToolBar tb = (JToolBar) FamilyToolBarUI.findRegisteredComponentOfType(c, JToolBar.class);
				if (tb.isOpaque() && tb.getBackground() instanceof UIResource)
				{
					FamilyUtils.drawGradient(c, g, "MenuBar.gradient", 0, 0, c.getWidth(),
					        c.getHeight() + tb.getHeight(), true);
					paint(g, c);
					return;
				}
			}
			FamilyUtils.drawGradient(c, g, "MenuBar.gradient", 0, 0, c.getWidth(), c.getHeight(), true);
			paint(g, c);
		}
		else
			super.update(g, c);
	}
}
