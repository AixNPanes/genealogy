package ws.daley.genealogy.gedcom.attribute;

import java.util.Vector;

/**
 * MONTH:=	{Size=3} 
 * [ JAN | FEB | MAR | APR | MAY | JUN |  JUL | AUG | SEP | OCT | NOV | DEC ] 
 * 
 * Where:
 * JAN	= January 
 * FEB	= February 
 * MAR = March 
 * APR	= April 
 * MAY	= May 
 * JUN	= June 
 * JUL	= July 
 * AUG = August 
 * SEP	= September 
 * OCT	= October 
 * NOV = November 
 * DEC	= December
 */

public class GcMonthAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();

	private static Vector<String> months = new Vector<String>();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("MONTH", 3, 3, GcMonthAttribute.class),
		});
		months.add("JAN");
		months.add("FEB");
		months.add("MAR");
		months.add("APR");
		months.add("MAY");
		months.add("JUN");
		months.add("JUL");
		months.add("AUG");
		months.add("SEP");
		months.add("OCT");
		months.add("NOV");
		months.add("DEC");
	}
	
	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		if (!months.contains(this.parameters))
			return false;
		return true;
	}

}
