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

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;

import sun.awt.AppContext;

/**
 * CheckboxUI implementation for FamilyCheckboxUI
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Michael C. Albers
 *
 */
public class FamilyCheckBoxUI extends FamilyRadioButtonUI
{

	// NOTE: FamilyCheckBoxUI inherts from FamilyRadioButtonUI instead
	// of BasicCheckBoxUI because we want to pick up all the
	// painting changes made in FamilyRadioButtonUI.

	private static final Object FAMILY_CHECK_BOX_UI_KEY = new Object();

	private final static String propertyPrefix = "CheckBox" + ".";

	private boolean defaults_initialized = false;

	// ********************************
	// Create PlAF
	// ********************************
	public static ComponentUI createUI(@SuppressWarnings("unused") JComponent b)
	{
		AppContext appContext = AppContext.getAppContext();
		FamilyCheckBoxUI checkboxUI = (FamilyCheckBoxUI) appContext.get(FAMILY_CHECK_BOX_UI_KEY);
		if (checkboxUI == null)
		{
			checkboxUI = new FamilyCheckBoxUI();
			appContext.put(FAMILY_CHECK_BOX_UI_KEY, checkboxUI);
		}
		return checkboxUI;
	}

	@Override
	public String getPropertyPrefix()
	{
		return propertyPrefix;
	}

	// ********************************
	// Defaults
	// ********************************
	@Override
	public void installDefaults(AbstractButton b)
	{
		super.installDefaults(b);
		if (!this.defaults_initialized)
		{
			this.icon = UIManager.getIcon(getPropertyPrefix() + "icon");
			this.defaults_initialized = true;
		}
	}

	@Override
	protected void uninstallDefaults(AbstractButton b)
	{
		super.uninstallDefaults(b);
		this.defaults_initialized = false;
	}
}
