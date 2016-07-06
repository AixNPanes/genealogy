package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcCountOfChildrenAttribute;
import ws.daley.genealogy.gedcom.attribute.family.Gc_AttributeFamily;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n NCHI <COUNT_OF_CHILDREN> 						{1:1}	p.43 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 */

public class GcIndividualCountOfChildrenStructure extends Gc_AttributeFamily
{
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("COUNT_OF_CHILDREN", 1, 1, GcCountOfChildrenAttribute.class)
		});
	}
	
	public GcIndividualCountOfChildrenStructure() {}
	
	public GcIndividualCountOfChildrenStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "NCHI", attributeDescriptorMap, null, _vector);
	}
};
