package ws.daley.genealogy.gedcom.line.text;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcTextFromSourceAttribute;
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

public class GcTextFromSourceConcLine extends Gc_TextLine
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("TEXT_FROM_SOURCE", 0, 1, GcTextFromSourceAttribute.class)
		});
	}
	
	public GcTextFromSourceConcLine(GcBaseElement e)
	{
		super(e, "CONC", getAttributeMap());
	}
};
