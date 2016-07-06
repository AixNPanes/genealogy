package ws.daley.genealogy.gedcom.attribute;


/**
 * EVENT_ATTRIBUTE_TYPE:=	{Size=1:15}
 * [ <EVENT_TYPE_INDIVIDUAL> | <EVENT_TYPE_FAMILY> | <ATTRIBUTE_TYPE> ] 
 * 
 * A code that classifies the principal event or happening that caused the source 
 * record entry to be created. If the event or attribute doesn't translate to one 
 * of these tag codes, then a user supplied code is expected, but will be 
 * considered as the generic tag EVEN for source certainty evaluation.
 */

public class GcEventAttributeTypeAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("EVENT_ATTRIBUTE_TYPE", 1, 15, GcEventAttributeTypeAttribute.class),
		});
	}
}
