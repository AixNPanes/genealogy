package ws.daley.genealogy.gedcom.attribute;


/**
 * PHYSICAL_DESCRIPTION:=	{Size=1:248} 
 * 
 * An unstructured list of the attributes that describe the physical 
 * characteristics of a person, place, or object. Commas separate each attribute.
 * 
 * Example:
 * 1 DSCR Hair Brown, Eyes Brown, Height 5 ft 8 in
 * 	2 DATE 23 JUL 1935
 */

public class GcPhysicalDescriptionAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PHYSICAL_DESCRIPTION", 1, 248, GcPhysicalDescriptionAttribute.class),
		});
	}

}
