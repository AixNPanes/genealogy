package ws.daley.genealogy.gedcom.attribute;

/**
 * ORDINANCE_PROCESS_FLAG:=	{Size=2:3} 
 * [ yes | no ] 
 * 
 * A flag that indicates whether submission should be processed for clearing 
 * temple ordinances. 
 */

public class GcOrdinanceProcessFlagAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ORDINANCE_PROCESS_FLAG", 2, 3, GcOrdinanceProcessFlagAttribute.class),
		});
	}
}
