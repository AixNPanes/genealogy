package ws.daley.genealogy.gedcom.line.text;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourceDescriptiveTitleAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n [CONT|CONC] <SOURCE_DESCRIPTIVE_TITLE>  {0:M}
 */

public class GcSourceDescriptiveTitleContLine extends Gc_TextLine
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_DESCRIPTIVE_TITLE", 1, 1, GcSourceDescriptiveTitleAttribute.class)
		});
	}

	public GcSourceDescriptiveTitleContLine(GcBaseElement e)
	{
		super(e, "CONT", getAttributeMap());
	}
};
