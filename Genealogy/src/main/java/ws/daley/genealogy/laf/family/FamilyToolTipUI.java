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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicToolTipUI;
import javax.swing.text.View;

import sun.swing.SwingUtilities2;

/**
 * A Family L&amp;F extension of BasicToolTipUI.
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
public class FamilyToolTipUI extends BasicToolTipUI
{

	static FamilyToolTipUI sharedInstance = new FamilyToolTipUI();
	private Font smallFont;
	// Refer to note in getAcceleratorString about this field.
	private JToolTip tip;
	public static final int padSpaceBetweenStrings = 12;
	private String acceleratorDelimiter;

	public FamilyToolTipUI()
	{
		super();
	}

	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		return sharedInstance;
	}

	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);
		this.tip = (JToolTip) c;
		Font f = c.getFont();
		this.smallFont = new Font(f.getName(), f.getStyle(), f.getSize() - 2);
		this.acceleratorDelimiter = UIManager.getString("MenuItem.acceleratorDelimiter");
		if (this.acceleratorDelimiter == null)
			this.acceleratorDelimiter = "-";
	}

	@Override
	public void uninstallUI(JComponent c)
	{
		super.uninstallUI(c);
		this.tip = null;
	}

	@Override
	public void paint(Graphics g, JComponent c)
	{
		@SuppressWarnings("hiding")
		JToolTip tip = (JToolTip) c;
		Font font = c.getFont();
		FontMetrics metrics = SwingUtilities2.getFontMetrics(c, g, font);
		Dimension size = c.getSize();
		int accelBL;

		g.setColor(c.getForeground());
		// fix for bug 4153892
		String tipText = tip.getTipText();
		if (tipText == null)
			tipText = "";

		String accelString = getAcceleratorString(tip);
		FontMetrics accelMetrics = SwingUtilities2.getFontMetrics(c, g, this.smallFont);
		int accelSpacing = calcAccelSpacing(c, accelMetrics, accelString);

		Insets insets = tip.getInsets();
		Rectangle paintTextR = new Rectangle(insets.left + 3, insets.top,
		        size.width - (insets.left + insets.right) - 6 - accelSpacing,
		        size.height - (insets.top + insets.bottom));
		View v = (View) c.getClientProperty(BasicHTML.propertyKey);
		if (v != null)
		{
			v.paint(g, paintTextR);
			accelBL = BasicHTML.getHTMLBaseline(v, paintTextR.width, paintTextR.height);
		}
		else
		{
			g.setFont(font);
			SwingUtilities2.drawString(tip, g, tipText, paintTextR.x, paintTextR.y + metrics.getAscent());
			accelBL = metrics.getAscent();
		}

		if (!accelString.equals(""))
		{
			g.setFont(this.smallFont);
			g.setColor(FamilyLookAndFeel.getPrimaryControlDarkShadow());
			SwingUtilities2.drawString(tip, g, accelString,
			        tip.getWidth() - 1 - insets.right - accelSpacing + padSpaceBetweenStrings - 3,
			        paintTextR.y + accelBL);
		}
	}

	private int calcAccelSpacing(JComponent c, FontMetrics fm, String accel)
	{
		return accel.equals("") ? 0 : padSpaceBetweenStrings + SwingUtilities2.stringWidth(c, fm, accel);
	}

	@Override
	public Dimension getPreferredSize(JComponent c)
	{
		Dimension d = super.getPreferredSize(c);

		String key = getAcceleratorString((JToolTip) c);
		if (!(key.equals("")))
			d.width += calcAccelSpacing(c, c.getFontMetrics(this.smallFont), key);
		return d;
	}

	protected boolean isAcceleratorHidden()
	{
		Boolean b = (Boolean) UIManager.get("ToolTip.hideAccelerator");
		return b != null && b.booleanValue();
	}

	@SuppressWarnings("hiding")
	private String getAcceleratorString(JToolTip tip)
	{
		this.tip = tip;

		String retValue = getAcceleratorString();

		this.tip = null;
		return retValue;
	}

	// NOTE: This requires the tip field to be set before this is invoked.
	// As FamilyToolTipUI is shared between all JToolTips the tip field is
	// set appropriately before this is invoked. Unfortunately this means
	// that subclasses that randomly invoke this method will see varying
	// results. If this becomes an issue, FamilyToolTipUI should no longer be
	// shared.
	@SuppressWarnings("unused")
	public String getAcceleratorString()
	{
		if (this.tip == null || isAcceleratorHidden()) { return ""; }
		JComponent comp = this.tip.getComponent();
		if (!(comp instanceof AbstractButton)) { return ""; }

		KeyStroke[] keys = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).keys();
		if (keys == null) { return ""; }

		String controlKeyStr = "";

		for (int i = 0; i < keys.length; i++)
		{
			int mod = keys[i].getModifiers();
			controlKeyStr = KeyEvent.getKeyModifiersText(mod) + this.acceleratorDelimiter
			        + KeyEvent.getKeyText(keys[i].getKeyCode());
			break;
		}

		return controlKeyStr;
	}

}
