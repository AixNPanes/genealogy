package ws.daley.genealogy.gedcom.attribute;

/**
 * RESPONSIBLE_AGENCY:=	{Size=1:120} 
 * 
 * The organization, institution, corporation, person, or other entity that has 
 * authority or control interests in the associated context. For example, an 
 * employer of a person of an associated occupation, or a church that administered 
 * rites or events, or an organization responsible for creating and/or archiving 
 * records.
 */

public class GcResponsibleAgencyAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RESPONSIBLE_AGENCY", 1, 120, GcResponsibleAgencyAttribute.class),
		});
	}

}
