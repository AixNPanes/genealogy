package ws.daley.genealogy.gedcom.attribute;


/**
 * COPYRIGHT_SOURCE_DATA:=	{Size=1:90} 
 * 
 * A copyright statement required by the owner of data from which this information 
 * was down- loaded.  For example, when a GEDCOM down-load is requested from the 
 * Ancestral File, this would be the copyright statement to indicate that the data 
 * came from a copyrighted source.
 */

public class GcCopyrightSourceDataAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("COPYRIGHT_SOURCE_DATA", 1, 90, GcCopyrightSourceDataAttribute.class),
		});
	}
}
