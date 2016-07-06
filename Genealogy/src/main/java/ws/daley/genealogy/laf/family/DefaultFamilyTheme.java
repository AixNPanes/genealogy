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

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import sun.awt.AppContext;
import sun.security.action.GetPropertyAction;
import sun.swing.SwingUtilities2;

/**
 * A concrete implementation of {@code FamilyTheme} providing the original look
 * of the Java Look and Feel, code-named "Steel". Refer to
 * {@link FamilyLookAndFeel#setCurrentTheme} for details on changing the default
 * theme.
 * <p>
 * All colors returned by {@code DefaultFamilyTheme} are completely opaque.
 *
 * <h3><a name="fontStyle"></a>Font Style</h3>
 *
 * {@code DefaultFamilyTheme} uses bold fonts for many controls. To make all
 * controls (with the exception of the internal frame title bars and client
 * decorated frame title bars) use plain fonts you can do either of the
 * following:
 * <ul>
 * <li>Set the system property <code>swing.boldFamily</code> to
 * <code>false</code>. For example,
 * <code>java&nbsp;-Dswing.boldFamily=false&nbsp;MyApp</code>.
 * <li>Set the defaults property <code>swing.boldFamily</code> to
 * <code>Boolean.FALSE</code>. For example:
 * <code>UIManager.put("swing.boldFamily",&nbsp;Boolean.FALSE);</code>
 * </ul>
 * The defaults property <code>swing.boldFamily</code>, if set, takes precedence
 * over the system property of the same name. After setting this defaults
 * property you need to re-install <code>FamilyLookAndFeel</code>, as well as
 * update the UI of any previously created widgets. Otherwise the results are
 * undefined. The following illustrates how to do this:
 * 
 * <pre>
 * // turn off bold fonts
 * UIManager.put("swing.boldFamily", Boolean.FALSE);
 *
 * // re-install the Family Look and Feel
 * UIManager.setLookAndFeel(new FamilyLookAndFeel());
 *
 * // Update the ComponentUIs for all Components. This
 * // needs to be invoked for all windows.
 * SwingUtilities.updateComponentTreeUI(rootComponent);
 * </pre>
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of Swing. As of 1.4, support for long term storage of all
 * JavaBeans&trade; has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @see FamilyLookAndFeel
 * @see FamilyLookAndFeel#setCurrentTheme
 *
 * @author Steve Wilson
 */
public class DefaultFamilyTheme extends FamilyTheme
{
	/**
	 * Whether or not fonts should be plain. This is only used if the defaults
	 * property 'swing.boldFamily' == "false".
	 */
	private static final boolean PLAIN_FONTS;

	/**
	 * Names of the fonts to use.
	 */
	private static final String[] fontNames = { Font.DIALOG, Font.DIALOG, Font.DIALOG, Font.DIALOG, Font.DIALOG, Font.DIALOG };
	/**
	 * Styles for the fonts. This is ignored if the defaults property
	 * <code>swing.boldFamily</code> is false, or PLAIN_FONTS is true.
	 */
	private static final int[] fontStyles = { Font.BOLD, Font.PLAIN, Font.PLAIN, Font.BOLD, Font.BOLD, Font.PLAIN };
	/**
	 * Sizes for the fonts.
	 */
	private static final int[] fontSizes = { 12, 12, 12, 12, 12, 10 };

	// note the properties listed here can currently be used by people
	// providing runtimes to hint what fonts are good. For example the bold
	// dialog font looks bad on a Mac, so Apple could use this property to
	// hint at a good font.
	//
	// However, we don't promise to support these forever. We may move
	// to getting these from the swing.properties file, or elsewhere.
	/**
	 * System property names used to look up fonts.
	 */
	private static final String[] defaultNames = {
			"swing.plaf.family.controlFont",
			"swing.plaf.family.systemFont",
	        "swing.plaf.family.userFont",
	        "swing.plaf.family.controlFont",
	        "swing.plaf.family.controlFont",
	        "swing.plaf.family.smallFont" };

	/**
	 * Returns the ideal font name for the font identified by key.
	 */
	static String getDefaultFontName(int key)
	{
		return fontNames[key];
	}

	/**
	 * Returns the ideal font size for the font identified by key.
	 */
	static int getDefaultFontSize(int key)
	{
		return fontSizes[key];
	}

	/**
	 * Returns the ideal font style for the font identified by key.
	 */
	static int getDefaultFontStyle(int key)
	{
		if (key != WINDOW_TITLE_FONT)
		{
			Object boldFamily = null;
			if (AppContext.getAppContext() .get(SwingUtilities2.LAF_STATE_KEY) != null)
				// Only access the boldFamily key if a look and feel has
				// been loaded, otherwise we'll trigger loading the look
				// and feel.
				boldFamily = UIManager.get("swing.boldFamily");
			if (boldFamily != null)
			{
				if (Boolean.FALSE.equals(boldFamily))
					return Font.PLAIN;
			}
			else if (PLAIN_FONTS) 
				return Font.PLAIN;
		}
		return fontStyles[key];
	}

	/**
	 * Returns the default used to look up the specified font.
	 */
	static String getDefaultPropertyName(int key)
	{
		return defaultNames[key];
	}

	static
	{
		Object boldProperty = java.security.AccessController.doPrivileged(new GetPropertyAction("swing.boldFamily"));
		if (boldProperty == null || !"false".equals(boldProperty))
			PLAIN_FONTS = false;
		else
			PLAIN_FONTS = true;
	}

	private static final ColorUIResource primary1 = new ColorUIResource(102, 102, 153);
	private static final ColorUIResource primary2 = new ColorUIResource(153, 153, 204);
	private static final ColorUIResource primary3 = new ColorUIResource(204, 204, 255);
	private static final ColorUIResource secondary1 = new ColorUIResource(102, 102, 102);
	private static final ColorUIResource secondary2 = new ColorUIResource(153, 153, 153);
	private static final ColorUIResource secondary3 = new ColorUIResource(204, 204, 204);

	private FontDelegate fontDelegate;

	/**
	 * Returns the name of this theme. This returns {@code "Steel"}.
	 *
	 * @return the name of this theme.
	 */
	@Override
	public String getName()
	{
		return "Steel";
	}

	/**
	 * Creates and returns an instance of {@code DefaultFamilyTheme}.
	 */
	public DefaultFamilyTheme()
	{
		install();
	}

	/**
	 * Returns the primary 1 color. This returns a color with rgb values of 102,
	 * 102, and 153, respectively.
	 *
	 * @return the primary 1 color
	 */
	@Override
	protected ColorUIResource getPrimary1()
	{
		return primary1;
	}

	/**
	 * Returns the primary 2 color. This returns a color with rgb values of 153,
	 * 153, 204, respectively.
	 *
	 * @return the primary 2 color
	 */
	@Override
	protected ColorUIResource getPrimary2()
	{
		return primary2;
	}

	/**
	 * Returns the primary 3 color. This returns a color with rgb values 204,
	 * 204, 255, respectively.
	 *
	 * @return the primary 3 color
	 */
	@Override
	protected ColorUIResource getPrimary3()
	{
		return primary3;
	}

	/**
	 * Returns the secondary 1 color. This returns a color with rgb values 102,
	 * 102, and 102, respectively.
	 *
	 * @return the secondary 1 color
	 */
	@Override
	protected ColorUIResource getSecondary1()
	{
		return secondary1;
	}

	/**
	 * Returns the secondary 2 color. This returns a color with rgb values 153,
	 * 153, and 153, respectively.
	 *
	 * @return the secondary 2 color
	 */
	@Override
	protected ColorUIResource getSecondary2()
	{
		return secondary2;
	}

	/**
	 * Returns the secondary 3 color. This returns a color with rgb values 204,
	 * 204, and 204, respectively.
	 *
	 * @return the secondary 3 color
	 */
	@Override
	protected ColorUIResource getSecondary3()
	{
		return secondary3;
	}

	/**
	 * Returns the control text font. This returns Dialog, 12pt. If plain fonts
	 * have been enabled as described in <a href="#fontStyle"> font style</a>,
	 * the font style is plain. Otherwise the font style is bold.
	 *
	 * @return the control text font
	 */
	@Override
	public FontUIResource getControlTextFont()
	{
		return getFont(CONTROL_TEXT_FONT);
	}

	/**
	 * Returns the system text font. This returns Dialog, 12pt, plain.
	 *
	 * @return the system text font
	 */
	@Override
	public FontUIResource getSystemTextFont()
	{
		return getFont(SYSTEM_TEXT_FONT);
	}

	/**
	 * Returns the user text font. This returns Dialog, 12pt, plain.
	 *
	 * @return the user text font
	 */
	@Override
	public FontUIResource getUserTextFont()
	{
		return getFont(USER_TEXT_FONT);
	}

	/**
	 * Returns the menu text font. This returns Dialog, 12pt. If plain fonts
	 * have been enabled as described in <a href="#fontStyle"> font style</a>,
	 * the font style is plain. Otherwise the font style is bold.
	 *
	 * @return the menu text font
	 */
	@Override
	public FontUIResource getMenuTextFont()
	{
		return getFont(MENU_TEXT_FONT);
	}

	/**
	 * Returns the window title font. This returns Dialog, 12pt, bold.
	 *
	 * @return the window title font
	 */
	@Override
	public FontUIResource getWindowTitleFont()
	{
		return getFont(WINDOW_TITLE_FONT);
	}

	/**
	 * Returns the sub-text font. This returns Dialog, 10pt, plain.
	 *
	 * @return the sub-text font
	 */
	@Override
	public FontUIResource getSubTextFont()
	{
		return getFont(SUB_TEXT_FONT);
	}

	private FontUIResource getFont(int key)
	{
		return this.fontDelegate.getFont(key);
	}

	@Override
	void install()
	{
		if (FamilyLookAndFeel.isWindows() && FamilyLookAndFeel.useSystemFonts())
			this.fontDelegate = new WindowsFontDelegate();
		else
			this.fontDelegate = new FontDelegate();
	}

	/**
	 * Returns true if this is a theme provided by the core platform.
	 */
	@Override
	boolean isSystemTheme()
	{
		return (getClass() == DefaultFamilyTheme.class);
	}

	/**
	 * FontDelegates add an extra level of indirection to obtaining fonts.
	 */
	private static class FontDelegate
	{
		private static int[] defaultMapping = { CONTROL_TEXT_FONT, SYSTEM_TEXT_FONT, USER_TEXT_FONT, CONTROL_TEXT_FONT, CONTROL_TEXT_FONT, SUB_TEXT_FONT };
		FontUIResource fonts[];

		// menu and window are mapped to controlFont
		public FontDelegate()
		{
			this.fonts = new FontUIResource[6];
		}

		public FontUIResource getFont(int type)
		{
			int mappedType = defaultMapping[type];
			if (this.fonts[type] == null)
			{
				Font f = getPrivilegedFont(mappedType);

				if (f == null)
					f = new Font(getDefaultFontName(type), getDefaultFontStyle(type), getDefaultFontSize(type));
				this.fonts[type] = new FontUIResource(f);
			}
			return this.fonts[type];
		}

		/**
		 * This is the same as invoking <code>Font.getFont(key)</code>, with the
		 * exception that it is wrapped inside a <code>doPrivileged</code> call.
		 */
		protected Font getPrivilegedFont(final int key)
		{
			return java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Font>()
			        {
				        @Override
						public Font run()
				        {
					        return Font.getFont(getDefaultPropertyName(key));
				        }
			        });
		}
	}

	/**
	 * The WindowsFontDelegate uses DesktopProperties to obtain fonts.
	 */
	private static class WindowsFontDelegate extends FontDelegate
	{
		private FamilyFontDesktopProperty[] props;
		private boolean[] checkedPriviledged;

		public WindowsFontDelegate()
		{
			this.props = new FamilyFontDesktopProperty[6];
			this.checkedPriviledged = new boolean[6];
		}

		@Override
		public FontUIResource getFont(int type)
		{
			if (this.fonts[type] != null)
				return this.fonts[type];
			if (!this.checkedPriviledged[type])
			{
				Font f = getPrivilegedFont(type);

				this.checkedPriviledged[type] = true;
				if (f != null)
				{
					this.fonts[type] = new FontUIResource(f);
					return this.fonts[type];
				}
			}
			if (this.props[type] == null)
				this.props[type] = new FamilyFontDesktopProperty(type);
			// While passing null may seem bad, we don't actually use
			// the table and looking it up is rather expensive.
			return (FontUIResource) this.props[type].createValue(null);
		}
	}
}
