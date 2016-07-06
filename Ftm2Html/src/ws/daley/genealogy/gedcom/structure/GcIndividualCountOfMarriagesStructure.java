package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcCountOfMarriagesAttribute;
import ws.daley.genealogy.gedcom.attribute.family.Gc_AttributeFamily;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n NMR <COUNT_OF_MARRIAGES> 						{1:1}	p.43 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 */

public class GcIndividualCountOfMarriagesStructure extends Gc_AttributeFamily
{
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("COUNT_OF_MARRIAGES", 1, 1, GcCountOfMarriagesAttribute.class)
		});
	}
	
	public GcIndividualCountOfMarriagesStructure() {}
	
	public GcIndividualCountOfMarriagesStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "NMR", attributeDescriptorMap, null, _vector);
	}
};
