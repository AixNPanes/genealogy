package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcCertaintyAssessmentLine;
import ws.daley.genealogy.gedcom.line.GcPageLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.record.GcMultimediaLinkRecord;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;
import ws.daley.genealogy.gedcom.xref.Gc_Xref;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in FAMILY_EVENT_STRUCTURE
 *  
 * n <<SOURCE_CITATION>>							{0:M} 
 * 
 * SOURCE_CITATION:=
 * [ 
 * n SOUR @<XREF:SOUR>@    							{1:1}	// pointer to source record	
 * 	+1 PAGE <WHERE_WITHIN_SOURCE>					{0:1} 
 * 	+1 EVEN <EVENT_TYPE_CITED_FROM>					{0:1} 
 * 		+2 ROLE <ROLE_IN_EVENT>						{0:1} 
 * 	+1 DATA	{0:1} 
 * 		+2 DATE <ENTRY_RECORDING_DATE>				{0:1} 
 * 		+2 TEXT <TEXT_FROM_SOURCE>					{0:M} 
 * 			+3 [ CONC | CONT ] <TEXT_FROM_SOURCE>	{0:M} 
 * 	+1 QUAY <CERTAINTY_ASSESSMENT>					{0:1} 
 * 	+1 <<MULTIMEDIA_LINK>>							{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M} 
 * |	// Systems not using source records 
 * n SOUR <SOURCE_DESCRIPTION>						{1:1} 
 * 	+1 [ CONC | CONT ] <SOURCE_DESCRIPTION>			{0:M} 
 * 	+1 TEXT <TEXT_FROM_SOURCE>						{0:M} 
 * 		+2 [CONC | CONT ] <TEXT_FROM_SOURCE>		{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M} 
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
 */

public class GcSourceCitationXrefStructure extends Gc_Xref
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("PAGE", 0, 1, GcPageLine.class),
				new TagDescriptor("EVEN", 0, 1, GcEventTypeCitedFromStructure.class),
				new TagDescriptor("DATA", 0, 1, GcSourceCitationDataStructure.class),
				new TagDescriptor("QUAY", 0, 1, GcCertaintyAssessmentLine.class),
				new TagDescriptor("OBJE", 0, 1, GcMultimediaLinkRecord.class),
				new TagDescriptor("NOTE", 0, Integer.MAX_VALUE, GcNoteStructure.class),
				new TagDescriptor("_FOOT", 0, Integer.MAX_VALUE, GcSourceFooterStructure.class),
		});
	}
	
	public GcSourceCitationXrefStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "SOUR", null, map, _vector);
	}
};
