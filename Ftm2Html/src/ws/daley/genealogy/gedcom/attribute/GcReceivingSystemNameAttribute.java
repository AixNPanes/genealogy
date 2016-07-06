package ws.daley.genealogy.gedcom.attribute;

/**
 * RECEIVING_SYSTEM_NAME:=	{Size=1:20} 
 * 
 * The name of the system expected to process the GEDCOM-compatible transmission. 
 * The registered RECEIVING_SYSTEM_NAME for all GEDCOM submissions to the Family 
 * History Department must be one of the following names:
 * 
 * ! "ANSTFILE" when submitting to Ancestral File.
 * ! "TempleReady" when submitting for temple ordinance clearance.
 */

public class GcReceivingSystemNameAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RECEIVING_SYSTEM_NAME", 1, 20, GcReceivingSystemNameAttribute.class),
		});
	}
}
