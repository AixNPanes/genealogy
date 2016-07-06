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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.CellRendererPane;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

/**
 * JButton subclass to help out FamilyComboBoxUI
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @see FamilyComboBoxButton
 * @author Tom Santos
 */
@SuppressWarnings("serial")
public class FamilyComboBoxButton extends JButton
{
	@SuppressWarnings("rawtypes")
	protected JComboBox comboBox;
	@SuppressWarnings("rawtypes")
	protected JList listBox;
	protected CellRendererPane rendererPane;
	protected Icon comboIcon;
	protected boolean iconOnly = false;

	@SuppressWarnings("rawtypes")
	public final JComboBox getComboBox()
	{
		return this.comboBox;
	}

	public final void setComboBox(@SuppressWarnings("rawtypes") JComboBox cb)
	{
		this.comboBox = cb;
	}

	public final Icon getComboIcon()
	{
		return this.comboIcon;
	}

	public final void setComboIcon(Icon i)
	{
		this.comboIcon = i;
	}

	public final boolean isIconOnly()
	{
		return this.iconOnly;
	}

	public final void setIconOnly(boolean isIconOnly)
	{
		this.iconOnly = isIconOnly;
	}

	FamilyComboBoxButton()
	{
		super("");
		@SuppressWarnings("hiding")
		DefaultButtonModel model = new DefaultButtonModel()
		{
			@Override
			public void setArmed(boolean armed)
			{
				super.setArmed(isPressed() ? true : armed);
			}
		};
		setModel(model);
	}

	public FamilyComboBoxButton(@SuppressWarnings("rawtypes") JComboBox cb, Icon i, CellRendererPane pane,
	        @SuppressWarnings("rawtypes") JList list)
	{
		this();
		this.comboBox = cb;
		this.comboIcon = i;
		this.rendererPane = pane;
		this.listBox = list;
		setEnabled(this.comboBox.isEnabled());
	}

	public FamilyComboBoxButton(@SuppressWarnings("rawtypes") JComboBox cb, Icon i, boolean onlyIcon,
	        CellRendererPane pane, @SuppressWarnings("rawtypes") JList list)
	{
		this(cb, i, pane, list);
		this.iconOnly = onlyIcon;
	}

	@Override
	public boolean isFocusTraversable()
	{
		return false;
	}

	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);

		// Set the background and foreground to the combobox colors.
		if (enabled)
		{
			setBackground(this.comboBox.getBackground());
			setForeground(this.comboBox.getForeground());
		}
		else
		{
			setBackground(UIManager.getColor("ComboBox.disabledBackground"));
			setForeground(UIManager.getColor("ComboBox.disabledForeground"));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void paintComponent(Graphics g)
	{
		boolean leftToRight = FamilyUtils.isLeftToRight(this.comboBox);

		// Paint the button as usual
		super.paintComponent(g);

		Insets insets = getInsets();

		int width = getWidth() - (insets.left + insets.right);
		int height = getHeight() - (insets.top + insets.bottom);

		if (height <= 0 || width <= 0) { return; }

		int left = insets.left;
		int top = insets.top;
		int right = left + (width - 1);
		int bottom = top + (height - 1);

		int iconWidth = 0;
		int iconLeft = (leftToRight) ? right : left;

		// Paint the icon
		if (this.comboIcon != null)
		{
			iconWidth = this.comboIcon.getIconWidth();
			int iconHeight = this.comboIcon.getIconHeight();
			int iconTop = 0;

			if (this.iconOnly)
			{
				iconLeft = (getWidth() / 2) - (iconWidth / 2);
				iconTop = (getHeight() / 2) - (iconHeight / 2);
			}
			else
			{
				if (leftToRight)
					iconLeft = (left + (width - 1)) - iconWidth;
				else
					iconLeft = left;
				iconTop = (top + ((bottom - top) / 2)) - (iconHeight / 2);
			}

			this.comboIcon.paintIcon(this, g, iconLeft, iconTop);

			// Paint the focus
			if (this.comboBox.hasFocus() && (!FamilyLookAndFeel.usingOcean() || this.comboBox.isEditable()))
			{
				g.setColor(FamilyLookAndFeel.getFocusColor());
				g.drawRect(left - 1, top - 1, width + 3, height + 1);
			}
		}

		if (FamilyLookAndFeel.usingOcean())
			// With Ocean the button only paints the arrow, bail.
			return;

		// Let the renderer paint
		if (!this.iconOnly && this.comboBox != null)
		{
			@SuppressWarnings("rawtypes")
			ListCellRenderer renderer = this.comboBox.getRenderer();
			Component c;
			boolean renderPressed = getModel().isPressed();
			c = renderer.getListCellRendererComponent(this.listBox, this.comboBox.getSelectedItem(), -1, renderPressed, false);
			c.setFont(this.rendererPane.getFont());

			if (this.model.isArmed() && this.model.isPressed())
			{
				if (isOpaque())
					c.setBackground(UIManager.getColor("Button.select"));
				c.setForeground(this.comboBox.getForeground());
			}
			else if (!this.comboBox.isEnabled())
			{
				if (isOpaque())
					c.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
				c.setForeground(UIManager.getColor("ComboBox.disabledForeground"));
			}
			else
			{
				c.setForeground(this.comboBox.getForeground());
				c.setBackground(this.comboBox.getBackground());
			}

			int cWidth = width - (insets.right + iconWidth);

			// Fix for 4238829: should lay out the JPanel.
			boolean shouldValidate = false;
			if (c instanceof JPanel)
				shouldValidate = true;

			if (leftToRight)
				this.rendererPane.paintComponent(g, c, this, left, top, cWidth, height, shouldValidate);
			else
				this.rendererPane.paintComponent(g, c, this, left + iconWidth, top, cWidth, height, shouldValidate);
		}
	}

	@Override
	public Dimension getMinimumSize()
	{
		Dimension ret = new Dimension();
		Insets insets = getInsets();
		ret.width = insets.left + getComboIcon().getIconWidth() + insets.right;
		ret.height = insets.bottom + getComboIcon().getIconHeight() + insets.top;
		return ret;
	}
}
