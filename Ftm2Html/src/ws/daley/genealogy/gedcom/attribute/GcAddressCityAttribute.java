package ws.daley.genealogy.gedcom.attribute;


/**
 * ADDRESS_CITY:=	{Size=1:60} 
 * 
 * The name of the city used in the address. Isolated for sorting or indexing.
 */

public class GcAddressCityAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_CITY", 1, 60, GcAddressCityAttribute.class),
		});
	}

}
