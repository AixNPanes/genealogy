package ws.daley.genealogy.gedcom.attribute;


/**
 * COUNT_OF_MARRIAGES:=	{Size=1:3} 
 * 
 * The number of different families that this person was known to have been a 
 * member of as a spouse or parent, regardless of whether the associated families 
 * are represented in the GEDCOM file.
 */

public class GcCastNameAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("COUNT_OF_MARRIAGES", 1, 3, GcCastNameAttribute.class),
		});
	}

}
