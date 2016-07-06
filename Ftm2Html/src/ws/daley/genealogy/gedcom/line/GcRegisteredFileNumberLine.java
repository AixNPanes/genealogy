package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSubmitterRegisteredRfnAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in SUBMITTER_RECORD
 * 
 * n RFN <SUBMITTER_REGISTERED_RFN>				{0:1} 
 */

public class GcRegisteredFileNumberLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SUBMITTER_REGISTERED_RFN", 0, 1, GcSubmitterRegisteredRfnAttribute.class)
		});
	}

	public GcRegisteredFileNumberLine(GcBaseElement e)
	{
		super(e, "RFN", getAttributeMap());
	}
};
