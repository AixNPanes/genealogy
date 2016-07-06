package ws.daley.genealogy.gedcom.attribute;

import java.util.Vector;

/**
 * EVENT_TYPE_INDIVIDUAL:=	{Size=3:4} 
 * [ ADOP | BIRT | BAPM | BARM | BASM | BLES | BURI | CENS | CHR | CHRA | CONF 
 * | CREM | DEAT | EMIG | FCOM | GRAD | IMMI | NATU | ORDN | RETI | PROB | WILL 
 * | EVEN ]
 * 
 * A code used to indicate the type of family event.  The definition is the same 
 * as the corresponding event tag defined in Appendix A. (See Appendix A, starting 
 * on page 71).
 */

public class GcEventTypeIndividualAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	private static Vector<String> types = new Vector<String>();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("EVENT_TYPE_INDIVIDUAL", 3, 4, GcEventTypeIndividualAttribute.class),
		});
		types.add("ADOP");
		types.add("BIRT");
		types.add("BAPM");
		types.add("BARM");
		types.add("BASM");
		types.add("BLES");
		types.add("BURI");
		types.add("CENS");
		types.add("CHR");
		types.add("CHRA");
		types.add("CONF");
		types.add("CREM");
		types.add("DEAT");
		types.add("EMIG");
		types.add("FCOM");
		types.add("GRAD");
		types.add("IMMI");
		types.add("NATU");
		types.add("ORDN");
		types.add("RETI");
		types.add("PROB");
		types.add("WILL");
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
