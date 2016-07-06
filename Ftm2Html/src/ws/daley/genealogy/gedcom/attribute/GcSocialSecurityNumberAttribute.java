package ws.daley.genealogy.gedcom.attribute;

/**
 * SOCIAL_SECURITY_NUMBER:=	{Size=9:11} 
 * 
 * A number assigned to a person in the United States for identification purposes.
 */

public class GcSocialSecurityNumberAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOCIAL_SECURITY_NUMBER", 9, 11, GcSocialSecurityNumberAttribute.class),
		});
	}

}
