package ws.daley.genealogy.gedcom.attribute;

/**
 * GENERATIONS_OF_ANCESTORS:=	{Size=1:4} 
 * 
 * The number of generations of ancestors included in this transmission. This 
 * value is usually provided when FamilySearch programs build a GEDCOM file for 
 * a patron requesting a download of ancestors.
 */

public class GcGenerationsOfAncestorsAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("GENERATIONS_OF_ANCESTORS", 1, 4, GcGenerationsOfAncestorsAttribute.class),
		});
	}

}
