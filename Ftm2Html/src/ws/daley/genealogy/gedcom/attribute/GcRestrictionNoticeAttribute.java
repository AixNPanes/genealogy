package ws.daley.genealogy.gedcom.attribute;

/**
 * RESTRICTION_NOTICE:=	{Size=6:7} 
 * [ locked | privacy ] 
 * 
 * The restriction notice is defined for Ancestral File usage.  Ancestral File 
 * download GEDCOM files may contain this data.
 * 
 * Where:
 * locked	= Some records in Ancestral File have been satisfactorily proven by 
 * 	evidence, but because of source conflicts or incorrect traditions, there 
 * 	are repeated attempts to change this record. By arrangement, the Ancestral 
 * 	File Custodian can lock a record so that it cannot be changed without an 
 * 	agreement from the person assigned as the steward of such a record. The 
 * 	assigned steward is either the submitter listed for the record or Family 
 * 	History Support when no submitter is listed.
 * privacy = Information concerning this record is not present due to rights of 
 * 	or an approved request for privacy.

 */

public class GcRestrictionNoticeAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RESTRICTION_NOTICE", 6, 7, GcRestrictionNoticeAttribute.class),
		});
	}
}
