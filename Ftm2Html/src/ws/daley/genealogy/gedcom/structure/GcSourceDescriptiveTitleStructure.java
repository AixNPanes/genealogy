package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourceDescriptiveTitleAttribute;
import ws.daley.genealogy.gedcom.line.text.GcSourceDescriptiveTitleConcLine;
import ws.daley.genealogy.gedcom.line.text.GcSourceDescriptiveTitleContLine;
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
 * n TITL <SOURCE_DESCRIPTIVE_TITLE>  {0:1}
 * 	+1 [CONT|CONC] <SOURCE_DESCRIPTIVE_TITLE>  {0:M}
 */

public class GcSourceDescriptiveTitleStructure extends Gc_Structure
{
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("CONC", 0, Integer.MAX_VALUE, GcSourceDescriptiveTitleConcLine.class),
				new TagDescriptor("CONT", 0, Integer.MAX_VALUE, GcSourceDescriptiveTitleContLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_DESCRIPTIVE_TITLE", 0, 1, GcSourceDescriptiveTitleAttribute.class)
		});
	}
	
	public GcSourceDescriptiveTitleStructure() {}
	
	public GcSourceDescriptiveTitleStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "TITL", attributeDescriptorMap, tagDescriptorMap, _vector);
	}
};
