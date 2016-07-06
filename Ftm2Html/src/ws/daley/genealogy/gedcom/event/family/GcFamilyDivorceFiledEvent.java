package ws.daley.genealogy.gedcom.event.family;

import java.util.Vector;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/**
 * used in FAM_RECORD:=
 * 
 * FAMILY_EVENT_STRUCTURE:= 
 * [ 
 * n [ ANUL | CENS | DIV | DIVF ] [Y|<NULL>]	{1:1}	
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * 		+2 HUSB	{0:1} +3 AGE <AGE_AT_EVENT>		{1:1} 
 * 		+2 WIFE	{0:1} +3 AGE <AGE_AT_EVENT>		{1:1} 
 * | 
 * n [ ENGA | MARR | MARB | MARC ] [Y|<NULL>]	{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * 		+2 HUSB	{0:1} +3 AGE <AGE_AT_EVENT>		{1:1} 
 * 		+2 WIFE	{0:1} +3 AGE <AGE_AT_EVENT>		{1:1} 
 * | 
 * n [ MARL | MARS ] [Y|<NULL>]					{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * 		+2 HUSB	{0:1} +3 AGE <AGE_AT_EVENT>		{1:1} 
 * 		+2 WIFE	{0:1} +3 AGE <AGE_AT_EVENT>		{1:1} 
 * | 
 * n EVEN	{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * 		+2 HUSB	{0:1} +3 AGE <AGE_AT_EVENT>		{1:1} 
 * 		+2 WIFE	{0:1} +3 AGE <AGE_AT_EVENT>		{1:1} 
 * ]
 *
 * EVENT_DETAIL:= 
 * n TYPE <EVENT_DESCRIPTOR>					{0:1} 
 * n DATE <DATE_VALUE>							{0:1} 
 * n <<PLACE_STRUCTURE>>						{0:1} 
 * n <<ADDRESS_STRUCTURE>>						{0:1} 
 * n AGE <AGE_AT_EVENT>							{0:1}
 * n AGNC <RESPONSIBLE_AGENCY>					{0:1} 
 * n CAUS <CAUSE_OF_EVENT>						{0:1} 
 * n <<SOURCE_CITATION>>						{0:M} 
 * n <<MULTIMEDIA_LINK>>						{0:M} 
 * n <<NOTE_STRUCTURE>>							{0:M}
 * 
 * PLACE_STRUCTURE:= 
 * n PLAC <PLACE_VALUE>							{1:1} 
 * 	+1 FORM <PLACE_HIERARCHY>					{0:1} 
 * 	+1 <<SOURCE_CITATION>>						{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>						{0:M}
 *
 * ADDRESS_STRUCTURE:=
 * n ADDR <ADDRESS_LINE>						{0:1}	
 *     +1 CONT <ADDRESS_LINE>					{0:M}	
 *     +1 ADR1 <ADDRESS_LINE1>					{0:1}	
 *     +1 ADR2 <ADDRESS_LINE2>					{0:1}	
 *     +1 CITY <ADDRESS_CITY>					{0:1}	
 *     +1 STAE <ADDRESS_STATE>					{0:1}	
 *     +1 POST <ADDRESS_POSTAL_CODE>			{0:1}	
 *     +1 CTRY <ADDRESS_COUNTRY>				{0:1}	
 *
 * SOURCE_CITATION:=
 * [ 
 * n SOUR @<XREF:SOUR>@    	{1:1}	p.57 // pointer to source record	
 * 	+1 PAGE <WHERE_WITHIN_SOURCE>	{0:1}	p.57 
 * 	+1 EVEN <EVENT_TYPE_CITED_FROM>	{0:1}	p.46 
 * 		+2 ROLE <ROLE_IN_EVENT>	{0:1}	p.54 
 * 	+1 DATA	{0:1} 
 * 		+2 DATE <ENTRY_RECORDING_DATE>	{0:1}	p.46 
 * 		+2 TEXT <TEXT_FROM_SOURCE>	{0:M}	p.56 
 * 			+3 [ CONC | CONT ] <TEXT_FROM_SOURCE>	{0:M} 
 * 	+1 QUAY <CERTAINTY_ASSESSMENT>	{0:1}	p.42 
 * 	+1 <<MULTIMEDIA_LINK>>	{0:M}	p.36,29 
 * 	+1 <<NOTE_STRUCTURE>>	{0:M}	p.37 
 * |	// Systems not using source records 
 * n SOUR <SOURCE_DESCRIPTION>	{1:1}	p.55 
 * 	+1 [ CONC | CONT ] <SOURCE_DESCRIPTION>	{0:M} 
 * 	+1 TEXT <TEXT_FROM_SOURCE>	{0:M}	p.56 
 * 		+2 [CONC | CONT ] <TEXT_FROM_SOURCE>	{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>	{0:M}	p.37 
 * ]
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
 * 	! A pointer to the SOURCE_RECORD, which contains a more general description
 * 	of the source.
 * 
 * 	! Information, such as a page number, on how to find the cited data within
 * 	the source. 
 * 
 * 	! Actual text from the source that was used in making assertions, for example
 * 	a date phrase as actually recorded or an applicable sentence from a letter,
 * 	would be appropriate.
 * 
 * 	! Data that allows an assessment of the relative value of one source over
 * 	another for making the recorded assertions (primary or secondary source, etc.). 
 * 	Data needed for this assessment is how much time from the asserted fact and
 * 	when the source event was recorded, what type of event was cited, and what
 * 	was the role of this person in the cited event.
 * 
 * - Date when the entry was recorded in source document, ".SOUR.DATA.DATE." 
 * - Event that initiated the recording, ".SOUR.EVEN." 
 * - Role of this person in the event, ".SOUR.EVEN.ROLE".
 *
 * MULTIMEDIA_LINK:=
 * 
 * [	
 * // embedded form 
 * n OBJE @<XREF:OBJE>@								{1:1}
 * |	
 * // linked form 
 * n OBJE 											{1:1}
 * 	+1 FORM <MULTIMEDIA_FORMAT>						{1:1} 
 * 	+1 TITL <DESCRIPTIVE_TITLE>						{0:1}
 * 	+1 FILE <MULTIMEDIA_FILE_REFERENCE>				{1:1} 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M}
 * |
 * n @XREF:OBJE@ OBJE								{1:1} 
 * 	+1 FORM <MULTIMEDIA_FORMAT>						{1:1) 
 * 	+1 TITL <DESCRIPTIVE_TITLE>						{0:1} 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M} 
 * 	+1 BLOB											{1:1} 
 * 		+2 CONT <ENCODED_MULTIMEDIA_LINE>			{1:M} 
 * 	+1 OBJE @<XREF:OBJE>@							{0:1}	// chain to continued object 	
 * 	+1 REFN <USER_REFERENCE_NUMBER>					{0:M}
 * 		+2 TYPE <USER_REFERENCE_TYPE>				{0:1} 
 * 	+1 RIN <AUTOMATED_RECORD_ID>					{0:1} 
 * 	+1 <<CHANGE_DATE>>								{0:1}
 * ]
 * 
 * Large whole multimedia objects embedded in a GEDCOM file would
 * break some systems.  For this purpose, large multimedia files
 * should be divided into smaller multimedia records by using the
 * subordinate OBJE tag to chain to the next <MULTIMEDIA_RECORD>
 * fragment.  This will allow GEDCOM records to be maintained below
 * the 32K limit for use in systems with limited resources.
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

public class GcFamilyDivorceFiledEvent extends Gc_FamilyEventStructureEvent
{
	public GcFamilyDivorceFiledEvent(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "DIVF", null, null, _vector);
	}
}
