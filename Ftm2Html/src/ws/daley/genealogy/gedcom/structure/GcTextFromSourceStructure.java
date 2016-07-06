package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcTextFromSourceAttribute;
import ws.daley.genealogy.gedcom.line.text.GcTextFromSourceContLine;
import ws.daley.genealogy.gedcom.line.text.Gc_TextLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.text.Gc_TextStructure;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
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

public class GcTextFromSourceStructure extends Gc_TextStructure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	@SuppressWarnings("hiding")
    private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("CONC", 0, 1, Gc_TextLine.class),
				new TagDescriptor("CONT", 0, 1, GcTextFromSourceContLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("TEXT_FROM_SOURCE", 0, 1, GcTextFromSourceAttribute.class)
		});
	}
	
	public GcTextFromSourceStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "TEXT", attributeDescriptorMap, map, _vector);
	}
};
