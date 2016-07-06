package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcMultimediaFileReferenceAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in MULTIMEDIA_LINK
 * 
 * n FILE <MULTIMEDIA_FILE_REFERENCE>				{1:1} 
 */

public class GcMultimediaFileReferenceLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("MULTIMEDIA_FILE_REFERENCE", 1, 1, GcMultimediaFileReferenceAttribute.class)
		});
	}

	public GcMultimediaFileReferenceLine(GcBaseElement e)
	{
		super(e, "FILE", getAttributeMap());
	}
};
