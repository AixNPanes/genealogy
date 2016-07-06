package ws.daley.genealogy.gedcom.attribute;

/**
 * GENERATIONS_OF_DESCENDANTS:=	{Size=1:4} 
 * 
 * The number of generations of descendants included in this transmission. This 
 * value is usually provided when FamilySearch programs build a GEDCOM file for 
 * a patron requesting a download of descendants.
 */

public class GcGenerationsOfDescendantsAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("GENERATIONS_OF_DESCENDANTS", 1, 4, GcGenerationsOfDescendantsAttribute.class),
		});
	}

}
