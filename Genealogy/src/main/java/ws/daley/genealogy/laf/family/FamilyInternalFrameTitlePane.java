/*
 * Copyright (c) 1998, 2008, Oracle and/or its affiliates. All rights reserved.
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
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import sun.swing.SwingUtilities2;

/**
 * Class that manages a JLF title bar
 * 
 * @author Steve Wilson
 * @author Brian Beck
 * @since 1.3
 */

@SuppressWarnings("serial")
public class FamilyInternalFrameTitlePane extends BasicInternalFrameTitlePane
{

	protected boolean isPalette = false;
	protected Icon paletteCloseIcon;
	protected int paletteTitleHeight;

	private static final Border handyEmptyBorder = new EmptyBorder(0, 0, 0, 0);

	/**
	 * Key used to lookup Color from UIManager. If this is null,
	 * <code>getWindowTitleBackground</code> is used.
	 */
	private String selectedBackgroundKey;
	/**
	 * Key used to lookup Color from UIManager. If this is null,
	 * <code>getWindowTitleForeground</code> is used.
	 */
	private String selectedForegroundKey;
	/**
	 * Key used to lookup shadow color from UIManager. If this is null,
	 * <code>getPrimaryControlDarkShadow</code> is used.
	 */
	private String selectedShadowKey;
	/**
	 * Boolean indicating the state of the <code>JInternalFrame</code>s closable
	 * property at <code>updateUI</code> time.
	 */
	private boolean wasClosable;

	int buttonsWidth = 0;

	FamilyBumps activeBumps = new FamilyBumps(0, 0, FamilyLookAndFeel.getPrimaryControlHighlight(),
	        FamilyLookAndFeel.getPrimaryControlDarkShadow(),
	        (UIManager.get("InternalFrame.activeTitleGradient") != null) ? null
	                : FamilyLookAndFeel.getPrimaryControl());
	FamilyBumps inactiveBumps = new FamilyBumps(0, 0, FamilyLookAndFeel.getControlHighlight(),
	        FamilyLookAndFeel.getControlDarkShadow(),
	        (UIManager.get("InternalFrame.inactiveTitleGradient") != null) ? null : FamilyLookAndFeel.getControl());
	FamilyBumps paletteBumps;

	private Color activeBumpsHighlight = FamilyLookAndFeel.getPrimaryControlHighlight();
	private Color activeBumpsShadow = FamilyLookAndFeel.getPrimaryControlDarkShadow();

	public FamilyInternalFrameTitlePane(JInternalFrame f)
	{
		super(f);
	}

	@Override
	public void addNotify()
	{
		super.addNotify();
		// This is done here instead of in installDefaults as I was worried
		// that the BasicInternalFrameUI might not be fully initialized, and
		// that if this resets the closable state the BasicInternalFrameUI
		// Listeners that get notified might be in an odd/uninitialized state.
		updateOptionPaneState();
	}

	@Override
	protected void installDefaults()
	{
		super.installDefaults();
		setFont(UIManager.getFont("InternalFrame.titleFont"));
		this.paletteTitleHeight = UIManager.getInt("InternalFrame.paletteTitleHeight");
		this.paletteCloseIcon = UIManager.getIcon("InternalFrame.paletteCloseIcon");
		this.wasClosable = this.frame.isClosable();
		this.selectedForegroundKey = this.selectedBackgroundKey = null;
		if (FamilyLookAndFeel.usingOcean())
			setOpaque(true);
	}

	@Override
	protected void uninstallDefaults()
	{
		super.uninstallDefaults();
		if (this.wasClosable != this.frame.isClosable()) this.frame.setClosable(this.wasClosable);
	}

	@Override
	protected void createButtons()
	{
		super.createButtons();

		Boolean paintActive = this.frame.isSelected() ? Boolean.TRUE : Boolean.FALSE;
		this.iconButton.putClientProperty("paintActive", paintActive);
		this.iconButton.setBorder(handyEmptyBorder);

		this.maxButton.putClientProperty("paintActive", paintActive);
		this.maxButton.setBorder(handyEmptyBorder);

		this.closeButton.putClientProperty("paintActive", paintActive);
		this.closeButton.setBorder(handyEmptyBorder);

		// The palette close icon isn't opaque while the regular close icon is.
		// This makes sure palette close buttons have the right background.
		this.closeButton.setBackground(FamilyLookAndFeel.getPrimaryControlShadow());

		if (FamilyLookAndFeel.usingOcean())
		{
			this.iconButton.setContentAreaFilled(false);
			this.maxButton.setContentAreaFilled(false);
			this.closeButton.setContentAreaFilled(false);
		}
	}

	/**
	 * Override the parent's method to do nothing. Family frames do not have
	 * system menus.
	 */
	@Override
	protected void assembleSystemMenu()
	{}

	/**
	 * Override the parent's method to do nothing. Family frames do not have
	 * system menus.
	 */
	@Override
	protected void addSystemMenuItems(@SuppressWarnings("unused") JMenu systemMenu)
	{}

	/**
	 * Override the parent's method to do nothing. Family frames do not have
	 * system menus.
	 */
	@Override
	protected void showSystemMenu()
	{}

	/**
	 * Override the parent's method avoid creating a menu bar. Family frames do
	 * not have system menus.
	 */
	@Override
	protected void addSubComponents()
	{
		add(this.iconButton);
		add(this.maxButton);
		add(this.closeButton);
	}

	@Override
	protected PropertyChangeListener createPropertyChangeListener()
	{
		return new FamilyPropertyChangeHandler();
	}

	@Override
	protected LayoutManager createLayout()
	{
		return new FamilyTitlePaneLayout();
	}

	class FamilyPropertyChangeHandler extends BasicInternalFrameTitlePane.PropertyChangeHandler
	{
		@SuppressWarnings("synthetic-access")
		@Override
		public void propertyChange(PropertyChangeEvent evt)
		{
			String prop = evt.getPropertyName();
			if (prop.equals(JInternalFrame.IS_SELECTED_PROPERTY))
			{
				Boolean b = (Boolean) evt.getNewValue();
				FamilyInternalFrameTitlePane.this.iconButton.putClientProperty("paintActive", b);
				FamilyInternalFrameTitlePane.this.closeButton.putClientProperty("paintActive", b);
				FamilyInternalFrameTitlePane.this.maxButton.putClientProperty("paintActive", b);
			}
			else if ("JInternalFrame.messageType".equals(prop))
			{
				updateOptionPaneState();
				FamilyInternalFrameTitlePane.this.frame.repaint();
			}
			super.propertyChange(evt);
		}
	}

	class FamilyTitlePaneLayout extends TitlePaneLayout
	{
		@Override
		public void addLayoutComponent(@SuppressWarnings("unused") String name, @SuppressWarnings("unused") Component c)
		{}

		@Override
		public void removeLayoutComponent(@SuppressWarnings("unused") Component c)
		{}

		@Override
		public Dimension preferredLayoutSize(Container c)
		{
			return minimumLayoutSize(c);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public Dimension minimumLayoutSize(@SuppressWarnings("unused") Container c)
		{
			// Compute width.
			int width = 30;
			if (FamilyInternalFrameTitlePane.this.frame.isClosable())
				width += 21;
			if (FamilyInternalFrameTitlePane.this.frame.isMaximizable())
				width += 16 + (FamilyInternalFrameTitlePane.this.frame.isClosable() ? 10 : 4);
			if (FamilyInternalFrameTitlePane.this.frame.isIconifiable())
				width += 16 + (FamilyInternalFrameTitlePane.this.frame.isMaximizable() ? 2
				        : (FamilyInternalFrameTitlePane.this.frame.isClosable() ? 10 : 4));
			FontMetrics fm = FamilyInternalFrameTitlePane.this.frame.getFontMetrics(getFont());
			String frameTitle = FamilyInternalFrameTitlePane.this.frame.getTitle();
			int title_w = frameTitle != null
			        ? SwingUtilities2.stringWidth(FamilyInternalFrameTitlePane.this.frame, fm, frameTitle) : 0;
			int title_length = frameTitle != null ? frameTitle.length() : 0;

			if (title_length > 2)
			{
				int subtitle_w = SwingUtilities2.stringWidth(FamilyInternalFrameTitlePane.this.frame, fm,
				        FamilyInternalFrameTitlePane.this.frame.getTitle().substring(0, 2) + "...");
				width += (title_w < subtitle_w) ? title_w : subtitle_w;
			}
			else
				width += title_w;

			// Compute height.
			int height;
			if (FamilyInternalFrameTitlePane.this.isPalette)
				height = FamilyInternalFrameTitlePane.this.paletteTitleHeight;
			else
			{
				int fontHeight = fm.getHeight();
				fontHeight += 7;
				Icon icon = FamilyInternalFrameTitlePane.this.frame.getFrameIcon();
				int iconHeight = 0;
				if (icon != null)
					// SystemMenuBar forces the icon to be 16x16 or less.
					iconHeight = Math.min(icon.getIconHeight(), 16);
				iconHeight += 5;
				height = Math.max(fontHeight, iconHeight);
			}

			return new Dimension(width, height);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void layoutContainer(@SuppressWarnings("unused") Container c)
		{
			boolean leftToRight = FamilyUtils.isLeftToRight(FamilyInternalFrameTitlePane.this.frame);

			int w = getWidth();
			int x = leftToRight ? w : 0;
			int y = 2;
			int spacing;

			// assumes all buttons have the same dimensions
			// these dimensions include the borders
			int buttonHeight = FamilyInternalFrameTitlePane.this.closeButton.getIcon().getIconHeight();
			int buttonWidth = FamilyInternalFrameTitlePane.this.closeButton.getIcon().getIconWidth();

			if (FamilyInternalFrameTitlePane.this.frame.isClosable())
				if (FamilyInternalFrameTitlePane.this.isPalette)
				{
					spacing = 3;
					x += leftToRight ? -spacing - (buttonWidth + 2) : spacing;
					FamilyInternalFrameTitlePane.this.closeButton.setBounds(x, y, buttonWidth + 2, getHeight() - 4);
					if (!leftToRight) x += (buttonWidth + 2);
				}
				else
				{
					spacing = 4;
					x += leftToRight ? -spacing - buttonWidth : spacing;
					FamilyInternalFrameTitlePane.this.closeButton.setBounds(x, y, buttonWidth, buttonHeight);
					if (!leftToRight) x += buttonWidth;
				}

			if (FamilyInternalFrameTitlePane.this.frame.isMaximizable() && !FamilyInternalFrameTitlePane.this.isPalette)
			{
				spacing = FamilyInternalFrameTitlePane.this.frame.isClosable() ? 10 : 4;
				x += leftToRight ? -spacing - buttonWidth : spacing;
				FamilyInternalFrameTitlePane.this.maxButton.setBounds(x, y, buttonWidth, buttonHeight);
				if (!leftToRight)
					x += buttonWidth;
			}

			if (FamilyInternalFrameTitlePane.this.frame.isIconifiable() && !FamilyInternalFrameTitlePane.this.isPalette)
			{
				spacing = FamilyInternalFrameTitlePane.this.frame.isMaximizable() ? 2
				        : (FamilyInternalFrameTitlePane.this.frame.isClosable() ? 10 : 4);
				x += leftToRight ? -spacing - buttonWidth : spacing;
				FamilyInternalFrameTitlePane.this.iconButton.setBounds(x, y, buttonWidth, buttonHeight);
				if (!leftToRight)
					x += buttonWidth;
			}

			FamilyInternalFrameTitlePane.this.buttonsWidth = leftToRight ? w - x : x;
		}
	}

	public void paintPalette(Graphics g)
	{
		boolean leftToRight = FamilyUtils.isLeftToRight(this.frame);

		int width = getWidth();
		int height = getHeight();

		if (this.paletteBumps == null)
			this.paletteBumps = new FamilyBumps(0, 0, FamilyLookAndFeel.getPrimaryControlHighlight(),
			        FamilyLookAndFeel.getPrimaryControlInfo(), FamilyLookAndFeel.getPrimaryControlShadow());

		Color background = FamilyLookAndFeel.getPrimaryControlShadow();
		Color darkShadow = FamilyLookAndFeel.getPrimaryControlDarkShadow();

		g.setColor(background);
		g.fillRect(0, 0, width, height);

		g.setColor(darkShadow);
		g.drawLine(0, height - 1, width, height - 1);

		int xOffset = leftToRight ? 4 : this.buttonsWidth + 4;
		int bumpLength = width - this.buttonsWidth - 2 * 4;
		int bumpHeight = getHeight() - 4;
		this.paletteBumps.setBumpArea(bumpLength, bumpHeight);
		this.paletteBumps.paintIcon(this, g, xOffset, 2);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		if (this.isPalette)
		{
			paintPalette(g);
			return;
		}

		boolean leftToRight = FamilyUtils.isLeftToRight(this.frame);
		boolean isSelected = this.frame.isSelected();

		int width = getWidth();
		int height = getHeight();

		Color background = null;
		Color foreground = null;
		Color shadow = null;

		FamilyBumps bumps;
		String gradientKey;

		if (isSelected)
		{
			if (!FamilyLookAndFeel.usingOcean())
			{
				this.closeButton.setContentAreaFilled(true);
				this.maxButton.setContentAreaFilled(true);
				this.iconButton.setContentAreaFilled(true);
			}
			if (this.selectedBackgroundKey != null)
				background = UIManager.getColor(this.selectedBackgroundKey);
			if (background == null)
				background = FamilyLookAndFeel.getWindowTitleBackground();
			if (this.selectedForegroundKey != null)
				foreground = UIManager.getColor(this.selectedForegroundKey);
			if (this.selectedShadowKey != null)
				shadow = UIManager.getColor(this.selectedShadowKey);
			if (shadow == null)
				shadow = FamilyLookAndFeel.getPrimaryControlDarkShadow();
			if (foreground == null)
				foreground = FamilyLookAndFeel.getWindowTitleForeground();
			this.activeBumps.setBumpColors(this.activeBumpsHighlight, this.activeBumpsShadow,
			        UIManager.get("InternalFrame.activeTitleGradient") != null ? null : background);
			bumps = this.activeBumps;
			gradientKey = "InternalFrame.activeTitleGradient";
		}
		else
		{
			if (!FamilyLookAndFeel.usingOcean())
			{
				this.closeButton.setContentAreaFilled(false);
				this.maxButton.setContentAreaFilled(false);
				this.iconButton.setContentAreaFilled(false);
			}
			background = FamilyLookAndFeel.getWindowTitleInactiveBackground();
			foreground = FamilyLookAndFeel.getWindowTitleInactiveForeground();
			shadow = FamilyLookAndFeel.getControlDarkShadow();
			bumps = this.inactiveBumps;
			gradientKey = "InternalFrame.inactiveTitleGradient";
		}

		if (!FamilyUtils.drawGradient(this, g, gradientKey, 0, 0, width, height, true))
		{
			g.setColor(background);
			g.fillRect(0, 0, width, height);
		}

		g.setColor(shadow);
		g.drawLine(0, height - 1, width, height - 1);
		g.drawLine(0, 0, 0, 0);
		g.drawLine(width - 1, 0, width - 1, 0);

		int titleLength;
		int xOffset = leftToRight ? 5 : width - 5;
		String frameTitle = this.frame.getTitle();

		Icon icon = this.frame.getFrameIcon();
		if (icon != null)
		{
			if (!leftToRight)
				xOffset -= icon.getIconWidth();
			int iconY = ((height / 2) - (icon.getIconHeight() / 2));
			icon.paintIcon(this.frame, g, xOffset, iconY);
			xOffset += leftToRight ? icon.getIconWidth() + 5 : -5;
		}

		if (frameTitle != null)
		{
			Font f = getFont();
			g.setFont(f);
			FontMetrics fm = SwingUtilities2.getFontMetrics(this.frame, g, f);
			@SuppressWarnings("unused")
			int fHeight = fm.getHeight();

			g.setColor(foreground);

			int yOffset = ((height - fm.getHeight()) / 2) + fm.getAscent();

			Rectangle rect = new Rectangle(0, 0, 0, 0);
			if (this.frame.isIconifiable())
				rect = this.iconButton.getBounds();
			else if (this.frame.isMaximizable())
				rect = this.maxButton.getBounds();
			else if (this.frame.isClosable())
				rect = this.closeButton.getBounds();
			int titleW;

			if (leftToRight)
			{
				if (rect.x == 0)
					rect.x = this.frame.getWidth() - this.frame.getInsets().right - 2;
				titleW = rect.x - xOffset - 4;
				frameTitle = getTitle(frameTitle, fm, titleW);
			}
			else
			{
				titleW = xOffset - rect.x - rect.width - 4;
				frameTitle = getTitle(frameTitle, fm, titleW);
				xOffset -= SwingUtilities2.stringWidth(this.frame, fm, frameTitle);
			}

			titleLength = SwingUtilities2.stringWidth(this.frame, fm, frameTitle);
			SwingUtilities2.drawString(this.frame, g, frameTitle, xOffset, yOffset);
			xOffset += leftToRight ? titleLength + 5 : -5;
		}

		int bumpXOffset;
		int bumpLength;
		if (leftToRight)
		{
			bumpLength = width - this.buttonsWidth - xOffset - 5;
			bumpXOffset = xOffset;
		}
		else
		{
			bumpLength = xOffset - this.buttonsWidth - 5;
			bumpXOffset = this.buttonsWidth + 5;
		}
		int bumpYOffset = 3;
		int bumpHeight = getHeight() - (2 * bumpYOffset);
		bumps.setBumpArea(bumpLength, bumpHeight);
		bumps.paintIcon(this, g, bumpXOffset, bumpYOffset);
	}

	public void setPalette(boolean b)
	{
		this.isPalette = b;

		if (this.isPalette)
		{
			this.closeButton.setIcon(this.paletteCloseIcon);
			if (this.frame.isMaximizable()) remove(this.maxButton);
			if (this.frame.isIconifiable()) remove(this.iconButton);
		}
		else
		{
			this.closeButton.setIcon(this.closeIcon);
			if (this.frame.isMaximizable()) add(this.maxButton);
			if (this.frame.isIconifiable()) add(this.iconButton);
		}
		revalidate();
		repaint();
	}

	/**
	 * Updates any state dependant upon the JInternalFrame being shown in a
	 * <code>JOptionPane</code>.
	 */
	private void updateOptionPaneState()
	{
		int type = -2;
		boolean closable = this.wasClosable;
		Object obj = this.frame.getClientProperty("JInternalFrame.messageType");

		if (obj == null)
			// Don't change the closable state unless in an JOptionPane.
			return;
		if (obj instanceof Integer)
			type = ((Integer) obj).intValue();
		switch(type)
		{
			case JOptionPane.ERROR_MESSAGE:
				this.selectedBackgroundKey = "OptionPane.errorDialog.titlePane.background";
				this.selectedForegroundKey = "OptionPane.errorDialog.titlePane.foreground";
				this.selectedShadowKey = "OptionPane.errorDialog.titlePane.shadow";
				closable = false;
				break;
			case JOptionPane.QUESTION_MESSAGE:
				this.selectedBackgroundKey = "OptionPane.questionDialog.titlePane.background";
				this.selectedForegroundKey = "OptionPane.questionDialog.titlePane.foreground";
				this.selectedShadowKey = "OptionPane.questionDialog.titlePane.shadow";
				closable = false;
				break;
			case JOptionPane.WARNING_MESSAGE:
				this.selectedBackgroundKey = "OptionPane.warningDialog.titlePane.background";
				this.selectedForegroundKey = "OptionPane.warningDialog.titlePane.foreground";
				this.selectedShadowKey = "OptionPane.warningDialog.titlePane.shadow";
				closable = false;
				break;
			case JOptionPane.INFORMATION_MESSAGE:
			case JOptionPane.PLAIN_MESSAGE:
				this.selectedBackgroundKey = this.selectedForegroundKey = this.selectedShadowKey = null;
				closable = false;
				break;
			default:
				this.selectedBackgroundKey = this.selectedForegroundKey = this.selectedShadowKey = null;
				break;
		}
		if (closable != this.frame.isClosable())
			this.frame.setClosable(closable);
	}
}
