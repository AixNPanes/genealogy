package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_PIECE_GIVEN:= {Size=1:120} 
 * [ <NAME_PIECE> | <NAME_PIECE_GIVEN>, <NAME_PIECE> ] 
 * 
 * Given name or earned name. Different given names are separated by a comma.
 */

public class GcNamePieceGivenAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_PIECE_GIVEN", 1, 120, GcNamePieceGivenAttribute.class),
		});
	}

}
