package ws.daley.genealogy.gedcom.attribute;


/**
 * GEDCOM_FORM:=	{Size=14:20} 
 * [ LINEAGE-LINKED ] 
 * 
 * The GEDCOM form used to construct this transmission. There maybe other forms 
 * used such as CommSoft's "EVENT_LINEAGE_LINKED" but these specifications define 
 * only the LINEAGELINKED Form.  Systems will use this value to specify GEDCOM 
 * compatible with these specifications.
 */

public class GcGecdomFormAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("GEDCOM_FORM", 14, 20, GcGecdomFormAttribute.class),
		});
	}

}
