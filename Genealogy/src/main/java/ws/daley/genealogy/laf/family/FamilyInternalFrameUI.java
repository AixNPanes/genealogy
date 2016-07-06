/*
 * Copyright (c) 1998, 2009, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 * Family implementation of JInternalFrame.
 * <p>
 *
 * @author Steve Wilson
 */
public class FamilyInternalFrameUI extends BasicInternalFrameUI
{

	@SuppressWarnings("synthetic-access")
	private static final PropertyChangeListener familyPropertyChangeListener = new FamilyPropertyChangeHandler();

	private static final Border handyEmptyBorder = new EmptyBorder(0, 0, 0, 0);

	protected static String IS_PALETTE = "JInternalFrame.isPalette";
	private static String IS_PALETTE_KEY = "JInternalFrame.isPalette";
	private static String FRAME_TYPE = "JInternalFrame.frameType";
	@SuppressWarnings("unused")
	private static String NORMAL_FRAME = "normal";
	private static String PALETTE_FRAME = "palette";
	private static String OPTION_DIALOG = "optionDialog";

	public FamilyInternalFrameUI(JInternalFrame b)
	{
		super(b);
	}

	public static ComponentUI createUI(JComponent c)
	{
		return new FamilyInternalFrameUI((JInternalFrame) c);
	}

	@Override
	public void installUI(JComponent c)
	{
		super.installUI(c);

		Object paletteProp = c.getClientProperty(IS_PALETTE_KEY);
		if (paletteProp != null)
			setPalette(((Boolean) paletteProp).booleanValue());

		Container content = this.frame.getContentPane();
		stripContentBorder(content);
		// c.setOpaque(false);
	}

	@Override
	public void uninstallUI(JComponent c)
	{
		this.frame = (JInternalFrame) c;

		Container cont = ((JInternalFrame) (c)).getContentPane();
		if (cont instanceof JComponent)
		{
			JComponent content = (JComponent) cont;
			if (content.getBorder() == handyEmptyBorder) content.setBorder(null);
		}
		super.uninstallUI(c);
	}

	@Override
	protected void installListeners()
	{
		super.installListeners();
		this.frame.addPropertyChangeListener(familyPropertyChangeListener);
	}

	@Override
	protected void uninstallListeners()
	{
		this.frame.removePropertyChangeListener(familyPropertyChangeListener);
		super.uninstallListeners();
	}

	@Override
	protected void installKeyboardActions()
	{
		super.installKeyboardActions();
		ActionMap map = SwingUtilities.getUIActionMap(this.frame);
		if (map != null)
		    // BasicInternalFrameUI creates an action with the same name, we
		    // override
		    // it as Family frames do not have system menus.
		    map.remove("showSystemMenu");
	}

	@Override
	protected void uninstallKeyboardActions()
	{
		super.uninstallKeyboardActions();
	}

	@Override
	protected void uninstallComponents()
	{
		this.titlePane = null;
		super.uninstallComponents();
	}

	private void stripContentBorder(Object c)
	{
		if (c instanceof JComponent)
		{
			JComponent contentComp = (JComponent) c;
			Border contentBorder = contentComp.getBorder();
			if (contentBorder == null || contentBorder instanceof UIResource) contentComp.setBorder(handyEmptyBorder);
		}
	}

	@Override
	protected JComponent createNorthPane(JInternalFrame w)
	{
		return new FamilyInternalFrameTitlePane(w);
	}

	private void setFrameType(String frameType)
	{
		if (frameType.equals(OPTION_DIALOG))
		{
			LookAndFeel.installBorder(this.frame, "InternalFrame.optionDialogBorder");
			((FamilyInternalFrameTitlePane) this.titlePane).setPalette(false);
		}
		else if (frameType.equals(PALETTE_FRAME))
		{
			LookAndFeel.installBorder(this.frame, "InternalFrame.paletteBorder");
			((FamilyInternalFrameTitlePane) this.titlePane).setPalette(true);
		}
		else
		{
			LookAndFeel.installBorder(this.frame, "InternalFrame.border");
			((FamilyInternalFrameTitlePane) this.titlePane).setPalette(false);
		}
	}

	// this should be deprecated - jcs
	public void setPalette(boolean isPalette)
	{
		if (isPalette)
			LookAndFeel.installBorder(this.frame, "InternalFrame.paletteBorder");
		else LookAndFeel.installBorder(this.frame, "InternalFrame.border");
		((FamilyInternalFrameTitlePane) this.titlePane).setPalette(isPalette);

	}

	private static class FamilyPropertyChangeHandler implements PropertyChangeListener
	{
		@SuppressWarnings("synthetic-access")
		@Override
		public void propertyChange(PropertyChangeEvent e)
		{
			String name = e.getPropertyName();
			JInternalFrame jif = (JInternalFrame) e.getSource();

			if (!(jif.getUI() instanceof FamilyInternalFrameUI))
				return;

			FamilyInternalFrameUI ui = (FamilyInternalFrameUI) jif.getUI();

			if (name.equals(FRAME_TYPE))
			{
				if (e.getNewValue() instanceof String)
					ui.setFrameType((String) e.getNewValue());
			}
			else if (name.equals(IS_PALETTE_KEY))
				if (e.getNewValue() != null)
					ui.setPalette(((Boolean) e.getNewValue()).booleanValue());
				else
					ui.setPalette(false);
			else if (name.equals(JInternalFrame.CONTENT_PANE_PROPERTY))
				ui.stripContentBorder(e.getNewValue());
		}
	} // end class FamilyPropertyChangeHandler

	private class BorderListener1 extends BorderListener implements SwingConstants
	{

		Rectangle getIconBounds()
		{
			@SuppressWarnings("synthetic-access")
			boolean leftToRight = FamilyUtils.isLeftToRight(FamilyInternalFrameUI.this.frame);
			@SuppressWarnings("synthetic-access")
			int xOffset = leftToRight ? 5 : FamilyInternalFrameUI.this.titlePane.getWidth() - 5;
			Rectangle rect = null;

			@SuppressWarnings("synthetic-access")
			Icon icon = FamilyInternalFrameUI.this.frame.getFrameIcon();
			if (icon != null)
			{
				if (!leftToRight)
					xOffset -= icon.getIconWidth();
				@SuppressWarnings("synthetic-access")
				int iconY = ((FamilyInternalFrameUI.this.titlePane.getHeight() / 2) - (icon.getIconHeight() / 2));
				rect = new Rectangle(xOffset, iconY, icon.getIconWidth(), icon.getIconHeight());
			}
			return rect;
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount() == 2 && e.getSource() == getNorthPane()
			        && FamilyInternalFrameUI.this.frame.isClosable() && !FamilyInternalFrameUI.this.frame.isIcon())
			{
				Rectangle rect = getIconBounds();
				if ((rect != null) && rect.contains(e.getX(), e.getY()))
					FamilyInternalFrameUI.this.frame.doDefaultCloseAction();
				else
					super.mouseClicked(e);
			}
			else
				super.mouseClicked(e);
		}
	}; /// End BorderListener Class

	/**
	 * Returns the <code>MouseInputAdapter</code> that will be installed on the
	 * TitlePane.
	 *
	 * @param w
	 *            the <code>JInternalFrame</code>
	 * @return the <code>MouseInputAdapter</code> that will be installed on the
	 *         TitlePane.
	 * @since 1.6
	 */
	@SuppressWarnings("synthetic-access")
	@Override
	protected MouseInputAdapter createBorderListener(JInternalFrame w)
	{
		return new BorderListener1();
	}
}
