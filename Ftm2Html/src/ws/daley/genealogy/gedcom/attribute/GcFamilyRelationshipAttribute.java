package ws.daley.genealogy.gedcom.attribute;


/**
 * FAMILY_RELATIONSHIP:=	{Size=1:90} 
 */

public class GcFamilyRelationshipAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("FAMILY_RELATIONSHIP", 1, 90, GcFamilyRelationshipAttribute.class),
		});
	}

}
