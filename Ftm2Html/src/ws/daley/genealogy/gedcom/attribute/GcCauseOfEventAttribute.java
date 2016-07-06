package ws.daley.genealogy.gedcom.attribute;

/**
 * CAUSE_OF_EVENT:=	{Size=1:90} 
 * 
 * Used in special cases to record the reasons which precipitated an event. 
 * Normally this will be used subordinate to a death event to show cause of 
 * death, such as might be listed on a death certificate.
 */

public class GcCauseOfEventAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("CAUSE_OF_EVENT", 1, 90, GcCauseOfEventAttribute.class),
		});
	}

}
