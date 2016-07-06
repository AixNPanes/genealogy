package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcPhoneNumberAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in <<ADDRESS_STRUCTURE>>    
 *     	
 * n PHON <PHONE_NUMBER>						(0:3)
 */

public class GcPhoneLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PHONE_NUMBER", 0, 1, GcPhoneNumberAttribute.class),
		});
	}

	public GcPhoneLine(GcBaseElement e)
	{
		super(e, "PHON", getAttributeMap());
	}
};
