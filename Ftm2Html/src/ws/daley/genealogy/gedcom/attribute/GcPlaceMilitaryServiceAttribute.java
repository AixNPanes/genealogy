package ws.daley.genealogy.gedcom.attribute;

/**
 * PLACE_MILITARY_SERVICE: = {Size=1:120} 
 * <PLACE_VALUE> 
 */

public class GcPlaceMilitaryServiceAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PLACE_MILITARY_SERVICE", 1, 7, GcPlaceMilitaryServiceAttribute.class),
		});
	}

}
