package ws.daley.genealogy.gedcom.attribute;


/**
 * TEMPLE_CODE:=	{Size=4:5} 
 * 
 * An abbreviation of the temple in which LDS temple ordinances were performed. 
 * (See Appendix C, page 87.)
 */

public class GcTempleCodeAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("TEMPLE_CODE", 4, 5, GcTempleCodeAttribute.class),
		});
	}

}
