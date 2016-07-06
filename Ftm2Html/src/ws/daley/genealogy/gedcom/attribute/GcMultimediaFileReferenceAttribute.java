package ws.daley.genealogy.gedcom.attribute;

/**
 * MULTIMEDIA_FILE_REFERENCE:=	{Size=1:30} 
 * 
 * A complete local or remote file reference to the auxiliary data to be linked 
 * to the GEDCOM context. Remote reference would include a network address where 
 * the multimedia data may be obtained.
 */

public class GcMultimediaFileReferenceAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("MULTIMEDIA_FILE_REFERENCE", 1, 30, GcMultimediaFileReferenceAttribute.class),
		});
	}

}
