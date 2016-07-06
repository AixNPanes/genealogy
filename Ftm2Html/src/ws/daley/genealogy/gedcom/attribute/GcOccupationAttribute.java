package ws.daley.genealogy.gedcom.attribute;


/**
 * OCCUPATION:=	{Size=1:90} 
 * 
 * The kind of activity that an individual does for a job, profession, or 
 * principal activity.
 */

public class GcOccupationAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("OCCUPATION", 1, 90, GcOccupationAttribute.class),
		});
	}

}
