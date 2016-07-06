package ws.daley.genealogy.gedcom.attribute;

/**
 * TIME_VALUE:=	{Size=1:12} 
 * [ hh:mm:ss.fs ] 
 * 
 * The time of a specific event, usually a computer-timed event, where:
 * hh = hours on a 24-hour clock 
 * mm = minutes 
 * ss	= seconds (optional) 
 * fs	= decimal fraction of a second (optional)
 */

public class GcTimeValueAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("TIME_VALUE", 1, 90, GcTimeValueAttribute.class),
		});
	}
}
