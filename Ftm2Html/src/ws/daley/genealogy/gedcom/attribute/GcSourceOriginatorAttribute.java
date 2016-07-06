package ws.daley.genealogy.gedcom.attribute;

/**
 * SOURCE_ORIGINATOR: = {Size=1:248} 
 * The person, agency, or entity who created the record. For a published work, this could be the author, compiler,
 * transcriber, abstractor, or editor. For an unpublished source, this may be an individual, a government agency,
 * church organization, or private organization, etc. 
 */

public class GcSourceOriginatorAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_ORIGINATOR", 1, 248, GcSourceOriginatorAttribute.class),
		});
	}

}
