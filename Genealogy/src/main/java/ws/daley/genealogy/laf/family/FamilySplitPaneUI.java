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

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 * Family split pane.
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
public class FamilySplitPaneUI extends BasicSplitPaneUI
{

	/**
	 * Creates a new FamilySplitPaneUI instance
	 */
	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent x)
	{
		return new FamilySplitPaneUI();
	}

	/**
	 * Creates the default divider.
	 */
	@Override
	public BasicSplitPaneDivider createDefaultDivider()
	{
		return new FamilySplitPaneDivider(this);
	}
}
