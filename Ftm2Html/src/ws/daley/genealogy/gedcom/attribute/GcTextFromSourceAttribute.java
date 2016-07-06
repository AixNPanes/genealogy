package ws.daley.genealogy.gedcom.attribute;

/**
 * TEXT_FROM_SOURCE:=	{Size=1:248} 
 * <TEXT> 
 * 
 * A verbatim copy of any description contained within the source. This 
 * indicates notes or text that are actually contained in the source document, 
 * not the submitter's opinion about the source. This should be, from the evidence 
 * point of view, "what the original record keeper said" as opposed to the 
 * researcher's interpretation. The word TEXT, in this case, means from the text 
 * which appeared in the source record including labels.
 */

public class GcTextFromSourceAttribute extends Gc_Attribute
{
	@SuppressWarnings("unused")
	private static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_FOOTER", 1, 248, GcTextFromSourceAttribute.class),
		});
	}
}
