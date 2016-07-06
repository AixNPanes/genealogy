package ws.daley.genealogy.gedcom.structure.record;

import java.util.Vector;

import ws.daley.genealogy.gedcom.event.individual.GcIndividualAdoptionEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualAdultChristeningEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualBaptismEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualBarmitzvahEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualBasmitzvahEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualBirthEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualBurialEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualCensusEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualChristeningEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualConfirmationEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualDeathEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualEmigrationEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualFirstCommunionEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualGraduationEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualImmigrationEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualNaturalizationEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualOrdinationEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualProbateEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualRetirementEvent;
import ws.daley.genealogy.gedcom.event.individual.GcIndividualWillEvent;
import ws.daley.genealogy.gedcom.event.individual.Gc_IndividualEventStructureEvent;
import ws.daley.genealogy.gedcom.line.GcAncestralFileNumberLine;
import ws.daley.genealogy.gedcom.line.GcAutomatedRecordIdLine;
import ws.daley.genealogy.gedcom.line.GcPermanentRecordFileNumberLine;
import ws.daley.genealogy.gedcom.line.GcRestrictionNoticeLine;
import ws.daley.genealogy.gedcom.line.GcSexLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcChangeDateStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualAssociationStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualBaptismOrdinanceStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualCastStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualChildToFamilyStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualConfirmationOrdinanceStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualCountOfChildrenStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualCountOfMarriagesStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualEndowmentOrdinanceStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualMilitaryStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualNationalIdNumberStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualNationalOrTribalOriginStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualNobilityTypeTitleStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualOccupationStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualPhysicalDescriptionStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualReligiousAffiliationStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualResidenceStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualScholasticAchievementStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualSealingChildOrdinanceStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualSocialSecurityNumberStructure;
import ws.daley.genealogy.gedcom.structure.GcIndividualSpouseToFamilyStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteStructure;
import ws.daley.genealogy.gedcom.structure.GcPersonalNameStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceCitationStructure;
import ws.daley.genealogy.gedcom.structure.GcUserReferenceNumberStructure;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;
import ws.daley.genealogy.gedcom.xref.GcAliasXref;
import ws.daley.genealogy.gedcom.xref.GcDescendantXref;
import ws.daley.genealogy.gedcom.xref.GcSubmitterXref;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in LINEAGE_LINKED_GEDCOM:=
 * INDIVIDUAL_RECORD:=
 * n @XREF:INDI@ INDI									{1:1} 
 * 	+1 RESN <RESTRICTION_NOTICE>						{0:1} 
 * 	+1 <<PERSONAL_NAME_STRUCTURE>>						{0:M} 
 * 	+1 SEX <SEX_VALUE> 									{0:1}
 * 	+1 <<INDIVIDUAL_EVENT_STRUCTURE>>					{0:M} 
 * 	+1 <<INDIVIDUAL_ATTRIBUTE_STRUCTURE>>				{0:M}
 * 	+1 <<LDS_INDIVIDUAL_ORDINANCE>>						{0:M} 
 * 	+1 <<CHILD_TO_FAMILY_LINK>>							{0:M} 
 * 	+1 <<SPOUSE_TO_FAMILY_LINK>>						{0:M} 
 * 	+1 SUBM @<XREF:SUBM>@								{0:M} 
 * 	+1 <<ASSOCIATION_STRUCTURE>>						{0:M} 
 * 	+1 ALIA @<XREF:INDI>@								{0:M} 
 * 	+1 ANCI @<XREF:SUBM>@								{0:M} 
 * 	+1 DESI @<XREF:SUBM>@								{0:M} 
 * 	+1 <<SOURCE_CITATION>>								{0:M} 
 * 	+1 <<MULTIMEDIA_LINK>>								{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>								{0:M} 
 * 	+1 RFN <PERMANENT_RECORD_FILE_NUMBER>				{0:1} 
 * 	+1 AFN <ANCESTRAL_FILE_NUMBER>						{0:1} 
 * 	+1 REFN <USER_REFERENCE_NUMBER>						{0:M} 
 * 		+2 TYPE <USER_REFERENCE_TYPE>					{0:1} 
 * 	+1 RIN <AUTOMATED_RECORD_ID>						{0:1} 
 * 	+1 <<CHANGE_DATE>>									{0:1}
 * 
 * The individual record is a compilation of facts, known
 * or discovered, about an individual.  Sometimes these facts
 * are from different sources.  This form allows documentation
 * of the source where each of the facts were discovered.
 * 
 * The normal lineage links are shown through the use of pointers
 * from the individual to a family through either the FAMC tag or
 * the FAMS tag.  The FAMC tag provides a pointer to a family where
 * this person is a child.  The FAMS tag provides a pointer to a
 * family where this person is a spouse or parent.  The 
 * <<CHILD_TO_FAMILY_LINK>> (see page 33) structure contains a
 * FAMC pointer which is required to show any child to parent linkage
 * for pedigree navigation.  The <<CHILD_TO_FAMILY_LINK>> structure
 * also indicates whether the pedigree link represents a birth lineage,
 * an adoption lineage, or a sealing lineage.
 * 
 * Linkage  a child and the family they belonged to at the time
 * of an event can also optionally be shown by a FAMC pointer subordinate
 * to the appropriate event.  For example, a FAMC pointer subordinate to
 * an adoption event would show which family adopted this individual.
 * Biological parent or parents can be indicated by a FAMC pointer
 * subordinate to the birth event. The FAMC tag can also optionally be
 * used subordinate to an ADOPtion, or BIRTh event to differentiate which
 * set of parents were related by adoption, sealing, or birth.
 * 
 * Other associations or relationships are represented by the ASSOciation
 * tag.  The person's relation or association is the person being pointed
 * to. The association or relationship is stated by the value on the
 * subordinate RELA line.   For example:
 * 0 @I1@ INDI
 * 	1 NAME Fred/Jones/
 * 	1 ASSO @I2@
 * 		2 RELA Godfather
 *  
 * The preferred order of the CHILdren pointers within a FAMily structure
 * is chronological by birth. 
 * 
 * PERSONAL_NAME_STRUCTURE:= 
 * n NAME <NAME_PERSONAL>								{1:1} 
 * 	+1 NPFX <NAME_PIECE_PREFIX>							{0:1} 
 * 	+1 GIVN <NAME_PIECE_GIVEN>							{0:1} 
 * 	+1 NICK <NAME_PIECE_NICKNAME>						{0:1}
 * 	+1 SPFX <NAME_PIECE_SURNAME_PREFIX					{0:1} 
 * 	+1 SURN <NAME_PIECE_SURNAME>						{0:1} 
 * 	+1 NSFX <NAME_PIECE_SUFFIX>							{0:1}
 * 	+1 <<SOURCE_CITATION>>								{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>								{0:M}
 * 
 * The name value is formed in the manner the name is normally spoken, with the given name and family name (surname) separated by slashes (/). (See <NAME_PERSONAL>, page 50.) Based on the dynamic nature or unknown compositions of naming conventions, it is difficult to provide more detailed name piece structure to handle every case. The NPFX, GIVN, NICK, SPFX, SURN, and NSFX tags are provided optionally for systems that cannot operate effectively with less structured information.  For current future compatibility, all systems must construct their names based on the <NAME_PERSONAL> structure. Those using the optional name pieces should assume that few systems will process them, and most will not provide the name pieces. Future GEDCOM releases (6.0 and later) will likely apply a very different strategy to resolve this problem, possibly using a sophisticated parser and a name-knowledge database.
 * 
 * INDIVIDUAL_EVENT_STRUCTURE:=
 * [ 
 * n [ BIRT | CHR ] [Y|<NULL>]							{1:1} 
 * 	+1 <<EVENT_DETAIL>>									{0:1} 
 * 	+1 FAMC @<XREF:FAM>@								{0:1} 
 * | 
 * n [ DEAT | BURI | CREM ] [Y|<NULL>] 					{1:1} 
 * 	+1 <<EVENT_DETAIL>>									{0:1} 
 * | 
 * n ADOP [Y|<NULL>]									{1:1} 
 * 	+1 <<EVENT_DETAIL>>									{0:1} 
 * 	+1 FAMC @<XREF:FAM>@								{0:1} 
 * 		+2 ADOP <ADOPTED_BY_WHICH_PARENT>				{0:1} 
 * | 
 * n [ BAPM | BARM | BASM | BLES ] [Y|<NULL>]			{1:1} 
 * 	+1 <<EVENT_DETAIL>>									{0:1} 
 * | 
 * n [ CHRA | CONF | FCOM | ORDN ] [Y|<NULL>]			{1:1} 
 * 	+1 <<EVENT_DETAIL>>									{0:1} 
 * | 
 * n [ NATU | EMIG | IMMI ] [Y|<NULL>]					{1:1} 
 * 	+1 <<EVENT_DETAIL>>									{0:1} 
 * | 
 * n [ CENS | PROB | WILL] [Y|<NULL>]					{1:1} 
 * 	+1 <<EVENT_DETAIL>>									{0:1} 
 * | 
 * n [ GRAD | RETI ] [Y|<NULL>]							{1:1} 
 * 	+1 <<EVENT_DETAIL>>									{0:1} 
 * | 
 * n EVEN												{1:1} 
 * 	+1 <<EVENT_DETAIL>>									{0:1}
 * ]
 * 
 * The EVEN tag in this structure is for recording general events or 
 * attributes that are not shown in the above <<INDIVIDUAL_EVENT_STRUCTURE>>.
 * The general event or attribute type is declared by using a subordinate TYPE
 * tag to show what event or attribute is recorded. For example, a candidate
 * for state senate in the 1952 election could be recorded:
 * 
 * 1 EVEN
 * 	2 TYPE Election
 * 	2 DATE 07 NOV 1952
 * 	2 NOTE Candidate for State Senate.
 * 
 * The TYPE tag is also optionally used to modify the basic understanding of
 * its superior event and is usually provided by the user.  For example:
 * 
 * 1 ORDN
 * 	2 TYPE Deacon
 * 
 * The presence of a DATE tag and/or PLACe tag makes the assertion of when
 * and/or where the event took place, and therefore that the event did happen.
 * The absence of both of these tags require a Y(es) value on the parent TAG
 * line to assert that the event happened. Using this convention protects
 * GEDCOM processors which may remove (prune) lines that have no value and
 * no subordinate lines.  It  * also allows a note or source to be attached
 * to the event context without implying that the event occurred.
 * 
 * It is not proper to use a N(o) value with an event tag to infer that it
 * did not happen.  Inferring that an event did not occur would require a
 * different tag. A symbol such as using an exclamation mark (!) preceding
 * an event tag to indicate an event is known not to have happened may be
 * defined in the future.  
 * 
 * INDIVIDUAL_ATTRIBUTE_STRUCTURE:=
 * [ 
 * n CAST <CASTE_NAME> 								{1:1}	p.42 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n DSCR <PHYSICAL_DESCRIPTION> 					{1:1}	p.53 
 * +1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n EDUC <SCHOLASTIC_ACHIEVEMENT> 					{1:1}	p.55 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n IDNO <NATIONAL_ID_NUMBER> 						{1:1}*	p.52 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n NATI <NATIONAL_OR_TRIBAL_ORIGIN> 				{1:1}	p.52 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n NCHI <COUNT_OF_CHILDREN> 						{1:1}	p.43 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n NMR <COUNT_OF_MARRIAGES> 						{1:1}	p.43 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n OCCU <OCCUPATION> 								{1:1}	p.52 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n PROP <POSSESSIONS> 							{1:1}	p.53 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n RELI <RELIGIOUS_AFFILIATION> 					{1:1}	p.54 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n RESI 											{1:1} 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n SSN <SOCIAL_SECURITY_NUMBER> 					{0:1}	p.55 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * | 
 * n TITL <NOBILITY_TYPE_TITLE>						{1:1}	p.52 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 * ]
 * 
 * Note: The usage of IDNO requires that the subordinate TYPE tag be used
 * to define what kind of number is assigned to IDNO.
 * 
 * LDS_INDIVIDUAL_ORDINANCE:= 
 * [ 
 * n [ BAPL | CONL ] 								{1:1} 
 * 	+1 STAT <LDS_BAPTISM_DATE_STATUS>				{0:1}	p.48 
 * 	+1 DATE <DATE_LDS_ORD>							{0:1}	p.44 
 * 	+1 TEMP <TEMPLE_CODE>							{0:1}	p.56 
 * 	+1 PLAC <PLACE_LIVING_ORDINANCE>				{0:1}	p.53 
 * 	+1 <<SOURCE_CITATION>>							{0:M}	p.37 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M}	p.37 
 * | 
 * n ENDL											{1:1} 
 * 	+1 STAT <LDS_ENDOWMENT_DATE_STATUS>				{0:1}	p.48 
 * 	+1 DATE <DATE_LDS_ORD>							{0:1}	p.44 
 * 	+1 TEMP <TEMPLE_CODE>							{0:1}	p.56 
 * 	+1 PLAC <PLACE_LIVING_ORDINANCE>				{0:1}	p.53 
 * 	+1 <<SOURCE_CITATION>>							{0:M}	p.37 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M}	p.37 
 * |
 * n SLGC											{1:1} 
 * 	+1 STAT <LDS_CHILD_SEALING_DATE_STATUS>			{0:1}	p.48 
 * 	+1 DATE <DATE_LDS_ORD>							{0:1}	p.44 
 * 	+1 TEMP <TEMPLE_CODE>							{0:1}	p.56 
 * 	+1 PLAC <PLACE_LIVING_ORDINANCE>				{0:1}	p.53 
 * 	+1 FAMC @<XREF:FAM>@							{1:1}	p.57 
 * 	+1 <<SOURCE_CITATION>>							{0:M}	p.37 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M}	p.37 
 * ]
 * 
 * CHILD_TO_FAMILY_LINK:= 
 * n FAMC @<XREF:FAM>@								{1:1} 
 * 	+1 PEDI <PEDIGREE_LINKAGE_TYPE>					{0:M}	p.52 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M}	p.37
 * 
 * SPOUSE_TO_FAMILY_LINK:= 
 * n FAMS @<XREF:FAM>@								{1:1}	p.57 
 * +1 <<NOTE_STRUCTURE>>							{0:M}	p.37
 * 
 * ASSOCIATION_STRUCTURE:= 
 * n ASSO @<XREF:INDI>@								{0:M}	p.57 
 * 	+1 TYPE <RECORD_TYPE>							{1:1}	p.54 
 * 	+1 RELA <RELATION_IS_DESCRIPTOR>				{1:1}	p.54 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M}	p.37 
 * 	+1 <<SOURCE_CITATION>>							{0:M}	p.37
 * 
 * SOURCE_CITATION:=
 * [ 
 * n SOUR @<XREF:SOUR>@    							{1:1}	p.57 // pointer to source record
 * 	+1 PAGE <WHERE_WITHIN_SOURCE>					{0:1}	p.57 
 * 	+1 EVEN <EVENT_TYPE_CITED_FROM>					{0:1}	p.46 
 * 		+2 ROLE <ROLE_IN_EVENT>						{0:1}	p.54 
 * 	+1 DATA	{0:1}
 * 		+2 DATE <ENTRY_RECORDING_DATE>				{0:1}	p.46 
 * 		+2 TEXT <TEXT_FROM_SOURCE>					{0:M}	p.56 
 * 			+3 [ CONC | CONT ] <TEXT_FROM_SOURCE>	{0:M} 
 * 	+1 QUAY <CERTAINTY_ASSESSMENT>					{0:1}	p.42 
 * 	+1 <<MULTIMEDIA_LINK>>							{0:M}	p.36,29 
 * +1 <<NOTE_STRUCTURE>>							{0:M}	p.37 
 * |	// Systems not using source records
 * n SOUR <SOURCE_DESCRIPTION>						{1:1}	p.55 
 * 	+1 [ CONC | CONT ] <SOURCE_DESCRIPTION>			{0:M} 
 * 	+1 TEXT <TEXT_FROM_SOURCE>						{0:M}	p.56 
 * 		+2 [CONC | CONT ] <TEXT_FROM_SOURCE>		{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M}	p.37 
 * ]
 * 
 * The data provided in the <<SOURCE_CITATION>> structure is source-related
 * information specific to the data being cited. (See GEDCOM examples starting
 * on page 61.) Systems that do not use SOURCE_RECORDS must use the second
 * SOURce citation structure option.  When systems which support SOURCE_RECORD
 * structures encounter source citations which do not contain pointers to source
 * records, that system will need to create a SOURCE_RECORD and store the
 * <SOURCE_DESCRIPTION> information found in the non-structured source citation
 * in either the title area of that SOURCE_RECORD, or if the title field is not
 * large enough, place a "(See Notes)" text in the title area, and place the
 * unstructured source description in the source record's note field.
 * 
 * The information intended to be placed in the citation structure includes:
 * 		A pointer to the SOURCE_RECORD, which contains a more general description
 * 		of the source.
 * 
 * 		Information, such as a page number, on how to find the cited data within
 * 		the source. ! Actual text from the source that was used in making
 * 		assertions, for example a date phrase as actually recorded or an
 * 		applicable sentence from a letter, would be appropriate.
 * 
 * 		Data that allows an assessment of the relative value of one source
 * 		over another for making the recorded assertions (primary or secondary
 * 		source, etc.).  Data needed for this assessment is how much time from
 * 		the asserted fact and when the source event was recorded, what type
 * 		of event was cited, and what was the role of this person in the cited event.
 * 		
 * 		- Date when the entry was recorded in source document, ".SOUR.DATA.DATE." 
 * 		- Event that initiated the recording, ".SOUR.EVEN." 
 * 		- Role of this person in the event, ".SOUR.EVEN.ROLE".
 * 
 * MULTIMEDIA_LINK:=
 * [	// embedded form 
 *  n OBJE @<XREF:OBJE>@							{1:1} 
 *  |	// linked form
 *  n OBJE 	{1:1}
 *  	+1 FORM <MULTIMEDIA_FORMAT>					{1:1} 
 *  	+1 TITL <DESCRIPTIVE_TITLE>					{0:1}
 *  	+1 FILE <MULTIMEDIA_FILE_REFERENCE>			{1:1} 
 *  	+1 <<NOTE_STRUCTURE>>						{0:M} 
 *  ]
 *  
 * NOTE_STRUCTURE:= 
 * [ 
 * n NOTE @<XREF:NOTE>@								{1:1} 
 * 	+1 <<SOURCE_CITATION>>							{0:M}
 * | 
 * n NOTE [SUBMITTER_TEXT> | <NULL>]				{1:1} 
 * 	+1 [ CONC | CONT ] <SUBMITTER_TEXT>				{0:M} 
 * 	+1 <<SOURCE_CITATION>>							{0:M} 
 * ]
 */

public class GcIndividualRecord extends Gc_IndividualEventStructureEvent
{
	public String data;
	
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("RESN", 1, 1, GcRestrictionNoticeLine.class),
			new TagDescriptor("NAME", 0, Integer.MAX_VALUE, GcPersonalNameStructure.class),
			new TagDescriptor("SEX", 0, 1, GcSexLine.class),
			// start INDIVIDUAL_ATTRIBUTE_STRUCTURE
			new TagDescriptor("BIRT", 0, 1, GcIndividualBirthEvent.class),
			new TagDescriptor("CHR",  0, 1, GcIndividualChristeningEvent.class),
			new TagDescriptor("DEAT", 0, 1, GcIndividualDeathEvent.class),
			new TagDescriptor("BURI", 0, 1, GcIndividualBurialEvent.class),
			new TagDescriptor("ADOP", 0, 1, GcIndividualAdoptionEvent.class),
			new TagDescriptor("BAPM", 0, 1, GcIndividualBaptismEvent.class),
			new TagDescriptor("BARM", 0, 1, GcIndividualBarmitzvahEvent.class),
			new TagDescriptor("BASM", 0, 1, GcIndividualBasmitzvahEvent.class),
			new TagDescriptor("CHRA", 0, 1, GcIndividualAdultChristeningEvent.class),
			new TagDescriptor("CONF", 0, 1, GcIndividualConfirmationEvent.class),
			new TagDescriptor("FCOM", 0, 1, GcIndividualFirstCommunionEvent.class),
			new TagDescriptor("ORDN", 0, 1, GcIndividualOrdinationEvent.class),
			new TagDescriptor("BATU", 0, 1, GcIndividualNaturalizationEvent.class),
			new TagDescriptor("EMIG", 0, 1, GcIndividualEmigrationEvent.class),
			new TagDescriptor("IMMI", 0, 1, GcIndividualImmigrationEvent.class),
			new TagDescriptor("CENS", 0, 1, GcIndividualCensusEvent.class),
			new TagDescriptor("PROB", 0, 1, GcIndividualProbateEvent.class),
			new TagDescriptor("WILL", 0, 1, GcIndividualWillEvent.class),
			new TagDescriptor("GRAD", 0, 1, GcIndividualGraduationEvent.class),
			new TagDescriptor("RETI", 0, 1, GcIndividualRetirementEvent.class),
			new TagDescriptor("EVEN", 0, 1, GcIndividualEvent.class),
			// end INDIVIDUAL_ATTRIBUTE_STRUCTURE
			// start INDIVIDUAL_ATTRIBUTE_STRUCTURE
			new TagDescriptor("CAST", 0, 1, GcIndividualCastStructure.class),
			new TagDescriptor("DSCR", 0, 1, GcIndividualPhysicalDescriptionStructure.class),
			new TagDescriptor("EDUC", 0, 1, GcIndividualScholasticAchievementStructure.class),
			new TagDescriptor("IDNO", 0, 1, GcIndividualNationalIdNumberStructure.class),
			new TagDescriptor("NATI", 0, 1, GcIndividualNationalOrTribalOriginStructure.class),
			new TagDescriptor("NCHI", 0, 1, GcIndividualCountOfChildrenStructure.class),
			new TagDescriptor("NMR", 0, 1, GcIndividualCountOfMarriagesStructure.class),
			new TagDescriptor("OCCU", 0, 1, GcIndividualOccupationStructure.class),
			new TagDescriptor("RELI", 0, 1, GcIndividualReligiousAffiliationStructure.class),
			new TagDescriptor("RESI", 0, 1, GcIndividualResidenceStructure.class),
			new TagDescriptor("SSN", 0, 1, GcIndividualSocialSecurityNumberStructure.class),
			new TagDescriptor("TITL", 0, 1, GcIndividualNobilityTypeTitleStructure.class),
			// end INDIVIDUAL_ATTRIBUTE_STRUCTURE
			// start LDS_INDIVIDUAL_ORDINANCE
			new TagDescriptor("BAPL", 0, 1, GcIndividualBaptismOrdinanceStructure.class),
			new TagDescriptor("CONL", 0, 1, GcIndividualConfirmationOrdinanceStructure.class),
			new TagDescriptor("ENDL", 0, 1, GcIndividualEndowmentOrdinanceStructure.class),
			new TagDescriptor("SLGC", 0, 1, GcIndividualSealingChildOrdinanceStructure.class),
			// end LDS_INDIVIDUAL_ORDINANCE
			new TagDescriptor("FAMC", 0, 1, GcIndividualChildToFamilyStructure.class),	// CHILD_TO_FAMILY_LINK
			new TagDescriptor("FAMS", 0, 1, GcIndividualSpouseToFamilyStructure.class),	// SPOUSE_TO_FAMILY_LINK
			new TagDescriptor("SUBM", 0, 1, GcSubmitterXref.class),
			new TagDescriptor("ASSO", 0, 1, GcIndividualAssociationStructure.class),	// ASSOCIATION_STRUCTURE
			new TagDescriptor("ALIA", 0, 1, GcAliasXref.class),
			new TagDescriptor("DESI", 0, 1, GcDescendantXref.class),
			new TagDescriptor("SOUR", 0, Integer.MAX_VALUE, GcSourceCitationStructure.class),			// SOURCE_CITATION
			new TagDescriptor("OBJE", 0, 1, GcMultimediaLinkRecord.class),				// MULTIMEDIA_LINK
			new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class),						// NOTE_STRUCTURE
			new TagDescriptor("RFN", 0, 1, GcPermanentRecordFileNumberLine.class),
			new TagDescriptor("AFN", 0, 1, GcAncestralFileNumberLine.class),
			new TagDescriptor("REFN", 0, 1, GcUserReferenceNumberStructure.class),
			new TagDescriptor("RIN", 0, 1, GcAutomatedRecordIdLine.class),
			new TagDescriptor("CHAN", 0, 1, GcChangeDateStructure.class),				// CHANGE_DATE
			new TagDescriptor("_MILT", 0, 1, GcIndividualMilitaryStructure.class)
		});
	}
	public GcIndividualRecord() {}
	
	public GcIndividualRecord(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "INDI", null, TagDescriptorMap.newFromArray(Gc_IndividualEventStructureEvent.parentTagMap, map), _vector);
	}
};
