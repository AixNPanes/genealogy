package ws.daley.genealogy.gedcom.attribute;

/**
 * SOURCE_CALL_NUMBER: = {Size=1:120} 
 * An identification or reference description used to file and retrieve items from the holdings of a repository. 
 */

public class GcSourceCallNumberAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_CALL_NUMBER", 1, 120, GcSourceCallNumberAttribute.class),
		});
	}

}
