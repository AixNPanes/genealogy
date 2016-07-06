package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcUserReferenceTypeAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in REFN
 * 
 * n TYPE <USER_REFERENCE_TYPE>					{0:1} 
 */

public class GcUserReferenceTypeLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("USER_REFERENCE_TYPE", 0, 1, GcUserReferenceTypeAttribute.class)
		});
	}

	public GcUserReferenceTypeLine(GcBaseElement e)
	{
		super(e, "TYPE", getAttributeMap());
	}
};
