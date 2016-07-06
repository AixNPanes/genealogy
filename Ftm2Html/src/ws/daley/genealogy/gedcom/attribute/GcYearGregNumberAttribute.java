package ws.daley.genealogy.gedcom.attribute;


/**
 * NUMBER:= 
[<DIGIT> | <NUMBER>+<DIGIT>]
 */

public class GcYearGregNumberAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NUMBER", 3, 4, GcYearGregNumberAttribute.class),
		});
	}
	
	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		try{Integer.parseInt(this.parameters);}
		catch(NumberFormatException e) {return false;}
		return true;
	}
}
