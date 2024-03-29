package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcRestrictionNoticeAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in INDIVIDUAL_RECORD
 * n RESN <RESTRICTION_NOTICE>			{0:1} 
 */

public class GcRestrictionNoticeLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RESTRICTION_NOTICE", 0, 1, GcRestrictionNoticeAttribute.class)
		});
	}
	
	public GcRestrictionNoticeLine(GcBaseElement e)
	{
		super(e, "RESN", getAttributeMap());
	}
};
