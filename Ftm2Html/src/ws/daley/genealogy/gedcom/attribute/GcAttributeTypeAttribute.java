package ws.daley.genealogy.gedcom.attribute;

import java.util.Vector;

/**
 * ATTRIBUTE_TYPE:=	{Size=1:4} 
 * [ CAST | EDUC | NATI | OCCU | PROP | RELI | RESI | TITL ] 
 * 
 * An attribute which may have caused name, addresses, phone numbers, family 
 * listings to be recorded.  Its application is in helping to classify sources 
 * used for information.
 */

public class GcAttributeTypeAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	private static Vector<String> types = new Vector<String>();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ATTRIBUTE_TYPE", 1, 4, GcAttributeTypeAttribute.class),
		});
		types.add("CAST");
		types.add("EDUC");
		types.add("NATI");
		types.add("OCCU");
		types.add("PROP");
		types.add("RELI");
		types.add("RESI");
		types.add("TITL");
	}
	
	@Override
    public boolean interpret()
	{
		super.interpret();
		if (!types.contains(this.parameters))
			return false;
		return true;
	}
}
