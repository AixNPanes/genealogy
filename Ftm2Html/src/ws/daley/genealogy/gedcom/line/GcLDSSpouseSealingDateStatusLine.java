package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcLdsSpouseSealingDateStatusAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in LDS_SPOUSE_SEALING
 *  
 * n STAT <LDS_SPOUSE_SEALING_DATE_STATUS>	{0:1} 
 */

public class GcLDSSpouseSealingDateStatusLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("LDS_SPOUSE_SEALING_DATE_STATUS", 1, 1, GcLdsSpouseSealingDateStatusAttribute.class)
		});
	}

	public GcLDSSpouseSealingDateStatusLine(GcBaseElement e)
	{
		super(e, "STAT", getAttributeMap());
	}
};
