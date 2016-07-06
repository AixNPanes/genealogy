package ws.daley.genealogy.gedcom.attribute;


/**
 * ADDRESS_COUNTRY:=	{Size=1:60} 
 * 
 * The name of the country that pertains to the associated address. Isolated by 
 * some systems for sorting or indexing. Used in most cases to facilitate 
 * automatic sorting of mail.
 */

public class GcAddressCountryAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_COUNTRY", 1, 60, GcAddressLineAttribute.class),
		});
	}

}
