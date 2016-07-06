package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_PIECE_SURNAME_PREFIX:=	{Size=1:30} 
 * [ <NAME_PIECE> | <NAME_PIECE_SURNAME_PREFIX>, <NAME_PIECE> ] 
 * 
 * Surname prefix or article used in a family name. Different surname articles 
 * are separated by a comma, for example in the name "de la Cruz", this value 
 * would be "de, la".
 */

public class GcNamePieceSurnamePrefixAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_PIECE_SURNAME_PREFIX", 1, 30, GcNamePieceSurnamePrefixAttribute.class),
		});
	}

}
