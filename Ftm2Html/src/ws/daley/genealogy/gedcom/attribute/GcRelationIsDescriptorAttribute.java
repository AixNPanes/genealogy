package ws.daley.genealogy.gedcom.attribute;

/**
 * RELATION_IS_DESCRIPTOR:=	{Size=1:25} 
 * 
 * A word or phrase that states object 1's relation is object 2. For example you 
 * would read the following as "Joe Jacob's great grandson is the submitter 
 * pointed to by the @XREF:SUBM@":
 * 
 * 0 INDI
 * 	1 NAME Joe /Jacob/
 * 	1 ASSO @<XREF:SUBM>@
 * 		2 TYPE great grandson
 */

public class GcRelationIsDescriptorAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RELATION_IS_DESCRIPTOR", 1, 25, GcRelationIsDescriptorAttribute.class),
		});
	}
}
