package ws.daley.genealogy.gedcom.attribute;


/**
 * CERTAINTY_ASSESSMENT:=	{Size=1:1} 
 * [ 0 | 1 | 2 | 3 ] 
 * 
 * The QUAY tag's value conveys the submitter's quantitative evaluation of the 
 * credibility of a piece of information, based upon its supporting evidence. 
 * Some systems use this feature to rank multiple conflicting opinions for 
 * display of most likely information first. It is not intended to eliminate the 
 * receiver's need to evaluate the evidence for themselves.
 * 0 = Unreliable evidence or estimated data
 * 1 = Questionable reliability of evidence (interviews, census, oral 
 * 	genealogies, or potential for bias for example, an autobiography)
 * 2 = Secondary evidence, data officially recorded sometime after event
 * 3 = Direct and primary evidence used, or by dominance of the evidence
 */

public class GcCertaintyAssessmentAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("CERTAINTY_ASSESSMENT", 1, 1, GcCertaintyAssessmentAttribute.class),
		});
	}

}
