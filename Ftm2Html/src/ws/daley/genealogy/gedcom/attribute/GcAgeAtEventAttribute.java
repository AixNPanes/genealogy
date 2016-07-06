package ws.daley.genealogy.gedcom.attribute;

/**
 * AGE_AT_EVENT:=	{Size=1:12} 
 * [ < | > | <NULL>] [ YYy MMm DDDd | YYy | MMm | DDDd 
 * | 
 * YYy MMm | YYy DDDd | MMm DDDd 
 * |  
 * CHILD | INFANT | STILLBORN ] 
 * ] 
 * 
 * Where:
 * >	= greater than indicated age 
 * <	= less than indicated age 
 * y	= a label indicating years 
 * m	= a label indicating months
 * d	= a label indicating days 
 * YY	= number of full years 
 * MM	= number of months 
 * DDD	= number of days 
 * CHILD	= age < 8 years 
 * INFANT	= age < 1 year 
 * STILLBORN = died just prior, at, or near birth, 
 * 0 years A number that indicates the age in years, months, and days that the 
 * principal was at the time of the associated event. Any labels must come after 
 * their corresponding number, for example; 4y 8m 10d.
 */

public class GcAgeAtEventAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("AGE_AT_EVENT", 1, 7, GcAgeAtEventAttribute.class),
		});
	}

	public void implementation()
	{
		throw new RuntimeException("implementation");
	}
}
