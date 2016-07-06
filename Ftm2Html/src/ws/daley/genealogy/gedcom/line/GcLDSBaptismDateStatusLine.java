package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcLdsBaptismDateStatusAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n STAT <LDS_BAPTISM_DATE_STATUS> 
 */

public class GcLDSBaptismDateStatusLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("LDS_BAPTISM_DATE_STATUS", 1, 1, GcLdsBaptismDateStatusAttribute.class)
		});
	}

	public GcLDSBaptismDateStatusLine(GcBaseElement e)
	{
		super(e, "STAT", getAttributeMap());
	}
};
