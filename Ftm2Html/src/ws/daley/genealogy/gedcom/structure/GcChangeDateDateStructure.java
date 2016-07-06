package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcChangeDateAttribute;
import ws.daley.genealogy.gedcom.line.GcTimeLine;
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
 * n DATE <CHANGE_DATE>												{1:1} 
 *  +1 TIME <TIME_VALUE>											{0:1} 
 */

public class GcChangeDateDateStructure extends Gc_Structure
{
	private static TagDescriptorMap getTagDescriptorMap()
	{
		return TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("TIME", 0, 1, GcTimeLine.class)
		});
	}
	
	private static AttributeDescriptorMap getAttributeDescriptorMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("CHANGE_DATE", 0, 1, GcChangeDateAttribute.class)
		});
	}

	public GcChangeDateDateStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "DATE", getAttributeDescriptorMap(), getTagDescriptorMap(), _vector);
	}
};
