package ws.daley.genealogy.gedcom.attribute;

/**
 * SOURCE_DESCRIPTION:=	{Size=1:248} 
 * 
 * A free form text block used to describe the source from which information was 
 * obtained.  This text block is used by those systems which cannot use a pointer 
 * to a source record. It must contain a descriptive title, who created the work, 
 * where and when it was created, and where is source data stored. The developer 
 * should encourage users to use an appropriate style for forming this free form 
 * bibliographic reference. Developers are encouraged to support the SOURCE_RECORD 
 * method of reporting bibliographic reference descriptions. 
 */

public class GcSourceDescriptionAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_DESCRIPTION", 1, 248, GcSourceDescriptionAttribute.class),
		});
	}

}
