package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcNameOfBusinessAttribute;
import ws.daley.genealogy.gedcom.line.GcPhoneLine;
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
 * used in SOUR
 * 
 * n CORP <NAME_OF_BUSINESS>					{0:1}	
 *     +1 <<ADDRESS_STRUCTURE>>					{0:1}
 *     
 * <<ADDRESS_STRUCTURE>>:=    
 *     	
 * n ADDR <ADDRESS_LINE>						{0:1}	
 *     +1 CONT <ADDRESS_LINE>					{0:M}	
 *     +1 ADR1 <ADDRESS_LINE1>					{0:1}	
 *     +1 ADR2 <ADDRESS_LINE2>					{0:1}	
 *     +1 CITY <ADDRESS_CITY>					{0:1}	
 *     +1 STAE <ADDRESS_STATE>					{0:1}	
 *     +1 POST <ADDRESS_POSTAL_CODE>			{0:1}	
 *     +1 CTRY <ADDRESS_COUNTRY>				{0:1}	
 * n PHON <PHONE_NUMBER>						(0:3)
 */

public class GcCorporationStructure extends Gc_Structure
{
	public String corporationName;
	
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("ADDR", 0, 1, GcAddressStructure.class),
				new TagDescriptor("PHON", 0, 1, GcPhoneLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_OF_BUSINESS", 0, 1, GcNameOfBusinessAttribute.class),
		});
	}

	public GcCorporationStructure() {}
	
	public GcCorporationStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "CORP", attributeDescriptorMap, tagDescriptorMap, _vector);

		this.corporationName = getParameters();
	}
	
	public GcAddressStructure getAddressStructure() {return (GcAddressStructure)this.getElementForKey("ADDR");}
	public String getPhone() {return this.getParameterForKey("PHON");}
};
