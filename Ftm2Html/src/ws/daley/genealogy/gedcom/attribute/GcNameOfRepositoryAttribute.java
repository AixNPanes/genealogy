package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_OF_REPOSITORY:=	{Size=1:90} 
 * 
 * The official name of the archive in which the stated source material is stored.
 */

public class GcNameOfRepositoryAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_OF_REPOSITORY", 1, 90, GcNameOfRepositoryAttribute.class),
		});
	}

}
