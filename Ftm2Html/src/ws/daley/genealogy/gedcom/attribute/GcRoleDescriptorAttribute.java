package ws.daley.genealogy.gedcom.attribute;

/**
 * ROLE_DESCRIPTOR:=	{Size=1:25}   
 * 
 * A word or phrase that identifies a person's role in an event being described. 
 * This should be the same word or phrase, and in the same language, that the 
 * recorder used to define the role in the actual record.
 */

public class GcRoleDescriptorAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ROLE_DESCRIPTOR", 1, 25, GcRoleDescriptorAttribute.class),
		});
	}
}
