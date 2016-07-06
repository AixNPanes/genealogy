package ws.daley.genealogy.gedcom.attribute;


/**
 * EMAIL_ADDRESS:=	{Size=1:90} 
 * 
 * A phone number.
 */

public class GcEmailAddressAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("EMAIL_ADDRESS", 1, 90, GcEmailAddressAttribute.class),
		});
	}

}
