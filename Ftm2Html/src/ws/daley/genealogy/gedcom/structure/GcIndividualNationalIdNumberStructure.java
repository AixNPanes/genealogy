package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcNationalIdNumberAttribute;
import ws.daley.genealogy.gedcom.attribute.family.Gc_AttributeFamily;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n IDNO <NATIONAL_ID_NUMBER> 						{1:1}*	p.52 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 */

public class GcIndividualNationalIdNumberStructure extends Gc_AttributeFamily
{
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NATIONAL_ID_NUMBER", 1, 1, GcNationalIdNumberAttribute.class)
		});
	}
	
	public GcIndividualNationalIdNumberStructure() {}
	
	public GcIndividualNationalIdNumberStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "IDNO", attributeDescriptorMap, null, _vector);
	}
};
