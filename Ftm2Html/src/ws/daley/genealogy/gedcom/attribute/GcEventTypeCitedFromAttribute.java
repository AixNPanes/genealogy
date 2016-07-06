package ws.daley.genealogy.gedcom.attribute;


/**
 * EVENT_TYPE_CITED_FROM:=	{SIZE=1:15} 
[ <EVENT_ATTRIBUTE_TYPE> ] 

A code that indicates the type of event which was responsible for the source 
entry being recorded. For example, if the entry was created to record a birth 
of a child, then the type would be BIRT regardless of the assertions made from 
that record, such as the mother's name or mother's birth date. This will allow 
a prioritized best view choice and a determination of the certainty associated 
with the source used in asserting the cited fact.
 */

public class GcEventTypeCitedFromAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("EVENT_TYPE_CITED_FROM", 1, 15, GcEventTypeCitedFromAttribute.class),
		});
	}
}
