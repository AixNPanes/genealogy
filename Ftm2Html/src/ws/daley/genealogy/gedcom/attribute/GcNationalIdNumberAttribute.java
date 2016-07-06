package ws.daley.genealogy.gedcom.attribute;

/**
 * NATIONAL_ID_NUMBER:=	{Size=1:30} 
 * 
 * A nationally-controlled number assigned to an individual. Commonly known 
 * national numbers should be assigned their own tag, such as SSN for U.S. 
 * Social Security Number. The use of the IDNO tag requires a subordinate TYPE 
 * tag to identify what kind of number is being stored.
 * 
 * For example:
 * n IDNO 43-456-1899 
 * 	+1 TYPE Canadian Health Registration
 */

public class GcNationalIdNumberAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NATIONAL_ID_NUMBER", 1, 30, GcNationalIdNumberAttribute.class),
		});
	}

}
