package ws.daley.genealogy.gedcom.attribute;

/**
 * DESCRIPTIVE_TITLE:=	{Size=1:248}  
 * 
 * The title of a work, record, item, or object.
 */

public class GcDescriptiveTitleAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("DESCRIPTIVE_TITLE", 1, 248, GcDescriptiveTitleAttribute.class),
		});
	}

}
