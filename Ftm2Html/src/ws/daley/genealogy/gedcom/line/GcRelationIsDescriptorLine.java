package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcRelationIsDescriptorAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in ASSOCIATION_STRUCTURE:= 
 * n RELA <RELATION_IS_DESCRIPTOR>	{1:1}
 */

public class GcRelationIsDescriptorLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RELATION_IS_DESCRIPTOR", 1, 1, GcRelationIsDescriptorAttribute.class)
		});
	}

	public GcRelationIsDescriptorLine(GcBaseElement e)
	{
		super(e, "RELA", getAttributeMap());
	}
};
