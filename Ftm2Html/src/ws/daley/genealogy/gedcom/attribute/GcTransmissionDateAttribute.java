package ws.daley.genealogy.gedcom.attribute;

import ws.daley.genealogy.gedcom.line.Gc_Line;

/**
 * TRANSMISSION_DATE:=	{Size=10:11} 
 * <DATE_EXACT> 
 * 
 * The date that this transmission was created.
 */

public class GcTransmissionDateAttribute extends GcDateExactAttribute
{
	@SuppressWarnings({ "hiding", "unused" })
    private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("TRANSMISSION_DATE", 10, 11, GcTransmissionDateAttribute.class),
		});
	}

	
	public GcTransmissionDateAttribute(Gc_Line e, String _type)
	{
		super(e, _type);
	}
}
