package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcAncestralFileNumberAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in INDIVIDUAL_RECORD
 * n AFN <ANCESTRAL_FILE_NUMBER>			{0:1}
 */

public class GcAncestralFileNumberLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ANCESTRAL_FILE_NUMBER", 0, 1, GcAncestralFileNumberAttribute.class),
		});
	}

	public GcAncestralFileNumberLine(GcBaseElement e)
	{
		super(e, "AFN", getAttributeMap());
	}
};
