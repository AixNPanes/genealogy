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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicToggleButtonUI;

import sun.awt.AppContext;
import sun.swing.SwingUtilities2;

/**
 * FamilyToggleButton implementation
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Tom Santos
 */
public class FamilyToggleButtonUI extends BasicToggleButtonUI
{

	private static final Object FAMILY_TOGGLE_BUTTON_UI_KEY = new Object();

	protected Color focusColor;
	protected Color selectColor;
	protected Color disabledTextColor;

	private boolean defaults_initialized = false;

	// ********************************
	// Create PLAF
	// ********************************
	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent b)
	{
		AppContext appContext = AppContext.getAppContext();
		FamilyToggleButtonUI familyToggleButtonUI = (FamilyToggleButtonUI) appContext.get(FAMILY_TOGGLE_BUTTON_UI_KEY);
		if (familyToggleButtonUI == null)
		{
			familyToggleButtonUI = new FamilyToggleButtonUI();
			appContext.put(FAMILY_TOGGLE_BUTTON_UI_KEY, familyToggleButtonUI);
		}
		return familyToggleButtonUI;
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
		AbstractButton button = (AbstractButton) c;
		if ((c.getBackground() instanceof UIResource) && button.isContentAreaFilled() && c.isEnabled())
		{
			ButtonModel model = button.getModel();
			if (!FamilyUtils.isToolBarButton(c))
			{
				if (!model.isArmed() && !model.isPressed() && FamilyUtils.drawGradient(c, g, "ToggleButton.gradient", 0,
				        0, c.getWidth(), c.getHeight(), true))
				{
					paint(g, c);
					return;
				}
			}
			else if ((model.isRollover() || model.isSelected())
			        && FamilyUtils.drawGradient(c, g, "ToggleButton.gradient", 0, 0, c.getWidth(), c.getHeight(), true))
			{
				paint(g, c);
				return;
			}
		}
		super.update(g, c);
	}

	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b)
	{
		if (b.isContentAreaFilled())
		{
			g.setColor(getSelectColor());
			g.fillRect(0, 0, b.getWidth(), b.getHeight());
		}
	}

	@Override
	protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text)
	{
		AbstractButton b = (AbstractButton) c;
		ButtonModel model = b.getModel();
		FontMetrics fm = SwingUtilities2.getFontMetrics(b, g);
		int mnemIndex = b.getDisplayedMnemonicIndex();

		/* Draw the Text */
		if (model.isEnabled())
			/*** paint the text normally */
			g.setColor(b.getForeground());
		else
			/*** paint the text disabled ***/
			if (model.isSelected())
				g.setColor(c.getBackground());
			else
				g.setColor(getDisabledTextColor());
		SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemIndex, textRect.x, textRect.y + fm.getAscent());
	}

	@Override
	protected void paintFocus(Graphics g, AbstractButton b, @SuppressWarnings("unused") Rectangle viewRect,
	        Rectangle textRect, Rectangle iconRect)
	{

		Rectangle focusRect = new Rectangle();
		String text = b.getText();
		boolean isIcon = b.getIcon() != null;

		// If there is text
		if (text != null && !text.equals(""))
			if (!isIcon)
				focusRect.setBounds(textRect);
			else
				focusRect.setBounds(iconRect.union(textRect));
		// If there is an icon and no text
		else if (isIcon)
			focusRect.setBounds(iconRect);

		g.setColor(getFocusColor());
		g.drawRect((focusRect.x - 1), (focusRect.y - 1), focusRect.width + 1, focusRect.height + 1);

	}

	/**
	 * Paints the appropriate icon of the button <code>b</code> in the space
	 * <code>iconRect</code>.
	 *
	 * @param g
	 *            Graphics to paint to
	 * @param b
	 *            Button to render for
	 * @param iconRect
	 *            space to render in
	 * @throws NullPointerException
	 *             if any of the arguments are null.
	 * @since 1.5
	 */
	@Override
	protected void paintIcon(Graphics g, AbstractButton b, Rectangle iconRect)
	{
		super.paintIcon(g, b, iconRect);
	}
}
