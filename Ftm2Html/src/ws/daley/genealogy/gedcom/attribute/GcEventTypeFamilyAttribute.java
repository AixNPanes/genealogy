package ws.daley.genealogy.gedcom.attribute;

import java.util.Vector;

/**
 * EVENT_TYPE_FAMILY:=	{Size=3:4} 
 * [ ANUL | CENS | DIV | DIVF | ENGA | MARR | MARB | MARC | MARL | MARS | EVEN ]
 * 
 * A code used to indicate the type of family event.  The definition is the same 
 * as the corresponding event tag defined in Appendix A. (See Appendix A, starting 
 * on page 71).
 */

public class GcEventTypeFamilyAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	private static Vector<String> types = new Vector<String>();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("EVENT_TYPE_FAMILY", 3, 4, GcEventTypeFamilyAttribute.class),
		});
		types.add("ANUL");
		types.add("CENS");
		types.add("DIV");
		types.add("DIVF");
		types.add("ENGA");
		types.add("MARR");
		types.add("MARB");
		types.add("MARC");
		types.add("MARL");
		types.add("MARS");
		types.add("EVEN");
	}
	
	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		if (!types.contains(this.parameters))
			return false;
		return true;
	}
}
