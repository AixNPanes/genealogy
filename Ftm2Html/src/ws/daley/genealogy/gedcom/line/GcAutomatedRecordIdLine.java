package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcAutomatedRecordIdAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n RIN <AUTOMATED_RECORD_ID>
 */

public class GcAutomatedRecordIdLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("AUTOMATED_RECORD_ID", 0, 1, GcAutomatedRecordIdAttribute.class),
		});
	}

	public GcAutomatedRecordIdLine(GcBaseElement e)
	{
		super(e, "RIN", getAttributeMap());
	}
};
