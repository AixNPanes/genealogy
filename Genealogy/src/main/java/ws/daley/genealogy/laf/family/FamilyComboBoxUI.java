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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

/**
 * Family UI for JComboBox
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @see FamilyComboBoxEditor
 * @see FamilyComboBoxButton
 * @author Tom Santos
 */
public class FamilyComboBoxUI extends BasicComboBoxUI
{
	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent c)
	{
		return new FamilyComboBoxUI();
	}

	@Override
	public void paint(Graphics g, JComponent c)
	{
		if (FamilyLookAndFeel.usingOcean())
			super.paint(g, c);
	}

	/**
	 * If necessary paints the currently selected item.
	 *
	 * @param g
	 *            Graphics to paint to
	 * @param bounds
	 *            Region to paint current value to
	 * @param hasFocus
	 *            whether or not the JComboBox has focus
	 * @throws NullPointerException
	 *             if any of the arguments are null.
	 * @since 1.5
	 */
	@Override
	public void paintCurrentValue(Graphics g, Rectangle bounds,
	        @SuppressWarnings("hiding") boolean hasFocus)
	{
		// This is really only called if we're using ocean.
		if (FamilyLookAndFeel.usingOcean())
		{
			bounds.x += 2;
			bounds.width -= 3;
			if (this.arrowButton != null)
			{
				Insets buttonInsets = this.arrowButton.getInsets();
				bounds.y += buttonInsets.top;
				bounds.height -= (buttonInsets.top + buttonInsets.bottom);
			}
			else
			{
				bounds.y += 2;
				bounds.height -= 4;
			}
			super.paintCurrentValue(g, bounds, hasFocus);
		}
		else if (g == null || bounds == null) { throw new NullPointerException("Must supply a non-null Graphics and Rectangle"); }
	}

	/**
	 * If necessary paints the background of the currently selected item.
	 *
	 * @param g
	 *            Graphics to paint to
	 * @param bounds
	 *            Region to paint background to
	 * @param hasFocus
	 *            whether or not the JComboBox has focus
	 * @throws NullPointerException
	 *             if any of the arguments are null.
	 * @since 1.5
	 */
	@Override
	public void paintCurrentValueBackground(Graphics g, Rectangle bounds,
	        @SuppressWarnings("hiding") boolean hasFocus)
	{
		// This is really only called if we're using ocean.
		if (FamilyLookAndFeel.usingOcean())
		{
			g.setColor(FamilyLookAndFeel.getControlDarkShadow());
			g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height - 1);
			g.setColor(FamilyLookAndFeel.getControlShadow());
			g.drawRect(bounds.x + 1, bounds.y + 1, bounds.width - 2, bounds.height - 3);
			if (hasFocus && !isPopupVisible(this.comboBox) && this.arrowButton != null)
			{
				g.setColor(this.listBox.getSelectionBackground());
				Insets buttonInsets = this.arrowButton.getInsets();
				if (buttonInsets.top > 2)
					g.fillRect(bounds.x + 2, bounds.y + 2, bounds.width - 3, buttonInsets.top - 2);
				if (buttonInsets.bottom > 2)
					g.fillRect(bounds.x + 2, bounds.y + bounds.height - buttonInsets.bottom, bounds.width - 3, buttonInsets.bottom - 2);
			}
		}
		else if (g == null || bounds == null)
			throw new NullPointerException("Must supply a non-null Graphics and Rectangle");
	}

	/**
	 * Returns the baseline.
	 *
	 * @throws NullPointerException
	 *             {@inheritDoc}
	 * @throws IllegalArgumentException
	 *             {@inheritDoc}
	 * @see javax.swing.JComponent#getBaseline(int, int)
	 * @since 1.6
	 */
	@Override
	public int getBaseline(JComponent c, int width, int height)
	{
		int baseline;
		if (FamilyLookAndFeel.usingOcean() && height >= 4)
		{
			height -= 4;
			baseline = super.getBaseline(c, width, height);
			if (baseline >= 0)
				baseline += 2;
		}
		else
			baseline = super.getBaseline(c, width, height);
		return baseline;
	}

	@Override
	protected ComboBoxEditor createEditor()
	{
		return new FamilyComboBoxEditor.UIResource();
	}

	@Override
	protected ComboPopup createPopup()
	{
		return super.createPopup();
	}

	@Override
	protected JButton createArrowButton()
	{
		boolean iconOnly = (this.comboBox.isEditable() || FamilyLookAndFeel.usingOcean());
		JButton button = new FamilyComboBoxButton(this.comboBox, new FamilyComboBoxIcon(), iconOnly, this.currentValuePane, this.listBox);
		button.setMargin(new Insets(0, 1, 1, 3));
		if (FamilyLookAndFeel.usingOcean())
			// Disabled rollover effect.
			button.putClientProperty(FamilyBorders.NO_BUTTON_ROLLOVER, Boolean.TRUE);
		updateButtonForOcean(button);
		return button;
	}

	/**
	 * Resets the necessary state on the ComboBoxButton for ocean.
	 */
	private void updateButtonForOcean(JButton button)
	{
		if (FamilyLookAndFeel.usingOcean())
			// Ocean renders the focus in a different way, this
			// would be redundant.
			button.setFocusPainted(this.comboBox.isEditable());
	}

	@Override
	public PropertyChangeListener createPropertyChangeListener()
	{
		return new FamilyPropertyChangeListener();
	}

	/**
	 * This class should be treated as a &quot;protected&quot; inner class.
	 * Instantiate it only within subclasses of {@code FamilyComboBoxUI}.
	 */
	public class FamilyPropertyChangeListener
	        extends BasicComboBoxUI.PropertyChangeHandler
	{
		@SuppressWarnings("synthetic-access")
		@Override
		public void propertyChange(PropertyChangeEvent e)
		{
			super.propertyChange(e);
			String propertyName = e.getPropertyName();

			if (propertyName == "editable")
			{
				if (FamilyComboBoxUI.this.arrowButton instanceof FamilyComboBoxButton)
				{
					FamilyComboBoxButton button = (FamilyComboBoxButton) FamilyComboBoxUI.this.arrowButton;
					button.setIconOnly(FamilyComboBoxUI.this.comboBox.isEditable() || FamilyLookAndFeel.usingOcean());
				}
				FamilyComboBoxUI.this.comboBox.repaint();
				updateButtonForOcean(FamilyComboBoxUI.this.arrowButton);
			}
			else if (propertyName == "background")
			{
				Color color = (Color) e.getNewValue();
				FamilyComboBoxUI.this.arrowButton.setBackground(color);
				FamilyComboBoxUI.this.listBox.setBackground(color);

			}
			else if (propertyName == "foreground")
			{
				Color color = (Color) e.getNewValue();
				FamilyComboBoxUI.this.arrowButton.setForeground(color);
				FamilyComboBoxUI.this.listBox.setForeground(color);
			}
		}
	}

	/**
	 * As of Java 2 platform v1.4 this method is no longer used. Do not call or
	 * override. All the functionality of this method is in the
	 * FamilyPropertyChangeListener.
	 *
	 * @deprecated As of Java 2 platform v1.4.
	 */
	@Deprecated
	protected void editablePropertyChanged(@SuppressWarnings("unused") PropertyChangeEvent e)
	{}

	@Override
	protected LayoutManager createLayoutManager()
	{
		return new FamilyComboBoxLayoutManager();
	}

	/**
	 * This class should be treated as a &quot;protected&quot; inner class.
	 * Instantiate it only within subclasses of {@code FamilyComboBoxUI}.
	 */
	public class FamilyComboBoxLayoutManager
	        extends BasicComboBoxUI.ComboBoxLayoutManager
	{
		@Override
		public void layoutContainer(Container parent)
		{
			layoutComboBox(parent, this);
		}

		public void superLayout(Container parent)
		{
			super.layoutContainer(parent);
		}
	}

	// This is here because of a bug in the compiler.
	// When a protected-inner-class-savvy compiler comes out we
	// should move this into FamilyComboBoxLayoutManager.
	public void layoutComboBox(Container parent,
	        FamilyComboBoxLayoutManager manager)
	{
		if (this.comboBox.isEditable() && !FamilyLookAndFeel.usingOcean())
		{
			manager.superLayout(parent);
			return;
		}

		if (this.arrowButton != null)
			if (FamilyLookAndFeel.usingOcean())
			{
				Insets insets = this.comboBox.getInsets();
				int buttonWidth = this.arrowButton.getMinimumSize().width;
				this.arrowButton.setBounds(
				        FamilyUtils.isLeftToRight(this.comboBox)
				                ? (this.comboBox.getWidth() - insets.right - buttonWidth)
				                : insets.left,
				        insets.top, buttonWidth,
				        this.comboBox.getHeight() - insets.top - insets.bottom);
			}
			else
			{
				Insets insets = this.comboBox.getInsets();
				int width = this.comboBox.getWidth();
				int height = this.comboBox.getHeight();
				this.arrowButton.setBounds(insets.left, insets.top,
				        width - (insets.left + insets.right),
				        height - (insets.top + insets.bottom));
			}

		if (this.editor != null && FamilyLookAndFeel.usingOcean())
		{
			Rectangle cvb = rectangleForCurrentValue();
			this.editor.setBounds(cvb);
		}
	}

	/**
	 * As of Java 2 platform v1.4 this method is no longer used.
	 *
	 * @deprecated As of Java 2 platform v1.4.
	 */
	@Deprecated
	protected void removeListeners()
	{
		if (this.propertyChangeListener != null)
			this.comboBox.removePropertyChangeListener(this.propertyChangeListener);
	}

	// These two methods were overloaded and made public. This was probably a
	// mistake in the implementation. The functionality that they used to
	// provide is no longer necessary and should be removed. However,
	// removing them will create an uncompatible API change.

	@Override
	public void configureEditor()
	{
		super.configureEditor();
	}

	@Override
	public void unconfigureEditor()
	{
		super.unconfigureEditor();
	}

	@Override
	public Dimension getMinimumSize(JComponent c)
	{
		if (!this.isMinimumSizeDirty)
			return new Dimension(this.cachedMinimumSize);

		Dimension size = null;

		if (!this.comboBox.isEditable() && this.arrowButton != null)
		{
			Insets buttonInsets = this.arrowButton.getInsets();
			Insets insets = this.comboBox.getInsets();

			size = getDisplaySize();
			size.width += insets.left + insets.right;
			size.width += buttonInsets.right;
			size.width += this.arrowButton.getMinimumSize().width;
			size.height += insets.top + insets.bottom;
			size.height += buttonInsets.top + buttonInsets.bottom;
		}
		else if (this.comboBox.isEditable() && this.arrowButton != null && this.editor != null)
		{
			size = super.getMinimumSize(c);
			Insets margin = this.arrowButton.getMargin();
			size.height += margin.top + margin.bottom;
			size.width += margin.left + margin.right;
		}
		else
			size = super.getMinimumSize(c);

		this.cachedMinimumSize.setSize(size.width, size.height);
		this.isMinimumSizeDirty = false;

		return new Dimension(this.cachedMinimumSize);
	}

	/**
	 * This class should be treated as a &quot;protected&quot; inner class.
	 * Instantiate it only within subclasses of {@code FamilyComboBoxUI}.
	 *
	 * This class is now obsolete and doesn't do anything and is only included
	 * for backwards API compatibility. Do not call or override.
	 *
	 * @deprecated As of Java 2 platform v1.4.
	 */
	@SuppressWarnings("serial")
	@Deprecated
	public class FamilyComboPopup extends BasicComboPopup
	{

		public FamilyComboPopup(@SuppressWarnings("rawtypes") JComboBox cBox)
		{
			super(cBox);
		}

		// This method was overloaded and made public. This was probably
		// mistake in the implementation. The functionality that they used to
		// provide is no longer necessary and should be removed. However,
		// removing them will create an uncompatible API change.

		@Override
		public void delegateFocus(MouseEvent e)
		{
			super.delegateFocus(e);
		}
	}
}
