package ws.daley.genealogy.gedcom.attribute;

/**
 * PEDIGREE_LINKAGE_TYPE:=	{Size=5:7} 
 * [ adopted | birth | foster | sealing ] 
 * 
 * A code used to indicate the child to family relationship for pedigree 
 * navigation purposes.
 * 
 * Where:
 * adopted = indicates adoptive parents.
 * birth	= indicates birth parents.
 * foster = indicates child was included in a foster or guardian family.
 * sealing = indicates child was sealed to parents other than birth parents. 
 */

public class GcPedigreeLinkageTypeAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PEDIGREE_LINKAGE_TYPE", 5, 7, GcPedigreeLinkageTypeAttribute.class),
		});
	}

}
