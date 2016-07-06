package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSocialSecurityNumberAttribute;
import ws.daley.genealogy.gedcom.attribute.family.Gc_AttributeFamily;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n SSN <SOCIAL_SECURITY_NUMBER> 					{0:1}	p.55 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 */

public class GcIndividualSocialSecurityNumberStructure extends Gc_AttributeFamily
{
	public GcIndividualSocialSecurityNumberStructure() {}
	@SuppressWarnings("hiding")
    private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static
	{
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOCIAL_SECURITY_NUMBER", 0, 1, GcSocialSecurityNumberAttribute.class)
		});
	}
	
	public GcIndividualSocialSecurityNumberStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "SSN", attributeDescriptorMap, null, _vector);
	}
};
