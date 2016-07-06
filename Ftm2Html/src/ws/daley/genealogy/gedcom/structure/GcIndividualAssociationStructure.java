package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcRelationIsDescriptorLine;
import ws.daley.genealogy.gedcom.line.GcTypeRecordLine;
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
 * used in ASSOCIATION_STRUCTURE:= 
 * n ASSO @<XREF:INDI>@	{0:M}	p.57 
 * 	+1 TYPE <RECORD_TYPE>	{1:1}	p.54 
 * 	+1 RELA <RELATION_IS_DESCRIPTOR>	{1:1}	p.54 
 * 	+1 <<NOTE_STRUCTURE>>	{0:M}	p.37 
 * 	+1 <<SOURCE_CITATION>>	{0:M}	p.37
 */

public class GcIndividualAssociationStructure extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("TYPE", 1, 1, GcTypeRecordLine.class),
			new TagDescriptor("RELA", 1, 1, GcRelationIsDescriptorLine.class),
			new TagDescriptor("NOTE", 0, Integer.MAX_VALUE, GcNoteStructure.class),
			new TagDescriptor("SOUR", 0, Integer.MAX_VALUE, GcSourceCitationStructure.class)
		});
	}
	
	public GcIndividualAssociationStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "ASSO", null, map, _vector);
	}
};
