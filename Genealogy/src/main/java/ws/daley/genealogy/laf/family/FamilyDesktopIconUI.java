/*
 * Copyright (c) 1998, 2003, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicDesktopIconUI;

/**
 * Family desktop icon.
 *
 * @author Steve Wilson
 */
public class FamilyDesktopIconUI extends BasicDesktopIconUI
{

	JButton button;
	JLabel label;
	TitleListener titleListener;
	private int width;

	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		return new FamilyDesktopIconUI();
	}

	public FamilyDesktopIconUI()
	{}

	@Override
	protected void installDefaults()
	{
		super.installDefaults();
		LookAndFeel.installColorsAndFont(this.desktopIcon, "DesktopIcon.background", "DesktopIcon.foreground", "DesktopIcon.font");
		this.width = UIManager.getInt("DesktopIcon.width");
	}

	@Override
	protected void installComponents()
	{
		this.frame = this.desktopIcon.getInternalFrame();
		Icon icon = this.frame.getFrameIcon();
		String title = this.frame.getTitle();

		this.button = new JButton(title, icon);
		this.button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(
		            @SuppressWarnings("unused") ActionEvent e)
			{
				deiconize();
			}
		});
		this.button.setFont(this.desktopIcon.getFont());
		this.button.setBackground(this.desktopIcon.getBackground());
		this.button.setForeground(this.desktopIcon.getForeground());

		int buttonH = this.button.getPreferredSize().height;

		Icon drag = new FamilyBumps((buttonH / 3), buttonH,
		        FamilyLookAndFeel.getControlHighlight(),
		        FamilyLookAndFeel.getControlDarkShadow(),
		        FamilyLookAndFeel.getControl());
		this.label = new JLabel(drag);

		this.label.setBorder(new MatteBorder(0, 2, 0, 1, this.desktopIcon.getBackground()));
		this.desktopIcon.setLayout(new BorderLayout(2, 0));
		this.desktopIcon.add(this.button, BorderLayout.CENTER);
		this.desktopIcon.add(this.label, BorderLayout.WEST);
	}

	@Override
	protected void uninstallComponents()
	{
		this.desktopIcon.setLayout(null);
		this.desktopIcon.remove(this.label);
		this.desktopIcon.remove(this.button);
		this.button = null;
		this.frame = null;
	}

	@Override
	protected void installListeners()
	{
		super.installListeners();
		this.desktopIcon.getInternalFrame().addPropertyChangeListener(this.titleListener = new TitleListener());
	}

	@Override
	protected void uninstallListeners()
	{
		this.desktopIcon.getInternalFrame().removePropertyChangeListener(this.titleListener);
		this.titleListener = null;
		super.uninstallListeners();
	}

	@Override
	public Dimension getPreferredSize(JComponent c)
	{
		// Family desktop icons can not be resized. Their dimensions should
		// always be the minimum size. See getMinimumSize(JComponent c).
		return getMinimumSize(c);
	}

	@Override
	public Dimension getMinimumSize(@SuppressWarnings("unused") JComponent c)
	{
		// For the family desktop icon we will use the layout maanger to
		// determine the correct height of the component, but we want to keep
		// the width consistent according to the jlf spec.
		return new Dimension(this.width, this.desktopIcon.getLayout().minimumLayoutSize(this.desktopIcon).height);
	}

	@Override
	public Dimension getMaximumSize(JComponent c)
	{
		// Family desktop icons can not be resized. Their dimensions should
		// always be the minimum size. See getMinimumSize(JComponent c).
		return getMinimumSize(c);
	}

	class TitleListener implements PropertyChangeListener
	{
		@Override
		public void propertyChange(PropertyChangeEvent e)
		{
			if (e.getPropertyName().equals("title"))
				FamilyDesktopIconUI.this.button.setText((String) e.getNewValue());

			if (e.getPropertyName().equals("frameIcon"))
				FamilyDesktopIconUI.this.button.setIcon((Icon) e.getNewValue());
		}
	}
}
