package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcEncodedMultimediaLineAttribute;
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
 * n CONT <ENCODED_MULTIMEDIA_LINE>			{1:M} 
 */

public class GcEncodedMultimediaLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ENCODED_MULTIMEDIA_LINE", 0, 1, GcEncodedMultimediaLineAttribute.class),
		});
	}

	public GcEncodedMultimediaLine(GcBaseElement e)
	{
		super(e, "CONT", getAttributeMap());
	}
};
