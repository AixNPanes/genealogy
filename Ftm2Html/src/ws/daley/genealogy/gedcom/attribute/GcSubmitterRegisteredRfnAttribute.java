package ws.daley.genealogy.gedcom.attribute;

/**
 * SUBMITTER_REGISTERED_RFN:=	{Size=1:30} 
 * 
 * A registered number of a submitter of Ancestral File data.  This number is 
 * used in subsequent submissions or inquiries by the submitter for identification 
 * purposes.
 */

public class GcSubmitterRegisteredRfnAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SUBMITTER_REGISTERED_RFN", 1, 30, GcSubmitterRegisteredRfnAttribute.class),
		});
	}

}
