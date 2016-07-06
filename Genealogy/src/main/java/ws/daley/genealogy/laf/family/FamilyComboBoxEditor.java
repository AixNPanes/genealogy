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

import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 * The default editor for Family editable combo boxes
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
public class FamilyComboBoxEditor extends BasicComboBoxEditor
{
	@SuppressWarnings("serial")
	public FamilyComboBoxEditor()
	{
		super();
		// editor.removeFocusListener(this);
		this.editor = new JTextField("", 9)
		{
			// workaround for 4530952
			@Override
			public void setText(String s)
			{
				if (getText().equals(s))
					return;
				super.setText(s);
			}

			// The preferred and minimum sizes are overriden and padded by
		    // 4 to keep the size as it previously was. Refer to bugs
		    // 4775789 and 4517214 for details.
			@Override
			public Dimension getPreferredSize()
			{
				Dimension pref = super.getPreferredSize();
				pref.height += 4;
				return pref;
			}

			@Override
			public Dimension getMinimumSize()
			{
				Dimension min = super.getMinimumSize();
				min.height += 4;
				return min;
			}
		};

		this.editor.setBorder(new EditorBorder());
		// editor.addFocusListener(this);
	}

	/**
	 * The default editor border <code>Insets</code>. This field might not be
	 * used.
	 */
	protected static Insets editorBorderInsets = new Insets(2, 2, 2, 0);

	@SuppressWarnings("serial")
	class EditorBorder extends AbstractBorder
	{
		@Override
		public void paintBorder(@SuppressWarnings("unused") Component c, Graphics g, int x, int y, int w, int h)
		{
			g.translate(x, y);

			if (FamilyLookAndFeel.usingOcean())
			{
				g.setColor(FamilyLookAndFeel.getControlDarkShadow());
				g.drawRect(0, 0, w, h - 1);
				g.setColor(FamilyLookAndFeel.getControlShadow());
				g.drawRect(1, 1, w - 2, h - 3);
			}
			else
			{
				g.setColor(FamilyLookAndFeel.getControlDarkShadow());
				g.drawLine(0, 0, w - 1, 0);
				g.drawLine(0, 0, 0, h - 2);
				g.drawLine(0, h - 2, w - 1, h - 2);
				g.setColor(FamilyLookAndFeel.getControlHighlight());
				g.drawLine(1, 1, w - 1, 1);
				g.drawLine(1, 1, 1, h - 1);
				g.drawLine(1, h - 1, w - 1, h - 1);
				g.setColor(FamilyLookAndFeel.getControl());
				g.drawLine(1, h - 2, 1, h - 2);
			}

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
	 * A subclass of BasicComboBoxEditor that implements UIResource.
	 * BasicComboBoxEditor doesn't implement UIResource directly so that
	 * applications can safely override the cellRenderer property with
	 * BasicListCellRenderer subclasses.
	 * <p>
	 * <strong>Warning:</strong> Serialized objects of this class will not be
	 * compatible with future Swing releases. The current serialization support
	 * is appropriate for short term storage or RMI between applications running
	 * the same version of Swing. As of 1.4, support for long term storage of
	 * all JavaBeans&trade; has been added to the <code>java.beans</code>
	 * package. Please see {@link java.beans.XMLEncoder}.
	 */
	public static class UIResource extends FamilyComboBoxEditor implements javax.swing.plaf.UIResource
	{}
}
