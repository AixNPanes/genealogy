package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcOrdinanceProcessFlagAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n ORDI <ORDINANCE_PROCESS_FLAG>
 */

public class GcOrdinanceProcessFlagLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ORDINANCE_PROCESS_FLAG", 0, 1, GcOrdinanceProcessFlagAttribute.class)
		});
	}

	public GcOrdinanceProcessFlagLine(GcBaseElement e)
	{
		super(e, "ORDI", getAttributeMap());
	}
};
