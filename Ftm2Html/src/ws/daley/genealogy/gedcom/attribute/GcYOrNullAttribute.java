package ws.daley.genealogy.gedcom.attribute;

/**
 * [Y|<NULL>]
 */

public class GcYOrNullAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
    private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("Y_OR_NULL", 0, 1, GcYOrNullAttribute.class),
		});
	}

	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		if (!"Y".equals(getParameters()))
			return false;
		return true;
	}
}
