package ws.daley.genealogy.gedcom.attribute;


/**
 * DATE_LDS_ORD:=	{Size=4:35} 
 * <DATE_VALUE> 
 * 
 * LDS ordinance dates use only the Gregorian date and most often use the form 
 * of day, month, and year.
 * 
 * Only in rare instances is there a partial date. The temple tag and code should 
 * always accompany temple ordinance dates. Sometimes the 
 * LDS_(ordinance)_DATE_STATUS is used to indicate that an ordinance date and 
 * temple code is not required, such as when BIC is used. 
 * (See LDS_(ordinance)_DATE_STATUS definitions beginning on page 48.)
 */

public class GcDateLdsOrdinanceAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("DATE_LDS_ORD", 4, 35, GcDateLdsOrdinanceAttribute.class),
		});
	}

}
