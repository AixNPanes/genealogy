package ws.daley.genealogy.gedcom.attribute;


/**
 * ADDRESS_CITY:=	{Size=1:60} 
 * 
 * The name of the city used in the address. Isolated for sorting or indexing.
 */

/**
 * SCHOLASTIC_ACHIEVEMENT:=	{Size=1:248} 
 * 
 * A description of a scholastic or educational achievement or pursuit.
 */

public class GcScholasticAchievementAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SCHOLASTIC_ACHIEVEMENT", 1, 248, GcScholasticAchievementAttribute.class),
		});
	}

}
