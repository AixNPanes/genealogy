package ws.daley.genealogy.gedcom.attribute;


/**
 * MARRIAGE_RELATIONSHIP:=	{Size=1:90} 
 */

public class GcMarriageRelationshipAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("MARRIAGE_RELATIONSHIP", 1, 90, GcMarriageRelationshipAttribute.class),
		});
	}

}
