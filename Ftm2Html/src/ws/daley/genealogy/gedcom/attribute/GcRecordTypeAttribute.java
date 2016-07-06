package ws.daley.genealogy.gedcom.attribute;

/**
 * RECORD_TYPE:=	{Size=3:4} 
 * [ FAM | INDI | NOTE | OBJE | REPO | SOUR | SUBM | SUBN ] 
 * 
 * An indicator of the record type being pointed to or used. For example if in 
 * an ASSOciation, an INDIvidual record were to be ASSOciated with a FAM record 
 * then:
 * 
 * 0 INDI
 * 	1 ASSO @F1@
 * 		2 TYPE FAM   /* ASSOCIATION is with a FAM record.
 * 		2 RELA Witness at marriage
 */

public class GcRecordTypeAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RECORD_TYPE", 1, 1, GcRecordTypeAttribute.class),
		});
	}
}
