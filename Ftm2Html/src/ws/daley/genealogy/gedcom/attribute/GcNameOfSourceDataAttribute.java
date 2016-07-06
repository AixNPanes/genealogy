package ws.daley.genealogy.gedcom.attribute;

/**
 * NAME_OF_SOURCE_DATA:=	{Size=1:90} 
 * 
 * The name of the electronic data source that was used to obtain the data in 
 * this transmission. For example, the data may have been obtained from a CD-ROM 
 * disc that was named "U.S. 1880 CENSUS CD-ROM vol. 13."
 */

public class GcNameOfSourceDataAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_OF_SOURCE_DATA", 1, 90, GcNameOfSourceDataAttribute.class),
		});
	}

}
