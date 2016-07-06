package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourceOriginatorAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n AUTH <SOURCE_ORIGINATOR>  {0:1}
 * 	+1 [CONT|CONC] <SOURCE_ORIGINATOR>  {0:M}
 */

public class GcAuthorConcLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_ORIGINATOR", 0, 1, GcSourceOriginatorAttribute.class)
		});
	}
	
	public GcAuthorConcLine(GcBaseElement e)
	{
		super(e, "CONC", getAttributeMap());
	}
};
