package ws.daley.genealogy.gedcom.attribute;


/**
 * WHERE_WITHIN_SOURCE:=	{Size=1:248} 
 * 
 * Specific location with in the information referenced. For a published work, 
 * this could include the volume of a multi-volume work and the page number(s). 
 * For a periodical, it could include volume, issue, and page numbers. For a 
 * newspaper, it could include a column number and page number. For an 
 * unpublished source, this could be a sheet number, page number, frame number, 
 * etc. A census record might have a line number or dwelling and family numbers 
 * in addition to the page number.
 */

public class GcWhereWithinSourceAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("WHERE_WITHIN_SOURCE", 1, 248, GcWhereWithinSourceAttribute.class),
		});
	}
}
