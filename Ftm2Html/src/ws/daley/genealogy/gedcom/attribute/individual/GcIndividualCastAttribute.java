package ws.daley.genealogy.gedcom.attribute.individual;

import java.util.HashMap;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/**
 * used in INDIVIDUAL_ATTRIBUTE_STRUCTURE:=
 * [ 
 * n CAST <CASTE_NAME> 	{1:1}	p.42 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n DSCR <PHYSICAL_DESCRIPTION> 	{1:1}	p.53 
 * +1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n EDUC <SCHOLASTIC_ACHIEVEMENT> 	{1:1}	p.55 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n IDNO <NATIONAL_ID_NUMBER> 	{1:1}*	p.52 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n NATI <NATIONAL_OR_TRIBAL_ORIGIN> 	{1:1}	p.52 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n NCHI <COUNT_OF_CHILDREN> 	{1:1}	p.43 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n NMR <COUNT_OF_MARRIAGES> 	{1:1}	p.43 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n OCCU <OCCUPATION> 	{1:1}	p.52 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n PROP <POSSESSIONS> 	{1:1}	p.53 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n RELI <RELIGIOUS_AFFILIATION> 	{1:1}	p.54 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n RESI 	{1:1} 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n SSN <SOCIAL_SECURITY_NUMBER> 	{0:1}	p.55 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n TITL <NOBILITY_TYPE_TITLE>	{1:1}	p.52 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * ]
 * 
 * Note: The usage of IDNO requires that the subordinate TYPE tag be used
 * to define what kind of number is assigned to IDNO.
 * 
 * EVENT_DETAIL:= 
 * n TYPE <EVENT_DESCRIPTOR>	{0:1}	p.46 
 * n DATE <DATE_VALUE>	{0:1}	p.45/44 
 * n <<PLACE_STRUCTURE>>	{0:1}	p.37 
 * n <<ADDRESS_STRUCTURE>>	{0:1}	p.33 
 * n AGE <AGE_AT_EVENT>	{0:1}	p.41 
 * n AGNC <RESPONSIBLE_AGENCY>	{0:1}	p.54 
 * n CAUS <CAUSE_OF_EVENT>	{0:1}	p.42 
 * n <<SOURCE_CITATION>>	{0:M}	p.37 
 * n <<MULTIMEDIA_LINK>>	{0:M}	p.36,29 
 * n <<NOTE_STRUCTURE>>	{0:M}	p.37
 */

public class GcIndividualCastAttribute extends Gc_AttributeIndividual
{
	
	public static HashMap<String, Integer[]> classCounts;
	
	public GcIndividualCastAttribute(GcBaseElement e) {
		super(e, "CAST", null, null, null);
	}

}
