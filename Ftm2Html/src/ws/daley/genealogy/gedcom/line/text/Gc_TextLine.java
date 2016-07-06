package ws.daley.genealogy.gedcom.line.text;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.line.Gc_Line;
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

public class Gc_TextLine extends Gc_Line
{
	public Gc_TextLine(GcBaseElement e, String type, AttributeDescriptorMap attributeDescriptorMap)
	{
		super(e, type, attributeDescriptorMap);
	}
	
	public String getText() {return this.getParameters();}
};
