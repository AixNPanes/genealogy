package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcWhereWithinSourceAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in SOURCE_CITATION:=
 * 
 * n PAGE <WHERE_WITHIN_SOURCE>					{0:1} 
 */

public class GcPageLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("WHERE_WITHIN_SOURCE", 1, 1, GcWhereWithinSourceAttribute.class)
		});
	}

	public GcPageLine(GcBaseElement e)
	{
		super(e, "PAGE", getAttributeMap());
	}
};
