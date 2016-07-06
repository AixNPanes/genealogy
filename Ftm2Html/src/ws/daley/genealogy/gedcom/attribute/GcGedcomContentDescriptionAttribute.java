package ws.daley.genealogy.gedcom.attribute;


/**
 * GEDCOM_CONTENT_DESCRIPTION:=	{Size=1:248} 
 * 
 * A note that a user enters to describe the contents of the lineage-linked file 
 * in terms of "ancestors or descendants of" so that the person receiving the data 
 * knows what genealogical information the transmission contains.
 */

public class GcGedcomContentDescriptionAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("GEDCOM_CONTENT_DESCRIPTION", 1, 248, GcGedcomContentDescriptionAttribute.class),
		});
	}
}
