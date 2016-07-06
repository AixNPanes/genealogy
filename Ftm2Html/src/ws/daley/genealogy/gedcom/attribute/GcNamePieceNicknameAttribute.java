package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_PIECE_NICKNAME:=	{Size=1:30} 
 * [ <NAME_PIECE> | <NAME_PIECE_NICKNAME>, <NAME_PIECE> ] 
 * 
 * A descriptive or familiar name used in connection with one's proper name.
 */

public class GcNamePieceNicknameAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_PIECE_NICKNAME", 1, 30, GcNamePieceNicknameAttribute.class),
		});
	}

	@Override
    public boolean interpret()
	{
		throw new RuntimeException("implementation");
	}
	
}
