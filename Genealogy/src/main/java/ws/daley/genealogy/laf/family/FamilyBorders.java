/*
 * Copyright (c) 1998, 2015, Oracle and/or its affiliates. All rights reserved.
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
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.text.JTextComponent;

import sun.swing.StringUIClientPropertyKey;
import sun.swing.SwingUtilities2;

/**
 * Factory object that can vend Borders appropriate for the family L &amp; F.
 * 
 * @author Steve Wilson
 */

public class FamilyBorders
{
	/**
	 * Client property indicating the button shouldn't provide a rollover
	 * indicator. Only used with the Ocean theme.
	 */
	static Object NO_BUTTON_ROLLOVER = new StringUIClientPropertyKey("NoButtonRollover");

	@SuppressWarnings("serial")
	public static class Flush3DBorder extends AbstractBorder implements UIResource
	{
		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			if (c.isEnabled())
				FamilyUtils.drawFlush3DBorder(g, x, y, w, h);
			else
				FamilyUtils.drawDisabledBorder(g, x, y, w, h);
		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			newInsets.set(2, 2, 2, 2);
			return newInsets;
		}
	}

	@SuppressWarnings("serial")
	public static class ButtonBorder extends AbstractBorder implements UIResource
	{

		protected static Insets borderInsets = new Insets(3, 3, 3, 3);

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			if (!(c instanceof AbstractButton))
				return;
			if (FamilyLookAndFeel.usingOcean())
			{
				paintOceanBorder(c, g, x, y, w, h);
				return;
			}
			AbstractButton button = (AbstractButton) c;
			ButtonModel model = button.getModel();

			if (model.isEnabled())
			{
				boolean isPressed = model.isPressed() && model.isArmed();
				boolean isDefault = (button instanceof JButton && ((JButton) button).isDefaultButton());

				if (isPressed && isDefault)
					FamilyUtils.drawDefaultButtonPressedBorder(g, x, y, w, h);
				else if (isPressed)
					FamilyUtils.drawPressed3DBorder(g, x, y, w, h);
				else if (isDefault)
					FamilyUtils.drawDefaultButtonBorder(g, x, y, w, h, false);
				else
					FamilyUtils.drawButtonBorder(g, x, y, w, h, false);
			}
			else
				// disabled state
				FamilyUtils.drawDisabledBorder(g, x, y, w - 1, h - 1);
		}

		private void paintOceanBorder(Component c, Graphics g, int x, int y,
		        int w, int h)
		{
			AbstractButton button = (AbstractButton) c;
			ButtonModel model = ((AbstractButton) c).getModel();

			g.translate(x, y);
			if (FamilyUtils.isToolBarButton(button))
			{
				if (model.isEnabled())
				{
					if (model.isPressed())
					{
						g.setColor(FamilyLookAndFeel.getWhite());
						g.fillRect(1, h - 1, w - 1, 1);
						g.fillRect(w - 1, 1, 1, h - 1);
						g.setColor(FamilyLookAndFeel.getControlDarkShadow());
						g.drawRect(0, 0, w - 2, h - 2);
						g.fillRect(1, 1, w - 3, 1);
					}
					else if (model.isSelected() || model.isRollover())
					{
						g.setColor(FamilyLookAndFeel.getWhite());
						g.fillRect(1, h - 1, w - 1, 1);
						g.fillRect(w - 1, 1, 1, h - 1);
						g.setColor(FamilyLookAndFeel.getControlDarkShadow());
						g.drawRect(0, 0, w - 2, h - 2);
					}
					else
					{
						g.setColor(FamilyLookAndFeel.getWhite());
						g.drawRect(1, 1, w - 2, h - 2);
						g.setColor(UIManager.getColor("Button.toolBarBorderBackground"));
						g.drawRect(0, 0, w - 2, h - 2);
					}
				}
				else
				{
					g.setColor(UIManager.getColor("Button.disabledToolBarBorderBackground"));
					g.drawRect(0, 0, w - 2, h - 2);
				}
			}
			else if (model.isEnabled())
			{
				boolean pressed = model.isPressed();
				@SuppressWarnings("unused")
				boolean armed = model.isArmed();

				if ((c instanceof JButton) && ((JButton) c).isDefaultButton())
				{
					g.setColor(FamilyLookAndFeel.getControlDarkShadow());
					g.drawRect(0, 0, w - 1, h - 1);
					g.drawRect(1, 1, w - 3, h - 3);
				}
				else if (pressed)
				{
					g.setColor(FamilyLookAndFeel.getControlDarkShadow());
					g.fillRect(0, 0, w, 2);
					g.fillRect(0, 2, 2, h - 2);
					g.fillRect(w - 1, 1, 1, h - 1);
					g.fillRect(1, h - 1, w - 2, 1);
				}
				else if (model.isRollover() && button.getClientProperty(NO_BUTTON_ROLLOVER) == null)
				{
					g.setColor(FamilyLookAndFeel.getPrimaryControl());
					g.drawRect(0, 0, w - 1, h - 1);
					g.drawRect(2, 2, w - 5, h - 5);
					g.setColor(FamilyLookAndFeel.getControlDarkShadow());
					g.drawRect(1, 1, w - 3, h - 3);
				}
				else
				{
					g.setColor(FamilyLookAndFeel.getControlDarkShadow());
					g.drawRect(0, 0, w - 1, h - 1);
				}
			}
			else
			{
				g.setColor(FamilyLookAndFeel.getInactiveControlTextColor());
				g.drawRect(0, 0, w - 1, h - 1);
				if ((c instanceof JButton) && ((JButton) c).isDefaultButton())
					g.drawRect(1, 1, w - 3, h - 3);
			}
		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			newInsets.set(3, 3, 3, 3);
			return newInsets;
		}
	}

	@SuppressWarnings("serial")
	public static class InternalFrameBorder extends AbstractBorder
	        implements UIResource
	{
		private static final int corner = 14;

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{

			Color background;
			Color highlight;
			Color shadow;

			if (c instanceof JInternalFrame && ((JInternalFrame) c).isSelected())
			{
				background = FamilyLookAndFeel.getPrimaryControlDarkShadow();
				highlight = FamilyLookAndFeel.getPrimaryControlShadow();
				shadow = FamilyLookAndFeel.getPrimaryControlInfo();
			}
			else
			{
				background = FamilyLookAndFeel.getControlDarkShadow();
				highlight = FamilyLookAndFeel.getControlShadow();
				shadow = FamilyLookAndFeel.getControlInfo();
			}

			g.setColor(background);
			// Draw outermost lines
			g.drawLine(1, 0, w - 2, 0);
			g.drawLine(0, 1, 0, h - 2);
			g.drawLine(w - 1, 1, w - 1, h - 2);
			g.drawLine(1, h - 1, w - 2, h - 1);

			// Draw the bulk of the border
			for (int i = 1; i < 5; i++)
				g.drawRect(x + i, y + i, w - (i * 2) - 1, h - (i * 2) - 1);

			if (c instanceof JInternalFrame && ((JInternalFrame) c).isResizable())
			{
				g.setColor(highlight);
				// Draw the Long highlight lines
				g.drawLine(corner + 1, 3, w - corner, 3);
				g.drawLine(3, corner + 1, 3, h - corner);
				g.drawLine(w - 2, corner + 1, w - 2, h - corner);
				g.drawLine(corner + 1, h - 2, w - corner, h - 2);

				g.setColor(shadow);
				// Draw the Long shadow lines
				g.drawLine(corner, 2, w - corner - 1, 2);
				g.drawLine(2, corner, 2, h - corner - 1);
				g.drawLine(w - 3, corner, w - 3, h - corner - 1);
				g.drawLine(corner, h - 3, w - corner - 1, h - 3);
			}

		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			newInsets.set(5, 5, 5, 5);
			return newInsets;
		}
	}

	/**
	 * Border for a Frame.
	 * 
	 * @since 1.4
	 */
	@SuppressWarnings("serial")
	static class FrameBorder extends AbstractBorder implements UIResource
	{
		private static final int corner = 14;

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{

			Color background;
			Color highlight;
			Color shadow;

			Window window = SwingUtilities.getWindowAncestor(c);
			if (window != null && window.isActive())
			{
				background = FamilyLookAndFeel.getPrimaryControlDarkShadow();
				highlight = FamilyLookAndFeel.getPrimaryControlShadow();
				shadow = FamilyLookAndFeel.getPrimaryControlInfo();
			}
			else
			{
				background = FamilyLookAndFeel.getControlDarkShadow();
				highlight = FamilyLookAndFeel.getControlShadow();
				shadow = FamilyLookAndFeel.getControlInfo();
			}

			g.setColor(background);
			// Draw outermost lines
			g.drawLine(x + 1, y + 0, x + w - 2, y + 0);
			g.drawLine(x + 0, y + 1, x + 0, y + h - 2);
			g.drawLine(x + w - 1, y + 1, x + w - 1, y + h - 2);
			g.drawLine(x + 1, y + h - 1, x + w - 2, y + h - 1);

			// Draw the bulk of the border
			for (int i = 1; i < 5; i++)
				g.drawRect(x + i, y + i, w - (i * 2) - 1, h - (i * 2) - 1);

			if ((window instanceof Frame) && ((Frame) window).isResizable())
			{
				g.setColor(highlight);
				// Draw the Long highlight lines
				g.drawLine(corner + 1, 3, w - corner, 3);
				g.drawLine(3, corner + 1, 3, h - corner);
				g.drawLine(w - 2, corner + 1, w - 2, h - corner);
				g.drawLine(corner + 1, h - 2, w - corner, h - 2);

				g.setColor(shadow);
				// Draw the Long shadow lines
				g.drawLine(corner, 2, w - corner - 1, 2);
				g.drawLine(2, corner, 2, h - corner - 1);
				g.drawLine(w - 3, corner, w - 3, h - corner - 1);
				g.drawLine(corner, h - 3, w - corner - 1, h - 3);
			}

		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			newInsets.set(5, 5, 5, 5);
			return newInsets;
		}
	}

	/**
	 * Border for a Frame.
	 * 
	 * @since 1.4
	 */
	@SuppressWarnings("serial")
	static class DialogBorder extends AbstractBorder implements UIResource
	{
		private static final int corner = 14;

		protected Color getActiveBackground()
		{
			return FamilyLookAndFeel.getPrimaryControlDarkShadow();
		}

		protected Color getActiveHighlight()
		{
			return FamilyLookAndFeel.getPrimaryControlShadow();
		}

		protected Color getActiveShadow()
		{
			return FamilyLookAndFeel.getPrimaryControlInfo();
		}

		protected Color getInactiveBackground()
		{
			return FamilyLookAndFeel.getControlDarkShadow();
		}

		protected Color getInactiveHighlight()
		{
			return FamilyLookAndFeel.getControlShadow();
		}

		protected Color getInactiveShadow()
		{
			return FamilyLookAndFeel.getControlInfo();
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w,
		        int h)
		{
			Color background;
			Color highlight;
			Color shadow;

			Window window = SwingUtilities.getWindowAncestor(c);
			if (window != null && window.isActive())
			{
				background = getActiveBackground();
				highlight = getActiveHighlight();
				shadow = getActiveShadow();
			}
			else
			{
				background = getInactiveBackground();
				highlight = getInactiveHighlight();
				shadow = getInactiveShadow();
			}

			g.setColor(background);
			// Draw outermost lines
			g.drawLine(x + 1, y + 0, x + w - 2, y + 0);
			g.drawLine(x + 0, y + 1, x + 0, y + h - 2);
			g.drawLine(x + w - 1, y + 1, x + w - 1, y + h - 2);
			g.drawLine(x + 1, y + h - 1, x + w - 2, y + h - 1);

			// Draw the bulk of the border
			for (int i = 1; i < 5; i++)
			{
				g.drawRect(x + i, y + i, w - (i * 2) - 1, h - (i * 2) - 1);
			}

			if ((window instanceof Dialog) && ((Dialog) window).isResizable())
			{
				g.setColor(highlight);
				// Draw the Long highlight lines
				g.drawLine(corner + 1, 3, w - corner, 3);
				g.drawLine(3, corner + 1, 3, h - corner);
				g.drawLine(w - 2, corner + 1, w - 2, h - corner);
				g.drawLine(corner + 1, h - 2, w - corner, h - 2);

				g.setColor(shadow);
				// Draw the Long shadow lines
				g.drawLine(corner, 2, w - corner - 1, 2);
				g.drawLine(2, corner, 2, h - corner - 1);
				g.drawLine(w - 3, corner, w - 3, h - corner - 1);
				g.drawLine(corner, h - 3, w - corner - 1, h - 3);
			}

		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			newInsets.set(5, 5, 5, 5);
			return newInsets;
		}
	}

	/**
	 * Border for an Error Dialog.
	 * 
	 * @since 1.4
	 */
	@SuppressWarnings("serial")
	static class ErrorDialogBorder extends DialogBorder implements UIResource
	{
		@Override
		protected Color getActiveBackground()
		{
			return UIManager.getColor("OptionPane.errorDialog.border.background");
		}
	}

	/**
	 * Border for a QuestionDialog. Also used for a JFileChooser and a
	 * JColorChooser..
	 * 
	 * @since 1.4
	 */
	@SuppressWarnings("serial")
	static class QuestionDialogBorder extends DialogBorder implements UIResource
	{
		@Override
		protected Color getActiveBackground()
		{
			return UIManager.getColor("OptionPane.questionDialog.border.background");
		}
	}

	/**
	 * Border for a Warning Dialog.
	 * 
	 * @since 1.4
	 */
	@SuppressWarnings("serial")
	static class WarningDialogBorder extends DialogBorder implements UIResource
	{
		@Override
		protected Color getActiveBackground()
		{
			return UIManager .getColor("OptionPane.warningDialog.border.background");
		}
	}

	/**
	 * Border for a Palette.
	 * 
	 * @since 1.3
	 */
	@SuppressWarnings("serial")
	public static class PaletteBorder extends AbstractBorder implements UIResource
	{
		int titleHeight = 0;

		@Override
		public void paintBorder(@SuppressWarnings("unused") Component c, Graphics g, int x, int y, int w, int h)
		{

			g.translate(x, y);
			g.setColor(FamilyLookAndFeel.getPrimaryControlDarkShadow());
			g.drawLine(0, 1, 0, h - 2);
			g.drawLine(1, h - 1, w - 2, h - 1);
			g.drawLine(w - 1, 1, w - 1, h - 2);
			g.drawLine(1, 0, w - 2, 0);
			g.drawRect(1, 1, w - 3, h - 3);
			g.translate(-x, -y);

		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			newInsets.set(1, 1, 1, 1);
			return newInsets;
		}
	}

	@SuppressWarnings("serial")
	public static class OptionDialogBorder extends AbstractBorder
	        implements UIResource
	{
		int titleHeight = 0;

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{

			g.translate(x, y);

			int messageType = JOptionPane.PLAIN_MESSAGE;
			if (c instanceof JInternalFrame)
			{
				Object obj = ((JInternalFrame) c).getClientProperty("JInternalFrame.messageType");
				if (obj instanceof Integer)
					messageType = (Integer) obj;
			}

			Color borderColor;

			switch(messageType)
			{
				case (JOptionPane.ERROR_MESSAGE):
					borderColor = UIManager.getColor(
					        "OptionPane.errorDialog.border.background");
					break;
				case (JOptionPane.QUESTION_MESSAGE):
					borderColor = UIManager.getColor(
					        "OptionPane.questionDialog.border.background");
					break;
				case (JOptionPane.WARNING_MESSAGE):
					borderColor = UIManager.getColor(
					        "OptionPane.warningDialog.border.background");
					break;
				case (JOptionPane.INFORMATION_MESSAGE):
				case (JOptionPane.PLAIN_MESSAGE):
				default:
					borderColor = FamilyLookAndFeel
					        .getPrimaryControlDarkShadow();
					break;
			}

			g.setColor(borderColor);

			// Draw outermost lines
			g.drawLine(1, 0, w - 2, 0);
			g.drawLine(0, 1, 0, h - 2);
			g.drawLine(w - 1, 1, w - 1, h - 2);
			g.drawLine(1, h - 1, w - 2, h - 1);

			// Draw the bulk of the border
			for (int i = 1; i < 3; i++)
				g.drawRect(i, i, w - (i * 2) - 1, h - (i * 2) - 1);

			g.translate(-x, -y);

		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			newInsets.set(3, 3, 3, 3);
			return newInsets;
		}
	}

	@SuppressWarnings("serial")
	public static class MenuBarBorder extends AbstractBorder implements UIResource
	{
		protected static Insets borderInsets = new Insets(1, 0, 1, 0);

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			g.translate(x, y);

			if (FamilyLookAndFeel.usingOcean())
			{
				// Only paint a border if we're not next to a horizontal toolbar
				if (c instanceof JMenuBar && !FamilyToolBarUI.doesMenuBarBorderToolBar((JMenuBar) c))
				{
					g.setColor(FamilyLookAndFeel.getControl());
					SwingUtilities2.drawHLine(g, 0, w - 1, h - 2);
					g.setColor(UIManager.getColor("MenuBar.borderColor"));
					SwingUtilities2.drawHLine(g, 0, w - 1, h - 1);
				}
			}
			else
			{
				g.setColor(FamilyLookAndFeel.getControlShadow());
				SwingUtilities2.drawHLine(g, 0, w - 1, h - 1);
			}
			g.translate(-x, -y);
		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			if (FamilyLookAndFeel.usingOcean())
				newInsets.set(0, 0, 2, 0);
			else
				newInsets.set(1, 0, 1, 0);
			return newInsets;
		}
	}

	@SuppressWarnings("serial")
	public static class MenuItemBorder extends AbstractBorder implements UIResource
	{
		protected static Insets borderInsets = new Insets(2, 2, 2, 2);

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			if (!(c instanceof JMenuItem))
				return;
			JMenuItem b = (JMenuItem) c;
			ButtonModel model = b.getModel();

			g.translate(x, y);

			if (c.getParent() instanceof JMenuBar)
			{
				if (model.isArmed() || model.isSelected())
				{
					g.setColor(FamilyLookAndFeel.getControlDarkShadow());
					g.drawLine(0, 0, w - 2, 0);
					g.drawLine(0, 0, 0, h - 1);
					g.drawLine(w - 2, 2, w - 2, h - 1);

					g.setColor(FamilyLookAndFeel.getPrimaryControlHighlight());
					g.drawLine(w - 1, 1, w - 1, h - 1);

					g.setColor(FamilyLookAndFeel.getMenuBackground());
					g.drawLine(w - 1, 0, w - 1, 0);
				}
			}
			else
			{
				if (model.isArmed() || (c instanceof JMenu && model.isSelected()))
				{
					g.setColor(FamilyLookAndFeel.getPrimaryControlDarkShadow());
					g.drawLine(0, 0, w - 1, 0);

					g.setColor(FamilyLookAndFeel.getPrimaryControlHighlight());
					g.drawLine(0, h - 1, w - 1, h - 1);
				}
				else
				{
					g.setColor(FamilyLookAndFeel.getPrimaryControlHighlight());
					g.drawLine(0, 0, 0, h - 1);
				}
			}

			g.translate(-x, -y);
		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			newInsets.set(2, 2, 2, 2);
			return newInsets;
		}
	}

	@SuppressWarnings("serial")
	public static class PopupMenuBorder extends AbstractBorder
	        implements UIResource
	{
		protected static Insets borderInsets = new Insets(3, 1, 2, 1);

		@Override
		public void paintBorder(@SuppressWarnings("unused") Component c, Graphics g, int x, int y, int w,
		        int h)
		{
			g.translate(x, y);

			g.setColor(FamilyLookAndFeel.getPrimaryControlDarkShadow());
			g.drawRect(0, 0, w - 1, h - 1);

			g.setColor(FamilyLookAndFeel.getPrimaryControlHighlight());
			g.drawLine(1, 1, w - 2, 1);
			g.drawLine(1, 2, 1, 2);
			g.drawLine(1, h - 2, 1, h - 2);

			g.translate(-x, -y);

		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets newInsets)
		{
			newInsets.set(3, 1, 2, 1);
			return newInsets;
		}
	}

	@SuppressWarnings("serial")
	public static class RolloverButtonBorder extends ButtonBorder
	{

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			AbstractButton b = (AbstractButton) c;
			ButtonModel model = b.getModel();

			if (model.isRollover() && !(model.isPressed() && !model.isArmed()))
				super.paintBorder(c, g, x, y, w, h);
		}

	}

	/**
	 * A border which is like a Margin border but it will only honor the margin
	 * if the margin has been explicitly set by the developer.
	 *
	 * Note: This is identical to the package private class
	 * BasicBorders.RolloverMarginBorder and should probably be consolidated.
	 */
	@SuppressWarnings("serial")
	static class RolloverMarginBorder extends EmptyBorder
	{
		public RolloverMarginBorder()
		{
			super(3, 3, 3, 3); // hardcoded margin for JLF requirements.
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets)
		{
			Insets margin = null;

			if (c instanceof AbstractButton)
				margin = ((AbstractButton) c).getMargin();
			if (margin == null || margin instanceof UIResource)
			{
				// default margin so replace
				insets.left = this.left;
				insets.top = this.top;
				insets.right = this.right;
				insets.bottom = this.bottom;
			}
			else
			{
				// Margin which has been explicitly set by the user.
				insets.left = margin.left;
				insets.top = margin.top;
				insets.right = margin.right;
				insets.bottom = margin.bottom;
			}
			return insets;
		}
	}

	@SuppressWarnings("serial")
	public static class ToolBarBorder extends AbstractBorder implements UIResource, SwingConstants
	{
		protected FamilyBumps bumps = new FamilyBumps(10, 10,FamilyLookAndFeel.getControlHighlight(),
		        FamilyLookAndFeel.getControlDarkShadow(), UIManager.getColor("ToolBar.background"));

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			if (!(c instanceof JToolBar))
				return;
			g.translate(x, y);

			if (((JToolBar) c).isFloatable())
			{
				if (((JToolBar) c).getOrientation() == HORIZONTAL)
				{
					int shift = FamilyLookAndFeel.usingOcean() ? -1 : 0;
					this.bumps.setBumpArea(10, h - 4);
					if (FamilyUtils.isLeftToRight(c))
						this.bumps.paintIcon(c, g, 2, 2 + shift);
					else
						this.bumps.paintIcon(c, g, w - 12, 2 + shift);
				}
				else // vertical
				{
					this.bumps.setBumpArea(w - 4, 10);
					this.bumps.paintIcon(c, g, 2, 2);
				}
			}

			if (((JToolBar) c).getOrientation() == HORIZONTAL && FamilyLookAndFeel.usingOcean())
			{
				g.setColor(FamilyLookAndFeel.getControl());
				g.drawLine(0, h - 2, w, h - 2);
				g.setColor(UIManager.getColor("ToolBar.borderColor"));
				g.drawLine(0, h - 1, w, h - 1);
			}

			g.translate(-x, -y);
		}

		@Override
		public Insets getBorderInsets(Component c, Insets newInsets)
		{
			if (FamilyLookAndFeel.usingOcean())
				newInsets.set(1, 2, 3, 2);
			else
				newInsets.top = newInsets.left = newInsets.bottom = newInsets.right = 2;

			if (!(c instanceof JToolBar))
				return newInsets;
			if (((JToolBar) c).isFloatable())
				if (((JToolBar) c).getOrientation() == HORIZONTAL)
					if (c.getComponentOrientation().isLeftToRight())
						newInsets.left = 16;
					else
						newInsets.right = 16;
				else
					// vertical
					newInsets.top = 16;

			Insets margin = ((JToolBar) c).getMargin();

			if (margin != null)
			{
				newInsets.left += margin.left;
				newInsets.top += margin.top;
				newInsets.right += margin.right;
				newInsets.bottom += margin.bottom;
			}

			return newInsets;
		}
	}

	private static Border buttonBorder;

	/**
	 * Returns a border instance for a JButton
	 * 
	 * @since 1.3
	 */
	public static Border getButtonBorder()
	{
		if (buttonBorder == null)
			buttonBorder = new BorderUIResource.CompoundBorderUIResource(new FamilyBorders.ButtonBorder(), new BasicBorders.MarginBorder());
		return buttonBorder;
	}

	private static Border textBorder;

	/**
	 * Returns a border instance for a text component
	 * 
	 * @since 1.3
	 */
	public static Border getTextBorder()
	{
		if (textBorder == null)
			textBorder = new BorderUIResource.CompoundBorderUIResource( new FamilyBorders.Flush3DBorder(), new BasicBorders.MarginBorder());
		return textBorder;
	}

	private static Border textFieldBorder;

	/**
	 * Returns a border instance for a JTextField
	 * 
	 * @since 1.3
	 */
	public static Border getTextFieldBorder()
	{
		if (textFieldBorder == null)
			textFieldBorder = new BorderUIResource.CompoundBorderUIResource( new FamilyBorders.TextFieldBorder(), new BasicBorders.MarginBorder());
		return textFieldBorder;
	}

	@SuppressWarnings("serial")
	public static class TextFieldBorder extends Flush3DBorder
	{
		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			if (!(c instanceof JTextComponent))
			{
				// special case for non-text components (bug ID 4144840)
				if (c.isEnabled())
					FamilyUtils.drawFlush3DBorder(g, x, y, w, h);
				else
					FamilyUtils.drawDisabledBorder(g, x, y, w, h);
				return;
			}

			if (c.isEnabled() && ((JTextComponent) c).isEditable())
				FamilyUtils.drawFlush3DBorder(g, x, y, w, h);
			else
				FamilyUtils.drawDisabledBorder(g, x, y, w, h);
		}
	}

	@SuppressWarnings("serial")
	public static class ScrollPaneBorder extends AbstractBorder implements UIResource
	{
		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			if (!(c instanceof JScrollPane))
				return;
			JScrollPane scroll = (JScrollPane) c;
			JComponent colHeader = scroll.getColumnHeader();
			int colHeaderHeight = 0;
			if (colHeader != null)
				colHeaderHeight = colHeader.getHeight();

			JComponent rowHeader = scroll.getRowHeader();
			int rowHeaderWidth = 0;
			if (rowHeader != null)
				rowHeaderWidth = rowHeader.getWidth();

			g.translate(x, y);

			g.setColor(FamilyLookAndFeel.getControlDarkShadow());
			g.drawRect(0, 0, w - 2, h - 2);
			g.setColor(FamilyLookAndFeel.getControlHighlight());

			g.drawLine(w - 1, 1, w - 1, h - 1);
			g.drawLine(1, h - 1, w - 1, h - 1);

			g.setColor(FamilyLookAndFeel.getControl());
			g.drawLine(w - 2, 2 + colHeaderHeight, w - 2, 2 + colHeaderHeight);
			g.drawLine(1 + rowHeaderWidth, h - 2, 1 + rowHeaderWidth, h - 2);

			g.translate(-x, -y);
		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets insets)
		{
			insets.set(1, 1, 2, 2);
			return insets;
		}
	}

	private static Border toggleButtonBorder;

	/**
	 * Returns a border instance for a JToggleButton
	 * 
	 * @since 1.3
	 */
	public static Border getToggleButtonBorder()
	{
		if (toggleButtonBorder == null)
			toggleButtonBorder = new BorderUIResource.CompoundBorderUIResource(new FamilyBorders.ToggleButtonBorder(), new BasicBorders.MarginBorder());
		return toggleButtonBorder;
	}

	/**
	 * @since 1.3
	 */
	@SuppressWarnings("serial")
	public static class ToggleButtonBorder extends ButtonBorder
	{
		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{
			AbstractButton button = (AbstractButton) c;
			ButtonModel model = button.getModel();
			if (FamilyLookAndFeel.usingOcean())
			{
				if (model.isArmed() || !button.isEnabled())
					super.paintBorder(c, g, x, y, w, h);
				else
				{
					g.setColor(FamilyLookAndFeel.getControlDarkShadow());
					g.drawRect(0, 0, w - 1, h - 1);
				}
				return;
			}
			if (!c.isEnabled())
				FamilyUtils.drawDisabledBorder(g, x, y, w - 1, h - 1);
			else
			{
				if (model.isPressed() && model.isArmed())
					FamilyUtils.drawPressed3DBorder(g, x, y, w, h);
				else if (model.isSelected())
					FamilyUtils.drawDark3DBorder(g, x, y, w, h);
				else
					FamilyUtils.drawFlush3DBorder(g, x, y, w, h);
			}
		}
	}

	/**
	 * Border for a Table Header
	 * 
	 * @since 1.3
	 */
	@SuppressWarnings("serial")
	public static class TableHeaderBorder extends javax.swing.border.AbstractBorder
	{
		protected Insets editorBorderInsets = new Insets(2, 2, 2, 0);

		@Override
		public void paintBorder(@SuppressWarnings("unused") Component c, Graphics g, int x, int y, int w, int h)
		{
			g.translate(x, y);

			g.setColor(FamilyLookAndFeel.getControlDarkShadow());
			g.drawLine(w - 1, 0, w - 1, h - 1);
			g.drawLine(1, h - 1, w - 1, h - 1);
			g.setColor(FamilyLookAndFeel.getControlHighlight());
			g.drawLine(0, 0, w - 2, 0);
			g.drawLine(0, 0, 0, h - 2);

			g.translate(-x, -y);
		}

		@Override
		public Insets getBorderInsets(@SuppressWarnings("unused") Component c, Insets insets)
		{
			insets.set(2, 2, 2, 0);
			return insets;
		}
	}

	/**
	 * Returns a border instance for a Desktop Icon
	 * 
	 * @since 1.3
	 */
	public static Border getDesktopIconBorder()
	{
		return new BorderUIResource.CompoundBorderUIResource(
		        new LineBorder(FamilyLookAndFeel.getControlDarkShadow(), 1),
		        new MatteBorder(2, 2, 1, 2, FamilyLookAndFeel.getControl()));
	}

	static Border getToolBarRolloverBorder()
	{
		if (FamilyLookAndFeel.usingOcean()) { return new CompoundBorder(
		        new FamilyBorders.ButtonBorder(),
		        new FamilyBorders.RolloverMarginBorder()); }
		return new CompoundBorder(new FamilyBorders.RolloverButtonBorder(),
		        new FamilyBorders.RolloverMarginBorder());
	}

	static Border getToolBarNonrolloverBorder()
	{
		if (FamilyLookAndFeel.usingOcean())
			new CompoundBorder(new FamilyBorders.ButtonBorder(),
			        new FamilyBorders.RolloverMarginBorder());
		return new CompoundBorder(new FamilyBorders.ButtonBorder(),
		        new FamilyBorders.RolloverMarginBorder());
	}
}
