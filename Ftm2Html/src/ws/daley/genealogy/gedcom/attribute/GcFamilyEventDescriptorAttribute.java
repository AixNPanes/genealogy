package ws.daley.genealogy.gedcom.attribute;

/**
 * EVENT_DESCRIPTOR:=	{Size=1:90} 
 * 
 * A descriptor that should be used whenever the EVEN tag is used to define the 
 * event being cited. For example, if the event was a purchase of a residence, 
 * the EVEN tag would be followed by a subordinate TYPE tag with the value 
 * "Purchased Residence." Using this descriptor with any of the other defined 
 * event tags basically classifies the basic definition of the associated tag 
 * but does not change its basic process. The form of using the TYPE tag with 
 * defined event tags has not been used by very many products. The MARR tag could 
 * be subordinated with a TYPE tag and EVENT_DESCRIPTOR value of Common Law. Other 
 * possible descriptor values might include "Childbirth—unmarried," "Common Law," 
 * or "Tribal Custom," for example. The event descriptor should use the same word 
 * or phrase and in the same language, when possible, as was used by the recorder 
 * of the event. Systems that display data from the GEDCOM form should be able to 
 * display the descriptor value in their screen or printed output.
 */

public class GcFamilyEventDescriptorAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("EVENT_DESCRIPTOR", 1, 90, GcFamilyEventDescriptorAttribute.class),
		});
	}
}
