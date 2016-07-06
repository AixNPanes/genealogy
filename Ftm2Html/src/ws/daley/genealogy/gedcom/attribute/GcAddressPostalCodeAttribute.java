package ws.daley.genealogy.gedcom.attribute;


/**
 * ADDRESS_POSTAL_CODE:=	{Size=1:10} 
 * 
 * The ZIP or postal code used by the various localities in handling of mail.  
 * Isolated for sorting or indexing.
 */

public class GcAddressPostalCodeAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_POSTAL_CODE", 1, 10, GcAddressPostalCodeAttribute.class),
		});
	}

}
