package ws.daley.genealogy.gedcom.attribute;


/**
 * PUBLICATION_DATE:=	{Size=10:11} 
<DATE_EXACT> 

The date this source was published or created.
 */

public class GcPublicationDateAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PUBLICATION_DATE", 10, 11, GcPublicationDateAttribute.class),
		});
	}

}
