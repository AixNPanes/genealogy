package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcGecdomFormAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in GEDC
 *   n FORM <GEDCOM_FORM>						{1:1}	
 */

public class GcFormPlaceHierarchyLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
			new AttributeDescriptor("GEDCOM_FORM", 1, 1, GcGecdomFormAttribute.class)
		});
	}

	public GcFormPlaceHierarchyLine(GcBaseElement e)
	{
		super(e, "FORM", getAttributeMap());
	}
};
