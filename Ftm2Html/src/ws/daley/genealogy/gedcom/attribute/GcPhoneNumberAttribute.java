package ws.daley.genealogy.gedcom.attribute;


/**
 * PHONE_NUMBER:=	{Size=1:25} 
 * 
 * A phone number.
 */

public class GcPhoneNumberAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PHONE_NUMBER", 1, 25, GcPhoneNumberAttribute.class),
		});
	}

}
