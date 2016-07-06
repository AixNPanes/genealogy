package ws.daley.genealogy.gedcom.link;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.line.GcFamilyRelationshipLine;
import ws.daley.genealogy.gedcom.line.GcMarriageRelationshipLine;
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
 * used in FAM_RECORD
 * 
 * n CHIL @<XREF:INDI>@						{0:M} 
 */

public class GcChildLink extends Gc_Link
{
	@SuppressWarnings("hiding")
    private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();

	static
	{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("_FREL", 0, 1, GcFamilyRelationshipLine.class),
				new TagDescriptor("_MREL", 0, 1, GcMarriageRelationshipLine.class)
		});
	}
	
	public GcChildLink(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "CHIL", (AttributeDescriptorMap)null, tagDescriptorMap, _vector);
	}
};
