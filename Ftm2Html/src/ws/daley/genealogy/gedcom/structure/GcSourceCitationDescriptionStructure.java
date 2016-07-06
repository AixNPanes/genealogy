package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourceDescriptionAttribute;
import ws.daley.genealogy.gedcom.line.text.GcSourceDescriptionConcLine;
import ws.daley.genealogy.gedcom.line.text.GcSourceDescriptionContLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

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

public class GcSourceCitationDescriptionStructure extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	@SuppressWarnings("hiding")
    private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("NOTE", 0, Integer.MAX_VALUE, GcNoteStructure.class),
				new TagDescriptor("CONC", 0, Integer.MAX_VALUE, GcSourceDescriptionConcLine.class),
				new TagDescriptor("CONT", 0, Integer.MAX_VALUE, GcSourceDescriptionContLine.class),
				new TagDescriptor("TEXT", 0, Integer.MAX_VALUE, GcTextFromSourceStructure.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_DESCRIPTION", 0, 1, GcSourceDescriptionAttribute.class)
		});
	}
	
	public GcSourceCitationDescriptionStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "SOUR", attributeDescriptorMap, map, _vector);
	}
};
