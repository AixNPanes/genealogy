package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcNamePiecePrefixAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n NPFX <NAME_PIECE_PREFIX>
 */

public class GcNamePieceSurnamePrefixLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_PIECE_PREFIX", 1, 1, GcNamePiecePrefixAttribute.class)
		});
	}

	public GcNamePieceSurnamePrefixLine(GcBaseElement e)
	{
		super(e, "NPFX", getAttributeMap());
	}
};
