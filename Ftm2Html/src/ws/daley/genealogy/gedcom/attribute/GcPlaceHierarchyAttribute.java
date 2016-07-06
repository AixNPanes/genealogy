package ws.daley.genealogy.gedcom.attribute;

/**
 * PLACE_HIERARCHY:= {Size=1:120} 
 * 
 * This shows the jurisdictional entities that are named in a sequence from the 
 * lowest to the highest jurisdiction. The jurisdictions are separated by commas, 
 * and any jurisdiction's name that is missing is still accounted for by a comma. 
 * When a PLAC.FORM structure is included in the HEADER of a GEDCOM transmission, 
 * it implies that all place names follow this jurisdictional format and each 
 * jurisdiction is accounted for by a comma, whether the name is known or not. 
 * When the PLAC.FORM is subordinate to an event, it temporarily overrides the 
 * implications made by the PLAC.FORM structure stated in the HEADER. This usage 
 * is not common and, therefore, not encouraged. It should only be used when a 
 * system has over-structured its place-names.
 */

public class GcPlaceHierarchyAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("PLACE_HIERARCHY", 1, 120, GcPlaceHierarchyAttribute.class),
		});
	}
}
