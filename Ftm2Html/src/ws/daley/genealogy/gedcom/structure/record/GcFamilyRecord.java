package ws.daley.genealogy.gedcom.structure.record;

import java.util.Vector;

import ws.daley.genealogy.gedcom.event.family.GcFamilyAnulmentEvent;
import ws.daley.genealogy.gedcom.event.family.GcFamilyCensusEvent;
import ws.daley.genealogy.gedcom.event.family.GcFamilyDivorceEvent;
import ws.daley.genealogy.gedcom.event.family.GcFamilyDivorceFiledEvent;
import ws.daley.genealogy.gedcom.event.family.GcFamilyEngagementEvent;
import ws.daley.genealogy.gedcom.event.family.GcFamilyMarriageBannEvent;
import ws.daley.genealogy.gedcom.event.family.GcFamilyMarriageContractEvent;
import ws.daley.genealogy.gedcom.event.family.GcFamilyMarriageEvent;
import ws.daley.genealogy.gedcom.event.family.GcFamilyMarriageLicenseEvent;
import ws.daley.genealogy.gedcom.event.family.GcFamilyMarriageSettlementEvent;
import ws.daley.genealogy.gedcom.line.GcAutomatedRecordIdLine;
import ws.daley.genealogy.gedcom.line.GcDateLine;
import ws.daley.genealogy.gedcom.line.GcNumberOfChildrenLine;
import ws.daley.genealogy.gedcom.link.GcChildLink;
import ws.daley.genealogy.gedcom.link.GcHusbandLink;
import ws.daley.genealogy.gedcom.link.GcWifeLink;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcLDSSpouseSealingStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceCitationStructure;
import ws.daley.genealogy.gedcom.structure.GcUserReferenceNumberStructure;
import ws.daley.genealogy.gedcom.structure.Gc_Structure;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in LINEAGE_LINKED_GEDCOM:=
 * FAM_RECORD:=
 * n @<XREF:FAM>@ FAM							{1:1} 
 * 	+1 <<FAMILY_EVENT_STRUCTURE>>				{0:M} 
 * 		+2 HUSB									{0:1} 
 * 			+3 AGE <AGE_AT_EVENT>				{1:1} 
 * 		+2 WIFE									{0:1} 
 * 			+3 AGE <AGE_AT_EVENT>				{1:1} 
 * 	+1 HUSB @<XREF:INDI>@						{0:1} 
 * 	+1 WIFE @<XREF:INDI>@						{0:1} 
 * 	+1 CHIL @<XREF:INDI>@						{0:M} 
 * 	+1 NCHI <COUNT_OF_CHILDREN>					{0:1} 
 * 	+1 SUBM @<XREF:SUBM>@						{0:M} 
 * 	+1 <<LDS_SPOUSE_SEALING>>					{0:M} 
 * 	+1 <<SOURCE_CITATION>>						{0:M} 
 * 	+1 <<MULTIMEDIA_LINK>>						{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>						{0:M}
 * 	+1 REFN <USER_REFERENCE_NUMBER>				{0:M} 
 * 		+2 TYPE <USER_REFERENCE_TYPE>			{0:1} 
 * 	+1 RIN <AUTOMATED_RECORD_ID>				{0:1} 
 * 	+1 <<CHANGE_DATE>>							{0:1}
 * 
 * The FAMily record is used to record marriages, common law marriages,
 * and family unions caused by two people becoming the parents of a child.
 * There can be no more than one HUSB/father and one WIFE/mother listed in
 * each FAM_RECORD. If, for example, a man participated in more than one
 * family union, then he would appear in more than one FAM_RECORD. The
 * family record structure assumes that the HUSB/father is male and
 * WIFE/mother is female.
 * 
 * The preferred order of the CHILdren pointers within a FAMily structure
 * is chronological by birth. 
 * 
 * FAMILY_EVENT_STRUCTURE:= 
 * [ 
 * n [ ANUL | CENS | DIV | DIVF ] [Y|<NULL>]	{1:1}	
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ ENGA | MARR | MARB | MARC ] [Y|<NULL>]	{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ MARL | MARS ] [Y|<NULL>]					{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n EVEN	{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * ]
 * 
 * LDS_SPOUSE_SEALING:= 
 * n SLGS	{1:1} 
 * 	+1 STAT <LDS_SPOUSE_SEALING_DATE_STATUS>	{0:1} 
 * 	+1 DATE <DATE_LDS_ORD>						{0:1} 
 * 	+1 TEMP <TEMPLE_CODE>						{0:1}
 * 	+1 PLAC <PLACE_LIVING_ORDINANCE>			{0:1} 
 * 	+1 <<SOURCE_CITATION>>						{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>						{0:M}
 * 
 * SOURCE_CITATION:=
 * [ 
 * n SOUR @<XREF:SOUR>@    	{1:1}	p.57 // pointer to source record
 * 	+1 PAGE <WHERE_WITHIN_SOURCE>	{0:1}	p.57 
 * 	+1 EVEN <EVENT_TYPE_CITED_FROM>	{0:1}	p.46 
 * 		+2 ROLE <ROLE_IN_EVENT>	{0:1}	p.54 
 * 	+1 DATA	{0:1} +2 DATE <ENTRY_RECORDING_DATE>	{0:1}	p.46 
 * 		+2 TEXT <TEXT_FROM_SOURCE>	{0:M}	p.56 
 * 			+3 [ CONC | CONT ] <TEXT_FROM_SOURCE>	{0:M} 
 * 	+1 QUAY <CERTAINTY_ASSESSMENT>	{0:1}	p.42 
 * 	+1 <<MULTIMEDIA_LINK>>	{0:M}	p.36,29 
 * +1 <<NOTE_STRUCTURE>>	{0:M}	p.37 
 * |	// Systems not using source records
 * n SOUR <SOURCE_DESCRIPTION>	{1:1}	p.55 
 * 	+1 [ CONC | CONT ] <SOURCE_DESCRIPTION>	{0:M} 
 * 	+1 TEXT <TEXT_FROM_SOURCE>	{0:M}	p.56 
 * 		+2 [CONC | CONT ] <TEXT_FROM_SOURCE>	{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>	{0:M}	p.37 
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

public class GcFamilyRecord extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("ANUL", 0, 1, GcFamilyAnulmentEvent.class),
				new TagDescriptor("CENS", 0, 1, GcFamilyCensusEvent.class),
				new TagDescriptor("DIV", 0, 1, GcFamilyDivorceEvent.class),
				new TagDescriptor("DIVF", 0, 1, GcFamilyDivorceFiledEvent.class),
				new TagDescriptor("ENGA", 0, 1, GcFamilyEngagementEvent.class),
				new TagDescriptor("MARR", 0, 1, GcFamilyMarriageEvent.class),
				new TagDescriptor("MARB", 0, 1, GcFamilyMarriageBannEvent.class),
				new TagDescriptor("MARC", 0, 1, GcFamilyMarriageContractEvent.class),
				new TagDescriptor("MARL", 0, 1, GcFamilyMarriageLicenseEvent.class),
				new TagDescriptor("MARS", 0, 1, GcFamilyMarriageSettlementEvent.class),
				new TagDescriptor("EVEN", 0, 1, GcFamilyAnulmentEvent.class),
				new TagDescriptor("HUSB", 0, 1, GcHusbandLink.class),
				new TagDescriptor("WIFE", 0, 1, GcWifeLink.class),
				new TagDescriptor("CHIL", 0, 1, GcChildLink.class),
				new TagDescriptor("NCHI", 0, 1, GcNumberOfChildrenLine.class),
				new TagDescriptor("SUBM", 0, 1, GcSubmitterRecord.class),
				new TagDescriptor("SLGS", 0, 1, GcLDSSpouseSealingStructure.class),
				new TagDescriptor("SOUR", 0, 1, GcSourceCitationStructure.class),
				new TagDescriptor("OBJE", 0, 1, GcMultimediaLinkRecord.class),
				new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class),
				new TagDescriptor("REFN", 0, 1, GcUserReferenceNumberStructure.class),
				new TagDescriptor("RIN", 0, 1, GcAutomatedRecordIdLine.class),
				new TagDescriptor("DATE", 0, 1, GcDateLine.class)
		});
	}
	
	public GcFamilyRecord(GcBaseElement e, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "DATA", null, TagDescriptorMap.newFromArray(_tagDescriptorMap, map), _vector);
	}
};
