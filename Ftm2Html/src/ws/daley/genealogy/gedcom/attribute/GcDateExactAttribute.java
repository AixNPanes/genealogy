package ws.daley.genealogy.gedcom.attribute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ws.daley.genealogy.gedcom.line.Gc_Line;

/**
 * DATE_EXACT:=	{Size=10:11} 
 * <DAY> <MONTH> <YEAR_GREG>
 */

public class GcDateExactAttribute extends Gc_Attribute
{
//	private Pattern pattern = Pattern.compile("([0-1]?[0-9]|2[0-8]|3[0-1]) *((JAN)|(FEB)|(MAR)|(APR)|(MAY)|(JUN)|(JUL)|(AUG)|(SEP)|(OCT)|(NOV)|(DEC)) *(([0-9]+)([/]([0-9]{2})?))");
	private Pattern pattern = Pattern.compile("([0-1]?[0-9]|2[0-8]|3[0-1]) *((JAN)|(FEB)|(MAR)|(APR)|(MAY)|(JUN)|(JUL)|(AUG)|(SEP)|(OCT)|(NOV)|(DEC)) *(([0-9]+)([/]([0-9]{2}))?)");
	private int day = -1;
	private String month = "";
	private int monthNumber = -1;
	private int year = -1;
	private int yearSuffix = -1;
	
	public GcDateExactAttribute(Gc_Line e, String _type)
	{
		super(e, _type);
	}

	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("DATE_EXACT", 10, 11, GcDateExactAttribute.class),
		});
	}

	@Override
    public boolean interpret()
	{
		Matcher m = this.pattern.matcher(this.parameters.toUpperCase());
		if (!m.matches())
			return false;
		{
			this.day = Integer.parseInt(m.group(1));
			this.month = m.group(2);
			this.year = Integer.parseInt(m.group(16));
			for(int i = 0; i < 12; i++)
				if (m.group(i+3) != null)
				{
					this.monthNumber = i;
					break;
				}
			if (m.group(17) != null)
				this.yearSuffix = Integer.parseInt(m.group(17));
		}
		return true;
	}

	public int getDay() {return this.day;}
	public String getMonth() {return this.month;}
	public int getMonthNumber() {return this.monthNumber;}
	public int getYear() {return this.year;}
	public int getYearSuffix() {return this.yearSuffix;}
}
