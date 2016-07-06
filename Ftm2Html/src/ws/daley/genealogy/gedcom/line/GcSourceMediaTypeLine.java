package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourceMediaTypeAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n MEDI <SOURCE_MEDIA_TYPE>		{0:1}
 */

public class GcSourceMediaTypeLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_MEDIA_TYPE", 0, 1, GcSourceMediaTypeAttribute.class)
		});
	}
	
	public GcSourceMediaTypeLine(GcBaseElement e)
	{
		super(e, "MEDI", getAttributeMap());
	}
};
