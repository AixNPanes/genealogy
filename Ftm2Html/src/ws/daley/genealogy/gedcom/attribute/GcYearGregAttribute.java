package ws.daley.genealogy.gedcom.attribute;

/**
 * YEAR_GREG:=	{Size=3:7} 
 * [ <NUMBER> | <NUMBER>/<DIGIT><DIGIT> ] 
 * 
 * The slash "/" <DIGIT><DIGIT> a year modifier which shows the possible date 
 * alternatives for pre1752 date brought about by a changing the beginning of 
 * the year from MAR to JAN in the English calendar change of 1752, for example, 
 * 15 APR 1699/00. A (B.C.) appended to the <YEAR> indicates a date before the 
 * birth of Christ. */

public class GcYearGregAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("YEAR_GREG", 3, 7, GcYearGregAttribute.class),
		});
	}

	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		String[] parts = this.parameters.split("/");
		switch(parts.length)
		{
		case 0:
			return false;
		case 1:
		case 2:
			break;
		default:
			return false;
		}
		return true;
	}
}
