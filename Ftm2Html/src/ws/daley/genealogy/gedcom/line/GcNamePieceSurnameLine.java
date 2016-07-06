package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcNamePieceSurnameAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n SURN <NAME_PIECE_SURNAME>
 */

public class GcNamePieceSurnameLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_PIECE_SURNAME", 1, 1, GcNamePieceSurnameAttribute.class)
		});
	}

	public GcNamePieceSurnameLine(GcBaseElement e)
	{
		super(e, "SURN", getAttributeMap());
	}
};
