package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcTempleCodeAttribute;
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
 * n TEMP <TEMPLE_CODE>						{0:1}
 */

public class GcTempleCodeLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("TEMPLE_CODE", 0, 1, GcTempleCodeAttribute.class)
		});
	}

	public GcTempleCodeLine(GcBaseElement e)
	{
		super(e, "TEMP", getAttributeMap());
	}
};
