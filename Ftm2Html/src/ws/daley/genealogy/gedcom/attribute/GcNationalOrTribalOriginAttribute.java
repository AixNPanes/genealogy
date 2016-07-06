package ws.daley.genealogy.gedcom.attribute;


/**
 * NATIONAL_OR_TRIBAL_ORIGIN:=	{Size=1:120} 
 * 
 * The person's division of national origin or other folk, house, kindred, 
 * lineage, or tribal interest.
 * 
 * Examples: Irish, Swede, Egyptian Coptic, Sioux Dakota Rosebud, 
 * Apache Chiricawa, Navajo Bitter Water, Eastern Cherokee Taliwa Wolf, and so 
 * forth.
 */

public class GcNationalOrTribalOriginAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NATIONAL_OR_TRIBAL_ORIGIN", 1, 120, GcNationalOrTribalOriginAttribute.class),
		});
	}

}
