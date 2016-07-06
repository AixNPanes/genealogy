package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_PIECE_PREFIX:=	{Size=1:30} 
 * [ <NAME_PIECE> | <NAME_PIECE_PREFIX>, <NAME_PIECE> ] 
 * 
 * Non indexing name piece that appears preceding the given name and surname 
 * parts. Different name prefix parts are separated by a comma.
 * 
 * For example:
 * Lt. Cmndr. Joseph /Allen/ jr.
 * In this example Lt. Cmndr. is considered as the name prefix portion.
 */

public class GcNamePiecePrefixAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_PIECE_PREFIX", 1, 30, GcNamePiecePrefixAttribute.class),
		});
	}

}
