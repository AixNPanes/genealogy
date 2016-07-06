package ws.daley.genealogy.gedcom.attribute;


/**
 * ADDRESS_CITY:=	{Size=1:60} 
 * 
 * The name of the city used in the address. Isolated for sorting or indexing.
 */

/**
 * RELIGIOUS_AFFILIATION:=	{Size=1:90} 
 * 
 * A name of the religion with which this person, event, or record was affiliated.
 */

public class GcReligiousAffiliationAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RELIGIOUS_AFFILIATION", 1, 90, GcReligiousAffiliationAttribute.class),
		});
	}

}
