package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcNameOfProductAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in SOUR	
 *  n NAME <NAME_OF_PRODUCT>						{0:1}	
 */

public class GcSourceNameLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_OF_PRODUCT", 0, 1, GcNameOfProductAttribute.class)
		});
	}
	
	public GcSourceNameLine(GcBaseElement e)
	{
		super(e, "NAME", getAttributeMap());
	}
};
