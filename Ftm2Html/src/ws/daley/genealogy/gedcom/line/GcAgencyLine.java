package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcResponsibleAgencyAttribute;
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
 * n AGNC <RESPONSIBLE_AGENCY>					{0:1} 
 */

public class GcAgencyLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RESPONSIBLE_AGENCY", 0, 1, GcResponsibleAgencyAttribute.class),
		});
	}

	public GcAgencyLine(GcBaseElement e)
	{
		super(e, "AGNC", getAttributeMap());
	}
};
