package ws.daley.genealogy.gedcom.attribute;

import ws.daley.genealogy.gedcom.line.Gc_Line;


/**
 * CHANGE_DATE:=	{Size=10:11} <DATE_EXACT> 

The date that this data was changed.
 */

public class GcChangeDateAttribute extends GcDateExactAttribute
{
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("CHANGE_DATE", 10, 11, GcChangeDateAttribute.class),
		});
	}

	public GcChangeDateAttribute(Gc_Line e, String _type)
	{
		super(e, _type);
	}

}
