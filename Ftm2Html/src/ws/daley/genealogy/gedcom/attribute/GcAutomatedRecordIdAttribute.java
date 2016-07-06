package ws.daley.genealogy.gedcom.attribute;

/**
 * AUTOMATED_RECORD_ID:=	{Size=1:12} 
 * 
 * A unique record identification number assigned to the record by the source 
 * system.  This number is intended to serve as a more sure means of 
 * identification of a record between two interfacing systems.
 */

public class GcAutomatedRecordIdAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("AUTOMATED_RECORD_ID", 1, 12, GcAutomatedRecordIdAttribute.class),
		});
	}
}
