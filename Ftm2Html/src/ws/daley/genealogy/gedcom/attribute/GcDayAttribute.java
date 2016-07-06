package ws.daley.genealogy.gedcom.attribute;

/**
 * DAY:=	{Size=1:2} 
 * dd 
 * 
 * Day of the month, where dd is a numeric digit whose value is within the valid 
 * range of the days for the associated calendar month.
 */

public class GcDayAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("DAY", 1, 2, GcDayAttribute.class),
		});
	}
	
	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		try
		{
			int dd = Integer.parseInt(this.parameters);
			if (dd < 1)
				return false;
			if (dd > 31)
				return false;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}
}
