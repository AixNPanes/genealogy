package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_OF_BUSINESS:=	{Size=1:90} 
 * 
 * Name of the business, corporation, or person that produced or commissioned the 
 * product.
 */

public class GcNameOfBusinessAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_OF_BUSINESS", 1, 90, GcNameOfBusinessAttribute.class),
		});
	}

}
