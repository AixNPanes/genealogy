package ws.daley.genealogy.gedcom.attribute;

/**
 * SOURCE_MEDIA_TYPE: = {Size=1:15} 
 * [ audio | book | card | electronic | fiche | film | magazine | 
 * manuscript | map | newspaper | photo | tombstone | video ] 
 * A code, selected from one of the media classifications choices above, that indicates the type of material in which the referenced source is stored. 
 */

public class GcSourceMediaTypeAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_MEDIA_TYPE", 1, 15, GcSourceMediaTypeAttribute.class),
		});
	}

}
