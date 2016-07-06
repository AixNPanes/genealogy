package ws.daley.genealogy.gedcom.attribute;


/**
 * ADDRESS_STATE:=	{Size=1:60} 
 * 
 * The name of the state used in the address. Isolated for sorting or indexing.
 */

public class GcAddressStateAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_STATE", 1, 60, GcAddressStateAttribute.class),
		});
	}

}
