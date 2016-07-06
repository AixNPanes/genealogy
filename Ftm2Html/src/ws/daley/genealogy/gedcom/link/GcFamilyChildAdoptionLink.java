package ws.daley.genealogy.gedcom.link;

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
 * used in ADOP
 * 
 * n FAMC @<XREF:FAM>@						{0:1} 
 * 	+1 ADOP <ADOPTED_BY_WHICH_PARENT>		{0:1} 
 */

public class GcFamilyChildAdoptionLink extends Gc_Link
{
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("ADOP", 0, 1, GcFamilyChildLink.class)
		});
	}
	public GcFamilyChildAdoptionLink(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "FAMC", null, map, _vector);
	}
};
