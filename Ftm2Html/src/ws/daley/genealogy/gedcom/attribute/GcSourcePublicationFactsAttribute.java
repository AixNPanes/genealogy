package ws.daley.genealogy.gedcom.attribute;

/**
 * SOURCE_PUBLICATION_FACTS: = {Size=248} 
 * When and where the record was created. For published works, this includes information such as the city of publication,
 * name of the publisher, and year of publication. 
 */

public class GcSourcePublicationFactsAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_TITLE", 1, 248, GcSourcePublicationFactsAttribute.class),
		});
	}

}
