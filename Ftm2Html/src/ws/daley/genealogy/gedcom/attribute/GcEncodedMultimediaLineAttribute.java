package ws.daley.genealogy.gedcom.attribute;

/**
 * ENCODED_MULTIMEDIA_LINE:=	{Size=1:87} 
 * 
 * A line from a multimedia object which has been ENCODED.  Each 54 characters of 
 * the multimedia object is encoded and stored in a 72 byte value.  The encoding 
 * algorithm is used to convert binary objects into legal ASCII values which can 
 * be transmitted.  See the encoding and decoding algorithms that are defined in 
 * Appendix E on page 93.
 */

public class GcEncodedMultimediaLineAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ENCODED_MULTIMEDIA_LINE", 1, 87, GcEncodedMultimediaLineAttribute.class),
		});
	}

}
