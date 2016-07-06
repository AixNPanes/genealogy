package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcNameOfFamilyFileAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n FAMF <NAME_OF_FAMILY_FILE> 
 */

public class GcFamilyFileLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("", 0, 1, GcNameOfFamilyFileAttribute.class)
		});
	}
	
	public GcFamilyFileLine(GcBaseElement e)
	{
		super(e, "FAMF", getAttributeMap());
	}
};
