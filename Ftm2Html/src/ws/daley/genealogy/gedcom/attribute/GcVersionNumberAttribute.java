package ws.daley.genealogy.gedcom.attribute;

/**
 * VERSION_NUMBER:=	{Size=1:15} 
 * 
 * An identifier that represents the version level assigned to the associated 
 * product. It is defined and changed by the creators of the product.
 */

public class GcVersionNumberAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("VERSION_NUMBER", 1, 15, GcVersionNumberAttribute.class),
		});
	}
}
