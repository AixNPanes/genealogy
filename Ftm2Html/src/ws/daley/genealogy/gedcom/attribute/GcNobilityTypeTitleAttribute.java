package ws.daley.genealogy.gedcom.attribute;


/**
 * NOBILITY_TYPE_TITLE:=	{Size=1:120} 
 * 
 * The title given to or used by a person, especially of royalty or other noble 
 * class within a locality.
 */

public class GcNobilityTypeTitleAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NOBILITY_TYPE_TITLE", 1, 120, GcNobilityTypeTitleAttribute.class),
		});
	}

}
