package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcEventTypeCitedFromAttribute;
import ws.daley.genealogy.gedcom.line.GcRoleInEventLine;
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
 * used in SOURCE_CITATION
 * 
 * n EVEN <EVENT_TYPE_CITED_FROM>					{0:1} 
 * 	+1 ROLE <ROLE_IN_EVENT>						{0:1} 
 */

public class GcEventTypeCitedFromStructure extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("ROLE", 0, 1, GcRoleInEventLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("EVENT_TYPE_CITED_FROM", 0, 1, GcEventTypeCitedFromAttribute.class)
		});
	}
	
	public GcEventTypeCitedFromStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "DATA", attributeDescriptorMap, map, _vector);
	}
};
