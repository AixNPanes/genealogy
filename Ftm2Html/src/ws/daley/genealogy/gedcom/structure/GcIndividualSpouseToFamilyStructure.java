package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

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
 * used in SPOUSE_TO_FAMILY_LINK 
 * n FAMS @<XREF:FAM>@	{1:1}	p.57 
 * +1 <<NOTE_STRUCTURE>>	{0:M}	p.37
 */

public class GcIndividualSpouseToFamilyStructure extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class)
		});
	}
	
	public GcIndividualSpouseToFamilyStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "FAMS", null, map, _vector);
	}
};
