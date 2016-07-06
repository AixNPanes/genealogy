package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_OF_PRODUCT:=	{Size=1:90} 

The name of the software product that produced this transmission.
 */

public class GcNameOfProductAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_OF_PRODUCT", 1, 90, GcNameOfProductAttribute.class),
		});
	}

}
