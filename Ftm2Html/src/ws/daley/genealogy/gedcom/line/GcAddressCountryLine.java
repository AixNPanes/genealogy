package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcAddressCountryAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in ADDR
 *     	
 * n CTRY <ADDRESS_COUNTRY>
 */

public class GcAddressCountryLine extends Gc_Line
{
	@SuppressWarnings("hiding")
    private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_COUNTRY", 0, 1, GcAddressCountryAttribute.class),
		});
	}
	
	public GcAddressCountryLine(GcBaseElement e)
	{
		super(e, "CTRY", attributeDescriptorMap);
	}
};
