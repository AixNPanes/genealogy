package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcGenerationsOfAncestorsAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n ANCE <GENERATIONS_OF_ANCESTORS>
 */

public class GcGenerationsOfAncestorsLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("GENERATIONS_OF_ANCESTORS", 0, 1, GcGenerationsOfAncestorsAttribute.class)
		});
	}
	public GcGenerationsOfAncestorsLine(GcBaseElement e)
	{
		super(e, "ANCE", getAttributeMap());
	}
};
