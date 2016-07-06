package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcGenerationsOfDescendantsAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n DESC <GENERATIONS_OF_DESCENDANTS>
 */

public class GcGenerationsOfDescendantsLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("GENERATIONS_OF_DESCENDANTS", 0, 1, GcGenerationsOfDescendantsAttribute.class)
		});
	}

	public GcGenerationsOfDescendantsLine(GcBaseElement e)
	{
		super(e, "DESC", getAttributeMap());
	}
};
