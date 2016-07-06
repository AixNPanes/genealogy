package ws.daley.genealogy.gedcom.attribute;

/**
 * FILE_NAME:=	{Size=1:90} 
 * 
 * The name of the GEDCOM transmission file. If the file name includes a file 
 * extension it must be shown in the form (filename.ext).
 */

public class GcFileNameAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("FILE_NAME", 1, 90, GcFileNameAttribute.class),
		});
	}
}
