package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcPedigreeLinkageTypeLine;
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
 * used in CHILD_TO_FAMILY_LINK:= 
 * n FAMC @<XREF:FAM>@	{1:1} 
 * 	+1 PEDI <PEDIGREE_LINKAGE_TYPE>	{0:M}	p.52 
 * 	+1 <<NOTE_STRUCTURE>>	{0:M}	p.37
 */

public class GcIndividualChildToFamilyStructure extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("PEDI", 0, 1, GcPedigreeLinkageTypeLine.class),
			new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class)
		});
	}
	
	public GcIndividualChildToFamilyStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "FAMC", null, map, _vector);
	}
};
