package ws.daley.genealogy.gedcom.attribute;


/**
 * ANCESTRAL_FILE_NUMBER:=	{Size=1:12} 
 * 
 * A unique permanent record number of an individual record contained in the 
 * Family History Department's Ancestral File.
 */

public class GcAncestralFileNumberAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ANCESTRAL_FILE_NUMBER", 1, 12, GcAncestralFileNumberAttribute.class),
		});
	}

}
