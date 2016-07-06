package ws.daley.genealogy.gedcom.attribute;

/**
 * LANGUAGE_OF_TEXT:=	{Size=1:15} 
 * [ <LANGUAGE_ID> ] 
 * 
 * The human language in which the data in the transmission is normally read or 
 * written.  It is used primarily by programs to select language-specific sorting 
 * sequences and phonetic name matching algorithms.
 */

public class GcLanguageOfTextAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("LANGUAGE_OF_TEXT", 1, 15, GcLanguageOfTextAttribute.class),
		});
	}

}
