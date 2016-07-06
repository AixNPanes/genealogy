package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcPlaceLivingOrdinanceAttribute;
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
 * n PLAC <PLACE_LIVING_ORDINANCE>			{0:1} 
 */

public class GcPlaceLivingOrdinanceLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PLACE_LIVING_ORDINANCE", 0, 1, GcPlaceLivingOrdinanceAttribute.class)
		});
	}

	public GcPlaceLivingOrdinanceLine(GcBaseElement e)
	{
		super(e, "PLAC", getAttributeMap());
	}
};
