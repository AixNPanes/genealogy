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

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

import sun.awt.AppContext;
import sun.swing.SwingUtilities2;

/**
 * A Windows L&amp;F implementation of LabelUI. This implementation is
 * completely static, i.e. there's only one UIView implementation that's shared
 * by all JLabel objects.
 *
 * @author Hans Muller
 */

public class FamilyLabelUI extends BasicLabelUI
{
	/**
	 * The default <code>FamilyLabelUI</code> instance. This field might not be
	 * used. To change the default instance use a subclass which overrides the
	 * <code>createUI</code> method, and place that class name in defaults table
	 * under the key "LabelUI".
	 */
	protected static FamilyLabelUI familyLabelUI = new FamilyLabelUI();

	private static final Object FAMILY_LABEL_UI_KEY = new Object();

	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		if (System.getSecurityManager() != null)
		{
			AppContext appContext = AppContext.getAppContext();
			FamilyLabelUI safeFamilyLabelUI = (FamilyLabelUI) appContext.get(FAMILY_LABEL_UI_KEY);
			if (safeFamilyLabelUI == null)
			{
				safeFamilyLabelUI = new FamilyLabelUI();
				appContext.put(FAMILY_LABEL_UI_KEY, safeFamilyLabelUI);
			}
			return safeFamilyLabelUI;
		}
		return familyLabelUI;
	}

	/**
	 * Just paint the text gray (Label.disabledForeground) rather than in the
	 * labels foreground color.
	 *
	 * @see #paint
	 * @see #paintEnabledText
	 */
	@Override
	protected void paintDisabledText(JLabel l, Graphics g, String s, int textX, int textY)
	{
		int mnemIndex = l.getDisplayedMnemonicIndex();
		g.setColor(UIManager.getColor("Label.disabledForeground"));
		SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemIndex, textX, textY);
	}
}
