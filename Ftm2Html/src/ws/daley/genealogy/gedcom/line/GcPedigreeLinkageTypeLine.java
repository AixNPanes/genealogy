package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcPedigreeLinkageTypeAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in FAMC 
 * n PEDI <PEDIGREE_LINKAGE_TYPE>	{0:M}
 */

public class GcPedigreeLinkageTypeLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PEDIGREE_LINKAGE_TYPE", 0, Integer.MAX_VALUE, GcPedigreeLinkageTypeAttribute.class)
		});
	}

	public GcPedigreeLinkageTypeLine(GcBaseElement e)
	{
		super(e, "PEDI", getAttributeMap());
	}
};
