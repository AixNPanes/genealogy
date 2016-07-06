package ws.daley.genealogy.gedcom.attribute;

/**
 * POSSESSIONS:=	{Size=1:248} 
 * 
 * A list of possessions (real estate or other property) belonging to this 
 * individual.
 */

public class GcPossessionAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("POSSESSIONS", 1, 248, GcPossessionAttribute.class),
		});
	}

}
