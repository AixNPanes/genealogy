package ws.daley.genealogy.gedcom.attribute;

/**
 * USER_REFERENCE_NUMBER:=	{Size=1:20} 
 * 
 * A user-defined number or text that the submitter uses to identify this record. 
 * For instance, it may be a record number within the submitter's automated or 
 * manual system, or it may be a page and position number on a pedigree chart.
 */

public class GcUserReferenceNumberAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("USER_REFERENCE_NUMBER", 1, 20, GcUserReferenceNumberAttribute.class),
		});
	}
}
