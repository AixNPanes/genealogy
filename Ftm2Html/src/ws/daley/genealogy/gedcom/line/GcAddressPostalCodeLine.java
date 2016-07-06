package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcAddressPostalCodeAttribute;
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
 * n POST <ADDRESS_POSTAL_CODE>
 */

public class GcAddressPostalCodeLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_POSTAL_CODE", 0, 1, GcAddressPostalCodeAttribute.class),
		});
	}
	
	public GcAddressPostalCodeLine(GcBaseElement e)
	{
		super(e, "POST", getAttributeMap());
	}
};
