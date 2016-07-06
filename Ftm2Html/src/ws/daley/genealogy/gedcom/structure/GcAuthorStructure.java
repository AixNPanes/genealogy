package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourceOriginatorAttribute;
import ws.daley.genealogy.gedcom.line.GcAuthorConcLine;
import ws.daley.genealogy.gedcom.line.GcAuthorContLine;
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
 * n AUTH <SOURCE_ORIGINATOR>			{0:1}
 * 	+1 [CONT|CONC] <SOURCE_ORIGINATOR>	{0:M}
 */

public class GcAuthorStructure extends Gc_Structure
{
	private static TagDescriptorMap getTagMap()
	{
		return TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("CONC", 0, Integer.MAX_VALUE, GcAuthorConcLine.class),
				new TagDescriptor("CONT", 0, Integer.MAX_VALUE, GcAuthorContLine.class)
		});
	}
	
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_ORIGINATOR", 0, 1, GcSourceOriginatorAttribute.class)
		});
	}
	
	public GcAuthorStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "CONT", getAttributeMap(), getTagMap(), _vector);
	}
};
