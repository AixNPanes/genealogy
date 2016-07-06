package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcAddressLineAttribute;
import ws.daley.genealogy.gedcom.line.GcAddressCityLine;
import ws.daley.genealogy.gedcom.line.GcAddressCountryLine;
import ws.daley.genealogy.gedcom.line.GcAddressLine1;
import ws.daley.genealogy.gedcom.line.GcAddressLine2;
import ws.daley.genealogy.gedcom.line.GcAddressLineCont;
import ws.daley.genealogy.gedcom.line.GcAddressPostalCodeLine;
import ws.daley.genealogy.gedcom.line.GcAddressStateLine;
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
 * used in CORP
 *     	
 * n ADDR <ADDRESS_LINE>						{0:1}	
 *     +1 CONT <ADDRESS_LINE>					{0:M}	
 *     +1 ADR1 <ADDRESS_LINE1>					{0:1}	
 *     +1 ADR2 <ADDRESS_LINE2>					{0:1}	
 *     +1 CITY <ADDRESS_CITY>					{0:1}	
 *     +1 STAE <ADDRESS_STATE>					{0:1}	
 *     +1 POST <ADDRESS_POSTAL_CODE>			{0:1}	
 *     +1 CTRY <ADDRESS_COUNTRY>				{0:1}	
 */

public class GcAddressStructure extends Gc_Structure
{
	public String addressLine;
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("CONT", 0, Integer.MAX_VALUE, GcAddressLineCont.class),
				new TagDescriptor("ADR1", 0, 1, GcAddressLine1.class),
				new TagDescriptor("ADR2", 0, 1, GcAddressLine2.class),
				new TagDescriptor("CITY", 0, 1, GcAddressCityLine.class),
				new TagDescriptor("STAE", 0, 1, GcAddressStateLine.class),
				new TagDescriptor("POST", 0, 1, GcAddressPostalCodeLine.class),
				new TagDescriptor("CTRY", 0, 1, GcAddressCountryLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_LINE", 0, 1, GcAddressLineAttribute.class),
		});
	}
	
	public GcAddressStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "DATA", attributeDescriptorMap, tagDescriptorMap, _vector);
		
		this.addressLine = getParameters();
	}
	
	public String getAddressLine() {return this.getParameters();}
	public String getAddressCont() {return this.getParameterForKey("CONT");}
	public String getAddressLine1() {return this.getParameterForKey("ADR1");}
	public String getAddressLine2() {return this.getParameterForKey("ADR2");}
	public String getAddressCity() {return this.getParameterForKey("CITY");}
	public String getAddressState() {return this.getParameterForKey("STAE");}
	public String getAddressPostalCode() {return this.getParameterForKey("POST");}
	public String getAddressCountry() {return this.getParameterForKey("CTRY");}
};
