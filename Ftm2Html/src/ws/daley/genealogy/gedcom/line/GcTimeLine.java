package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcTimeValueAttribute;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in DATE
 *
 * n TIME <TIME_VALUE>							{0:1}	
 */

public class GcTimeLine extends Gc_Line
{
	public String time;
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("TIME_VALUE", 0, 1, GcTimeValueAttribute.class)
		});
	}
	
	public GcTimeLine() {}
	
	public GcTimeLine(IGcBaseElement e)
	{
		super(e, "TIME", getAttributeMap());
	}
};
