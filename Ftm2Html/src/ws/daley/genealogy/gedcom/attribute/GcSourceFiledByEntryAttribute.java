package ws.daley.genealogy.gedcom.attribute;

/**
 * SOURCE_FILED_BY_ENTRY: = {Size= 1:60} 
 * This entry is to provide a short title used for sorting, filing, and retrieving source records. 
 */

public class GcSourceFiledByEntryAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_FILED_BY_ENTRY", 1, 60, GcSourceFiledByEntryAttribute.class),
		});
	}

}
