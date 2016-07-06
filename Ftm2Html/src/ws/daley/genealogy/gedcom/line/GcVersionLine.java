package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcVersionNumberAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n VERS <VERSION_NUMBER>										{0:1} 
 */

public class GcVersionLine extends Gc_Line
{
	public static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("VERSION_NUMBER", 0, 1, GcVersionNumberAttribute.class)
		});
	}

	public GcVersionLine(GcBaseElement e)
	{
		super(e, "VERS", getAttributeMap());
	}
};
