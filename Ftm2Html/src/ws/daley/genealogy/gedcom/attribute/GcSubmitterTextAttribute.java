package ws.daley.genealogy.gedcom.attribute;

/**
 * SUBMITTER_TEXT:=	{Size=1:248} 
 * 
 * Comments or opinions from the submitter.
 */

public class GcSubmitterTextAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SUBMITTER_TEXT", 1, 248, GcSubmitterTextAttribute.class),
		});
	}

}
