package ws.daley.genealogy.gedcom.line.text;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourcePublicationFactsAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n [CONT|CONC] <SOURCE_PUBLICATION_FACTS>	{0:M}
 */

public class GcSourcePublicationFactsConcLine extends Gc_TextLine
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_DESCRIPTION", 0, 1, GcSourcePublicationFactsAttribute.class)
		});
	}
	
	public GcSourcePublicationFactsConcLine(GcBaseElement e)
	{
		super(e, "CONC", getAttributeMap());
	}
};
