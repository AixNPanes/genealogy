package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcMultimediaFormatAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in MULTIMEDIA_LINK
 * 
 * n FORM <MULTIMEDIA_FORMAT>						{1:1} 
 */

public class GcMultimediaFormatLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("MULTIMEDIA_FORMAT", 1, 1, GcMultimediaFormatAttribute.class)
		});
	}

	public GcMultimediaFormatLine(GcBaseElement e)
	{
		super(e, "FORM", getAttributeMap());
	}
};
