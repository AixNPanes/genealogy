package ws.daley.genealogy.gedcom.structure.text;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.line.text.Gc_TextLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.Gc_Structure;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n TEXT <TEXT_FROM_SOURCE>						{0:M} 
 * 	+1 [CONC | CONT ] <TEXT_FROM_SOURCE>		{0:M} 
 */

public class Gc_TextStructure extends Gc_Structure
{
	public Gc_TextStructure(GcBaseElement e, String type, AttributeDescriptorMap attributeDescriptorMap, TagDescriptorMap map, Vector<GcBaseElement> _vector)
	{
		super(e, type, attributeDescriptorMap, map, _vector);
	}
	
	public String getText()
	{
		String text = getParameters();
		for(GcBaseElement element:getVectorMap().get("CONC"))
		{
			text += " ";
			text += ((Gc_TextLine)element).getText();
		}
		for(GcBaseElement element:getVectorMap().get("CONT"))
		{
			text += "\n";
			text += ((Gc_TextLine)element).getText();
		}
		return text;
	}
};
