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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import javax.swing.text.View;

import sun.awt.AppContext;
import sun.swing.SwingUtilities2;

/**
 * RadioButtonUI implementation for FamilyRadioButtonUI
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Michael C. Albers (Family modifications)
 * @author Jeff Dinkins (original BasicRadioButtonCode)
 */
public class FamilyRadioButtonUI extends BasicRadioButtonUI
{

	private static final Object FAMILY_RADIO_BUTTON_UI_KEY = new Object();

	protected Color focusColor;
	protected Color selectColor;
	protected Color disabledTextColor;

	private boolean defaults_initialized = false;

	// ********************************
	// Create PlAF
	// ********************************
	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		AppContext appContext = AppContext.getAppContext();
		FamilyRadioButtonUI familyRadioButtonUI = (FamilyRadioButtonUI) appContext.get(FAMILY_RADIO_BUTTON_UI_KEY);
		if (familyRadioButtonUI == null)
		{
			familyRadioButtonUI = new FamilyRadioButtonUI();
			appContext.put(FAMILY_RADIO_BUTTON_UI_KEY, familyRadioButtonUI);
		}
		return familyRadioButtonUI;
	}

	// ********************************
	// Install Defaults
	// ********************************
	@Override
	public void installDefaults(AbstractButton b)
	{
		super.installDefaults(b);
		if (!this.defaults_initialized)
		{
			this.focusColor = UIManager.getColor(getPropertyPrefix() + "focus");
			this.selectColor = UIManager.getColor(getPropertyPrefix() + "select");
			this.disabledTextColor = UIManager.getColor(getPropertyPrefix() + "disabledText");
			this.defaults_initialized = true;
		}
		LookAndFeel.installProperty(b, "opaque", Boolean.TRUE);
	}

	@Override
	protected void uninstallDefaults(AbstractButton b)
	{
		super.uninstallDefaults(b);
		this.defaults_initialized = false;
	}

	// ********************************
	// Default Accessors
	// ********************************
	protected Color getSelectColor()
	{
		return this.selectColor;
	}

	protected Color getDisabledTextColor()
	{
		return this.disabledTextColor;
	}

	protected Color getFocusColor()
	{
		return this.focusColor;
	}

	// ********************************
	// Paint Methods
	// ********************************
	@Override
	public synchronized void paint(Graphics g, JComponent c)
	{

		AbstractButton b = (AbstractButton) c;
		ButtonModel model = b.getModel();

		Dimension size = c.getSize();

		@SuppressWarnings("unused")
		int w = size.width;
		@SuppressWarnings("unused")
		int h = size.height;

		Font f = c.getFont();
		g.setFont(f);
		FontMetrics fm = SwingUtilities2.getFontMetrics(c, g, f);

		Rectangle viewRect = new Rectangle(size);
		Rectangle iconRect = new Rectangle();
		Rectangle textRect = new Rectangle();

		Insets i = c.getInsets();
		viewRect.x += i.left;
		viewRect.y += i.top;
		viewRect.width -= (i.right + viewRect.x);
		viewRect.height -= (i.bottom + viewRect.y);

		Icon altIcon = b.getIcon();
		@SuppressWarnings("unused")
		Icon selectedIcon = null;
		@SuppressWarnings("unused")
		Icon disabledIcon = null;

		String text = SwingUtilities.layoutCompoundLabel(c, fm, b.getText(),
		        altIcon != null ? altIcon : getDefaultIcon(), b.getVerticalAlignment(), b.getHorizontalAlignment(),
		        b.getVerticalTextPosition(), b.getHorizontalTextPosition(), viewRect, iconRect, textRect,
		        b.getIconTextGap());

		// fill background
		if (c.isOpaque())
		{
			g.setColor(b.getBackground());
			g.fillRect(0, 0, size.width, size.height);
		}

		// Paint the radio button
		if (altIcon != null)
		{

			if (!model.isEnabled())
				if (model.isSelected())
					altIcon = b.getDisabledSelectedIcon();
				else
					altIcon = b.getDisabledIcon();
			else if (model.isPressed() && model.isArmed())
			{
				altIcon = b.getPressedIcon();
				if (altIcon == null)
					// Use selected icon
					altIcon = b.getSelectedIcon();
			}
			else if (model.isSelected())
			{
				if (b.isRolloverEnabled() && model.isRollover())
				{
					altIcon = b.getRolloverSelectedIcon();
					if (altIcon == null)
						altIcon = b.getSelectedIcon();
				}
				else
					altIcon = b.getSelectedIcon();
			}
			else if (b.isRolloverEnabled() && model.isRollover())
				altIcon = b.getRolloverIcon();

			if (altIcon == null)
				altIcon = b.getIcon();

			altIcon.paintIcon(c, g, iconRect.x, iconRect.y);

		}
		else
		{
			getDefaultIcon().paintIcon(c, g, iconRect.x, iconRect.y);
		}

		// Draw the Text
		if (text != null)
		{
			View v = (View) c.getClientProperty(BasicHTML.propertyKey);
			if (v != null)
				v.paint(g, textRect);
			else
			{
				int mnemIndex = b.getDisplayedMnemonicIndex();
				if (model.isEnabled())
					// *** paint the text normally
					g.setColor(b.getForeground());
				else
					// *** paint the text disabled
					g.setColor(getDisabledTextColor());
				SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemIndex, textRect.x,
				        textRect.y + fm.getAscent());
			}
			if (b.hasFocus() && b.isFocusPainted() && textRect.width > 0 && textRect.height > 0)
				paintFocus(g, textRect, size);
		}
	}

	@Override
	protected void paintFocus(Graphics g, Rectangle t, @SuppressWarnings("unused") Dimension d)
	{
		g.setColor(getFocusColor());
		g.drawRect(t.x - 1, t.y - 1, t.width + 1, t.height + 1);
	}
}
