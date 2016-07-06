package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcReligiousAffiliationAttribute;
import ws.daley.genealogy.gedcom.attribute.family.Gc_AttributeFamily;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n RELI <RELIGIOUS_AFFILIATION> 					{1:1}	p.54 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 */

public class GcIndividualReligiousAffiliationStructure extends Gc_AttributeFamily
{
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static
	{
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RELIGIOUS_AFFILIATION", 1, 1, GcReligiousAffiliationAttribute.class)
		});
	}
	
	public GcIndividualReligiousAffiliationStructure() {}
	
	public GcIndividualReligiousAffiliationStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "RELI", attributeDescriptorMap, null, _vector);
	}
};
