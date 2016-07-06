package ws.daley.genealogy.gedcom.attribute;

/**
 * PERMANENT_RECORD_FILE_NUMBER:= 	{Size=1:90} 
 * <REGISTERED_RESOURCE_IDENTIFIER>:<RECORD_IDENTIFIER> 
 * 
 * The record number that uniquely identifies this record within a registered 
 * network resource. The number will be usable as a cross-reference pointer. The 
 * use of the colon (:) is reserved to indicate the separation of the "registered 
 * resource identifier" (which precedes the colon) and the unique "record 
 * identifier" within that resource (which follows the colon). If the colon is 
 * used, implementations that check pointers should not expect to find a matching 
 * cross-reference identifier in the transmission but would find it in the 
 * indicated database within a network. Making resource files available to a 
 * public network is a future implementation.
 */

public class GcPermanentRecordFileNumberAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PERMANENT_RECORD_FILE_NUMBER", 1, 1, GcPermanentRecordFileNumberAttribute.class),
		});
	}
	
	@Override
    public boolean interpret()
	{
		throw new RuntimeException("implementation");
	}
}
