package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcNameOfRepositoryAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in REPOSITORY_RECORD
 * 
 * n NAME <NAME_OF_REPOSITORY> 	{0:1} 
 */

public class GcRepositoryNameLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_OF_REPOSITORY", 0, 1, GcNameOfRepositoryAttribute.class)
		});
	}

	public GcRepositoryNameLine(GcBaseElement e)
	{
		super(e, "NAME", getAttributeMap());
	}
};
