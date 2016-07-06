package ws.daley.genealogy.gedcom.structure.record;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcAutomatedRecordIdLine;
import ws.daley.genealogy.gedcom.line.GcSourceFiledByEntryLine;
import ws.daley.genealogy.gedcom.line.GcVersionLine;
import ws.daley.genealogy.gedcom.link.GcMultimediaLinkLink;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcAuthorStructure;
import ws.daley.genealogy.gedcom.structure.GcChangeDateStructure;
import ws.daley.genealogy.gedcom.structure.GcDataStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceDescriptiveTitleStructure;
import ws.daley.genealogy.gedcom.structure.GcSourcePublicationFactsStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceRepositoryCitationStructure;
import ws.daley.genealogy.gedcom.structure.GcTextFromSourceStructure;
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
 * n  @<XREF:SOUR>@ SOUR							{1:1}
 * 	+1 DATA											{0:1}
 * 		+2 EVEN <EVENTS_RECORDED>					{0:M}
 * 			+3 DATE <DATE_PERIOD>					{0:1}
 * 			+3 PLAC <SOURCE_JURISDICTION_PLACE>		{0:1}
 * 		+2 AGNC <RESPONSIBLE_AGENCY>				{0:1}
 * 		+2 <<NOTE_STRUCTURE>>						{0:M}
 * 	+1 AUTH <SOURCE_ORIGINATOR>						{0:1}
 * 		+2 [CONT|CONC] <SOURCE_ORIGINATOR>			{0:M}
 * 	+1 TITL <SOURCE_DESCRIPTIVE_TITLE>				{0:1}
 * 		+2 [CONT|CONC] <SOURCE_DESCRIPTIVE_TITLE>	{0:M}
 * 	+1 ABBR <SOURCE_FILED_BY_ENTRY>					{0:1}
 * 	+1 PUBL <SOURCE_PUBLICATION_FACTS>				{0:1}
 * 		+2 [CONT|CONC] <SOURCE_PUBLICATION_FACTS>	{0:M}
 * 	+1 TEXT <TEXT_FROM_SOURCE>						{0:1}
 * 		+2 [CONT|CONC] <TEXT_FROM_SOURCE>			{0:M}
 * 	+1 <<SOURCE_REPOSITORY_CITATION>>				{0:1}
 * 	+1 <<MULTIMEDIA_LINK>>							{0:M}
 * 	+1 <<NOTE_STRUCTURE>>							{0:M}
 * 	+1 REFN <USER_REFERENCE_NUMBER>					{0:M}
 * 		+2 TYPE <USER_REFERENCE_TYPE>				{0:1}
 * 	+1 RIN <AUTOMATED_RECORD_ID>					{0:1}
 * 	+1 <<CHANGE_DATE>>								{0:1}
 */

public class GcSourceRecord extends Gc_Structure
{
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("DATA", 0, 1, GcDataStructure.class),
			new TagDescriptor("VERS", 0, 1, GcVersionLine.class),
			new TagDescriptor("AUTH", 0, 1, GcAuthorStructure.class),
			new TagDescriptor("TITL", 0, 1, GcSourceDescriptiveTitleStructure.class),
			new TagDescriptor("ABBR", 0, 1, GcSourceFiledByEntryLine.class),
			new TagDescriptor("PUBL", 0, 1, GcSourcePublicationFactsStructure.class),
			new TagDescriptor("TEXT", 0, 1, GcTextFromSourceStructure.class),
			new TagDescriptor("REPO", 0, 1, GcSourceRepositoryCitationStructure.class),
			new TagDescriptor("OBJE", 0, 1, GcMultimediaLinkLink.class),
			new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class),
			new TagDescriptor("REFN", 0, 1, GcUserReferenceNumberStructure.class),
			new TagDescriptor("RIN", 0, 1, GcAutomatedRecordIdLine.class),
			new TagDescriptor("NAME", 0, 1, GcChangeDateStructure.class),
		});
	}

	public GcSourceRecord() {}
	
	public GcSourceRecord(GcBaseElement e, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "SOUR", null, TagDescriptorMap.newFromArray(_tagDescriptorMap, tagDescriptorMap), _vector);
	}
};
