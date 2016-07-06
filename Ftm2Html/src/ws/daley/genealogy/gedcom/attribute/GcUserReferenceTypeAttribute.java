package ws.daley.genealogy.gedcom.attribute;


/**
 * USER_REFERENCE_TYPE:=	{Size=1:40} 

A user-defined definition of the USER_REFERENCE_NUMBER.
 */

public class GcUserReferenceTypeAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("USER_REFERENCE_TYPE", 1, 40, GcUserReferenceTypeAttribute.class),
		});
	}
}
