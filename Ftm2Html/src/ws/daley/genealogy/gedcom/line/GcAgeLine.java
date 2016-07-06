package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcAgeAtEventAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in FAMILY_EVENT_STRUCTURE:= 
 *
 * n AGE <AGE_AT_EVENT>							{0:1}
 */

public class GcAgeLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("AGE_AT_EVENT", 0, 1, GcAgeAtEventAttribute.class)
		});
	}
	
	public GcAgeLine(GcBaseElement e)
	{
		super(e, "AGE", getAttributeMap());
	}
};
