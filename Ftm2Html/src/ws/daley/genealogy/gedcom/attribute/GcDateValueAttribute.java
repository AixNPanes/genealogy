package ws.daley.genealogy.gedcom.attribute;


/**
 * DATE_VALUE:=	{Size=1:35} 
 * [ 
 * <DATE> 
 * | 
 * <DATE_PERIOD> 
 * | 
 * <DATE_RANGE> <DATE_APPROXIMATED> 
 * | 
 * INT <DATE> (<DATE_PHRASE>) 
 * | 
 * (<DATE_PHRASE>) 
 * ]
 * 
 * The DATE_VALUE represents the date of an activity, attribute, or event where:
 * INT = Interpreted from knowledge about the associated date phrase included in 
 * parentheses. 
 * An acceptable alternative to the date phrase choice is to use one of the other 
 * choices such as <DATE_APPROXIMATED> choice as the DATE line value and then 
 * include the <DATE_PHRASE> as a NOTE value subordinate to the DATE line.
 * 
 * The date value can take on the date form of just a date, an approximated date, 
 * between a date and another date, and from one date to another date.  The 
 * preferred form of showing date imprecision, is to show, for example, MAY 
 * 1890 rather than ABT 12 MAY 1890.  This is because limits have not been 
 * assigned to the precision of the prefixes such as ABT or EST.  
 */

public class GcDateValueAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("DATE_VALUE", 1, 35, GcDateValueAttribute.class),
		});
	}

}
