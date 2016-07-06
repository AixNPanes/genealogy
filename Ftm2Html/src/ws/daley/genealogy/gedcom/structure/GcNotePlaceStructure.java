package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSubmitterTextAttribute;
import ws.daley.genealogy.gedcom.line.GcNoteConcLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * NOTE_STRUCTURE:= 
 * [ 
 * n NOTE @<XREF:NOTE>@								{1:1} 
 * 	+1 <<SOURCE_CITATION>>							{0:M}
 * | 
 * n NOTE [SUBMITTER_TEXT> | <NULL>]				{1:1} 
 * 	+1 [ CONC | CONT ] <SUBMITTER_TEXT>				{0:M} 
 * 	+1 <<SOURCE_CITATION>>							{0:M}
 * ]
 */

public class GcNotePlaceStructure extends Gc_Structure
{
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("CONC", 0, 1, GcNoteConcLine.class),
				new TagDescriptor("CONT", 0, 1, GcNoteConcLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SUBMITTER_TEXT", 0, 1, GcSubmitterTextAttribute.class)
		});
	}
	
	public GcNotePlaceStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "NOTE", attributeDescriptorMap, tagDescriptorMap, _vector);
		throw new RuntimeException("implementation");
	}
};
