package ws.daley.genealogy.gedcom.attribute;

/**
 * SEX_VALUE:=	{Size=1:7} 
 * 
 * A code that indicates the sex of the individual:
 * M 	= Male                               
 * F	= Female
 */

public class GcPlaceLivingOrdinanceAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SEX_VALUE", 1, 7, GcPlaceLivingOrdinanceAttribute.class),
		});
	}

}
