package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcDateLdsOrdinanceAttribute;
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
 * n DATE <DATE_LDS_ORD>						{0:1} 
 */

public class GcDateLDSOrdinanceLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("DATE_LDS_ORD", 0, 1, GcDateLdsOrdinanceAttribute.class),
		});
	}
	
	public GcDateLDSOrdinanceLine(GcBaseElement e)
	{
		super(e, "DATE", getAttributeMap());
	}
};
