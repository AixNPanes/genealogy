package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcScholasticAchievementAttribute;
import ws.daley.genealogy.gedcom.attribute.family.Gc_AttributeFamily;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n EDUC <SCHOLASTIC_ACHIEVEMENT> 					{1:1}	p.55 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 */

public class GcIndividualScholasticAchievementStructure extends Gc_AttributeFamily
{
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static
	{
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SCHOLASTIC_ACHIEVEMENT", 0, 1, GcScholasticAchievementAttribute.class)
		});
	}
	
	public GcIndividualScholasticAchievementStructure() {}
	
	public GcIndividualScholasticAchievementStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "EDUC", attributeDescriptorMap, null, _vector);
	}
};
