package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcPermanentRecordFileNumberAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in INDIVIDUAL_RECORD
 * n RFN <PERMANENT_RECORD_FILE_NUMBER>	{0:1} 
 */

public class GcPermanentRecordFileNumberLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PERMANENT_RECORD_FILE_NUMBER", 0, 1, GcPermanentRecordFileNumberAttribute.class)
		});
	}

	public GcPermanentRecordFileNumberLine(GcBaseElement e)
	{
		super(e, "RFN", getAttributeMap());
	}
};
