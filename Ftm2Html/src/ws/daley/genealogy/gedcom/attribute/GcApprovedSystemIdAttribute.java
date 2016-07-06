package ws.daley.genealogy.gedcom.attribute;


/**
 * APPROVED_SYSTEM_ID:=	{Size=1:20} 
 * 
 * A system identification name which was obtained through the GEDCOM 
 * registration process. This name must be unique from any other product. Spaces 
 * within the name must be substituted with a 0x5F (underscore _) so as to create 
 * one word.
 */

public class GcApprovedSystemIdAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_CITY", 1, 20, GcApprovedSystemIdAttribute.class),
		});
	}

}
