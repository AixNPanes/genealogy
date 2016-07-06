package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcEntryRecordingDateAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n DATE <ENTRY_RECORDING_DATE>				{0:1} 
 */

public class GcSourceCitationDataDateLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ENTRY_RECORDING_DATE", 0, 1, GcEntryRecordingDateAttribute.class),
		});
	}
	
	public GcSourceCitationDataDateLine(GcBaseElement e)
	{
		super(e, "DATE", getAttributeMap());
	}
};
