package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_PIECE_SURNAME:=	{Size=1:120} 
 * [ <NAME_PIECE> | <NAME_PIECE_SURNAME>, <NAME_PIECE> ] 
 * 
 * Surname or family name. Different surnames are separated by a comma.
 */

public class GcNamePieceSuffixAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_PIECE_SURNAME", 1, 120, GcNamePieceSuffixAttribute.class),
		});
	}

	@Override
    public boolean interpret()
	{
		throw new RuntimeException("implementation");
	}
}
