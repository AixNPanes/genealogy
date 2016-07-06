package ws.daley.genealogy.gedcom.attribute;


/**
 * ADDRESS_LINE:=	{Size=1:60} 
 * 
 * Address information that, when combined with NAME and CONTinuation lines, 
 * meets requirements for sending communications through the mail.
 */

public class GcAddressLineAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_LINE", 1, 60, GcAddressLineAttribute.class),
		});
	}

}
