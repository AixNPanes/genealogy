package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcUserReferenceNumberAttribute;
import ws.daley.genealogy.gedcom.line.GcUserReferenceTypeLine;
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
 * used in MULTIMEDIA_LINK
 * 
 * n REFN <USER_REFERENCE_NUMBER>					{0:M}
 * 	+1 TYPE <USER_REFERENCE_TYPE>					{0:1} 
 */

public class GcUserReferenceNumberStructure extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	@SuppressWarnings("hiding")
    private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("TYPE", 0, 1, GcUserReferenceTypeLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("USER_REFERENCE_NUMBER", 0, 1, GcUserReferenceNumberAttribute.class)
		});
	}
	
	public GcUserReferenceNumberStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "REFN", attributeDescriptorMap, map, _vector);
	}
};
