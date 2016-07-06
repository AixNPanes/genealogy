package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcRoleInEventAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in EVEN 
 * n	ROLE <ROLE_IN_EVENT>						{0:1} 
 */

public class GcRoleInEventLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ROLE_IN_EVENT", 0, 1, GcRoleInEventAttribute.class)
		});
	}

	public GcRoleInEventLine(GcBaseElement e)
	{
		super(e, "ROLE", getAttributeMap());
	}
};
