package ws.daley.genealogy.gedcom.line.text;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSubmitterTextAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * 	+1 [ CONC | CONT] <SUBMITTER_TEXT>									{0:M} 
 */

public class GcSubmitterTextContLine extends Gc_TextLine
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SUBMITTER_TEXT", 0, 1, GcSubmitterTextAttribute.class)
		});
	}
	
	public GcSubmitterTextContLine(GcBaseElement e)
	{
		super(e, "CONT", getAttributeMap());
	}
};
