package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcFamilyEventDescriptorAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/
/**
 * n TYPE <EVENT_DESCRIPTOR>
 */

public class GcFamilyEventTypeLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("EVENT_DESCRIPTOR", 0, 1, GcFamilyEventDescriptorAttribute.class),
		});
	}

	protected GcFamilyEventTypeLine(GcBaseElement e, String tag)
	{
		super(e, tag, getAttributeMap());
	}
};
