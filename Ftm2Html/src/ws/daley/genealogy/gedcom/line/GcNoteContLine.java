package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcCountOfChildrenAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n [CONT|CONC] <GEDCOM_CONTENT_DESCRIPTION>	{0:M}
 */

public class GcNoteContLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("COUNT_OF_CHILDREN", 1, 1, GcCountOfChildrenAttribute.class)
		});
	}

	public GcNoteContLine(GcBaseElement e)
	{
		super(e, "CONT", getAttributeMap());
	}
};
