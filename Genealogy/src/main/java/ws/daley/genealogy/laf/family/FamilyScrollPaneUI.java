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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;

/**
 * A Family L&amp;F implementation of ScrollPaneUI.
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
public class FamilyScrollPaneUI extends BasicScrollPaneUI
{

	private PropertyChangeListener scrollBarSwapListener;

	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent x)
	{
		return new FamilyScrollPaneUI();
	}

	@Override
	public void installUI(JComponent c)
	{

		super.installUI(c);

		@SuppressWarnings("unused")
		JScrollPane sp = (JScrollPane) c;
		updateScrollbarsFreeStanding();
	}

	@Override
	public void uninstallUI(JComponent c)
	{
		super.uninstallUI(c);

		JScrollPane sp = (JScrollPane) c;
		JScrollBar hsb = sp.getHorizontalScrollBar();
		JScrollBar vsb = sp.getVerticalScrollBar();
		if (hsb != null)
			hsb.putClientProperty(FamilyScrollBarUI.FREE_STANDING_PROP, null);
		if (vsb != null)
			vsb.putClientProperty(FamilyScrollBarUI.FREE_STANDING_PROP, null);
	}

	@Override
	public void installListeners(JScrollPane scrollPane)
	{
		super.installListeners(scrollPane);
		this.scrollBarSwapListener = createScrollBarSwapListener();
		scrollPane.addPropertyChangeListener(this.scrollBarSwapListener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void uninstallListeners(JComponent c)
	{
		super.uninstallListeners(c);
		c.removePropertyChangeListener(this.scrollBarSwapListener);
	}

	/**
	 * @deprecated - Replaced by {@link #uninstallListeners(JComponent)}
	 */
	@Deprecated
	public void uninstallListeners(JScrollPane scrollPane)
	{
		super.uninstallListeners(scrollPane);
		scrollPane.removePropertyChangeListener(this.scrollBarSwapListener);
	}

	/**
	 * If the border of the scrollpane is an instance of
	 * <code>FamilyBorders.ScrollPaneBorder</code>, the client property
	 * <code>FREE_STANDING_PROP</code> of the scrollbars is set to false,
	 * otherwise it is set to true.
	 */
	private void updateScrollbarsFreeStanding()
	{
		if (this.scrollpane == null)
			return;
		Border border = this.scrollpane.getBorder();
		Object value;

		if (border instanceof FamilyBorders.ScrollPaneBorder)
			value = Boolean.FALSE;
		else
			value = Boolean.TRUE;
		JScrollBar sb = this.scrollpane.getHorizontalScrollBar();
		if (sb != null)
			sb.putClientProperty(FamilyScrollBarUI.FREE_STANDING_PROP, value);
		sb = this.scrollpane.getVerticalScrollBar();
		if (sb != null)
			sb.putClientProperty(FamilyScrollBarUI.FREE_STANDING_PROP, value);
	}

	protected PropertyChangeListener createScrollBarSwapListener()
	{
		return new PropertyChangeListener()
		{
			@SuppressWarnings("synthetic-access")
			@Override
			public void propertyChange(PropertyChangeEvent e)
			{
				String propertyName = e.getPropertyName();
				if (propertyName.equals("verticalScrollBar") || propertyName.equals("horizontalScrollBar"))
				{
					JScrollBar oldSB = (JScrollBar) e.getOldValue();
					if (oldSB != null)
						oldSB.putClientProperty(FamilyScrollBarUI.FREE_STANDING_PROP, null);
					JScrollBar newSB = (JScrollBar) e.getNewValue();
					if (newSB != null)
						newSB.putClientProperty(FamilyScrollBarUI.FREE_STANDING_PROP, Boolean.FALSE);
				}
				else if ("border".equals(propertyName))
					updateScrollbarsFreeStanding();
			}
		};
	}
}
