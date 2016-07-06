package ws.daley.genealogy.gedcom.attribute;

/**
 * MULTIMEDIA_FORMAT:=	{Size=3:4} 
 * [ bmp | gif | jpeg | ole | pcx | tiff | wav ] 
 * 
 * Indicates the format of the multimedia data associated with the specific 
 * GEDCOM context. This allows processors to determine whether they can process 
 * the data object. Any linked files should contain the data required, in the 
 * indicated format, to process the file data. Industry standards will emerge in 
 * this area and GEDCOM will then narrow its scope.
 */

public class GcMultimediaFormatAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("MULTIMEDIA_FORMAT", 3, 4, GcMultimediaFormatAttribute.class),
		});
	}
}
