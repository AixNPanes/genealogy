package ws.daley.genealogy.gedcom.line.text;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourceDescriptionAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in SOURCE_CITATION
 * 
 * n [ CONC | CONT ] <SOURCE_DESCRIPTION>			{0:M} 
 */

public class GcSourceDescriptionContLine extends Gc_TextLine
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_DESCRIPTION", 0, 1, GcSourceDescriptionAttribute.class)
		});
	}
	
	public GcSourceDescriptionContLine(GcBaseElement e)
	{
		super(e, "CONT", getAttributeMap());
	}
};
