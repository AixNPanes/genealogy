package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcPlaceHierarchyAttribute;
import ws.daley.genealogy.gedcom.line.GcFormPlaceHierarchyLine;
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
 * PLACE_STRUCTURE:= 
 * n PLAC <PLACE_VALUE>								{1:1} 
 * 	+1 FORM <PLACE_HIERARCHY>						{0:1} 
 * 	+1 <<SOURCE_CITATION>>							{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M}
 */

public class GcPlaceStructureFamilyEvent extends Gc_Structure
{
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("FORM", 1, 1, GcFormPlaceHierarchyLine.class),
			new TagDescriptor("SOUR", 1, 1, GcSourceCitationStructure.class),
			new TagDescriptor("NOTE", 1, 1, GcNotePlaceStructure.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
			new AttributeDescriptor("PLACE_VALUE", 1, 1, GcPlaceHierarchyAttribute.class)
		});
	}

	public GcPlaceStructureFamilyEvent(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "PLAC", attributeDescriptorMap, tagDescriptorMap, _vector);
	}
};
