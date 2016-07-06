package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcCountOfChildrenAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in FAM_RECORD
 * 
 * n NCHI <COUNT_OF_CHILDREN>					{0:1} 
 */

public class GcNumberOfChildrenLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("COUNT_OF_CHILDREN", 1, 1, GcCountOfChildrenAttribute.class)
		});
	}

	public GcNumberOfChildrenLine(GcBaseElement e)
	{
		super(e, "NCHI", getAttributeMap());
	}
};
