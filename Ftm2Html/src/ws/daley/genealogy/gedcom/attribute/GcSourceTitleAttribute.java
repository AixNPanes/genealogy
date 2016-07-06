package ws.daley.genealogy.gedcom.attribute;

/**
 * SOURCE_TITLE:=	{Size=1:90} 
 */

public class GcSourceTitleAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_TITLE", 1, 90, GcSourceTitleAttribute.class),
		});
	}

}
