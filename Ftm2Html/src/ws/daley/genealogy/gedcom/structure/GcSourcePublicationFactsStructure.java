package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourcePublicationFactsAttribute;
import ws.daley.genealogy.gedcom.attribute.family.Gc_AttributeFamily;
import ws.daley.genealogy.gedcom.line.text.GcSourcePublicationFactsConcLine;
import ws.daley.genealogy.gedcom.line.text.GcSourcePublicationFactsContLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n PUBL <SOURCE_PUBLICATION_FACTS>				{0:1}
 * 	+1 [CONT|CONC] <SOURCE_PUBLICATION_FACTS>	{0:M}
 */

public class GcSourcePublicationFactsStructure extends Gc_AttributeFamily
{
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("CONC", 0, 1, GcSourcePublicationFactsConcLine.class),
				new TagDescriptor("CONC", 0, 1, GcSourcePublicationFactsContLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_PUBLICATION_FACTS", 0, 1, GcSourcePublicationFactsAttribute.class)
		});
	}
	
	public GcSourcePublicationFactsStructure() {}
	
	public GcSourcePublicationFactsStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "PUBL", attributeDescriptorMap, tagDescriptorMap, _vector);
	}
};
