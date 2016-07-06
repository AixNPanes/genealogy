package ws.daley.genealogy.gedcom.attribute;

/**
 * LDS_BAPTISM_DATE_STATUS:=	{Size=5:10} 
 * [ CHILD | CLEARED | COMPLETED | INFANT | PRE-1970 |  QUALIFIED | STILLBORN 
 * | SUBMITTED | UNCLEARED ]
 * 
 * A code indicating the status of an LDS baptism and confirmation date where:
 * CHILD	= Died before eight years old.
 * CLEARED	= Baptism has been cleared for temple ordinance.
 * COMPLETED = Completed but the date is not known.
 * INFANT	= Died before less than one year old, baptism not required.
 * QUALIFIED	= Ordinance request qualified by authorized criteria.
 * PRE-1970	= Ordinance is likely completed, another ordinance for this person 
 * 	was converted from temple records of work completed before 1970, therefore 
 * 	this ordinance is assumed to be complete until all records are converted.
 * STILLBORN	= Stillborn, baptism not required.
 * SUBMITTED	= Ordinance was previously submitted.
 * UNCLEARED = Data for clearing ordinance request was insufficient.
 */

public class GcLdsBaptismDateStatusAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("LDS_BAPTISM_DATE_STATUS", 5, 10, GcLdsBaptismDateStatusAttribute.class),
		});
	}
}
