package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcCauseOfEventAttribute;
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
 * n CAUS <CAUSE_OF_EVENT>						{0:1} 
 */

public class GcCauseLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("CAUSE_OF_EVENT", 0, 1, GcCauseOfEventAttribute.class),
		});
	}

	public GcCauseLine(GcBaseElement e)
	{
		super(e, "CAUS", getAttributeMap());
	}
};
