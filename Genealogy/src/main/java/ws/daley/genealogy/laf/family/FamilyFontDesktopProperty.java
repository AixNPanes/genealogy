/*
 * Copyright (c) 2001, 2009, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.Font;

/**
 * DesktopProperty that only uses font height in configuring font. This
 * is only used on Windows.
 *
 */
class FamilyFontDesktopProperty extends com.sun.java.swing.plaf.windows.DesktopProperty {
    /**
     * Maps from Family font theme level as defined in FamilyTheme
     * to the corresponding desktop property name.
     */
    private static final String[] propertyMapping = {
        "win.ansiVar.font.height",
        "win.tooltip.font.height",
        "win.ansiVar.font.height",
        "win.menu.font.height",
        "win.frame.captionFont.height",
        "win.menu.font.height"
    };

    /**
     * Corresponds to a FamilyTheme font level.
     */
    private int type;


    /**
     * Creates a FamilyFontDesktopProperty. The key used to lookup the
     * desktop property is determined from the level of font.
     *
     * @param level FamilyTheme font level.
     */
    FamilyFontDesktopProperty(int type)
    {
        this(propertyMapping[type], type);
    }

    /**
     * Creates a FamilyFontDesktopProperty.
     *
     * @param key Key used in looking up desktop value.
     * @param toolkit Toolkit used to fetch property from, can be null
     *        in which default will be used.
     * @param level Type of font being used, corresponds to FamilyTheme font
     *        level.
     */
    FamilyFontDesktopProperty(String key, int type)
    {
        super(key, null);
        this.type = type;
    }

    /**
     * Overriden to create a Font with the size coming from the desktop
     * and the style and name coming from DefaultFamilyTheme.
     */
    @Override
	protected Object configureValue(Object value)
    {
        if (value instanceof Integer)
            value = new Font(DefaultFamilyTheme.getDefaultFontName(this.type), DefaultFamilyTheme.getDefaultFontStyle(this.type), ((Integer)value).intValue());
        return super.configureValue(value);
    }

    /**
     * Returns the default font.
     */
    @Override
	protected Object getDefaultValue() {
        return new Font(DefaultFamilyTheme.getDefaultFontName(this.type),
                DefaultFamilyTheme.getDefaultFontStyle(this.type),
		        DefaultFamilyTheme.getDefaultFontSize(this.type));
    }
}
