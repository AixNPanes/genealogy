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
 * used in TEXT 
 * n [CONC | CONT ] <TEXT_FROM_SOURCE>		{0:M} 
 */

public class GcAuthorContLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("TEXT_FROM_SOURCE", 0, 1, GcSourceOriginatorAttribute.class)
		});
	}
	
	public GcAuthorContLine(GcBaseElement e)
	{
		super(e, "CONT", getAttributeMap());
	}
};
