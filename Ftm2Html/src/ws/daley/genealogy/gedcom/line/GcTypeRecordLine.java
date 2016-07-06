package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcRecordTypeAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in ASSOCIATION_STRUCTURE:= 
 * n TYPE <RECORD_TYPE>	{1:1} 
 */

public class GcTypeRecordLine extends Gc_Line
{
	public static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RECORD_TYPE", 1, 1, GcRecordTypeAttribute.class)
		});
	}

	public GcTypeRecordLine(GcBaseElement e)
	{
		super(e, "TYPE", getAttributeMap());
	}
};
