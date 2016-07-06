package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_OF_FAMILY_FILE:=	{Size=1:120} 
 * 
 * Name under which family names for ordinances are stored in the temple's family 
 * file.
 */

public class GcNameOfFamilyFileAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_OF_FAMILY_FILE", 1, 120, GcNameOfFamilyFileAttribute.class),
		});
	}

}
