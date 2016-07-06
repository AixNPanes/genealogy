package ws.daley.genealogy.gedcom.structure.record;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcAutomatedRecordIdLine;
import ws.daley.genealogy.gedcom.line.GcMultimediaFileReferenceLine;
import ws.daley.genealogy.gedcom.line.GcMultimediaFormatLine;
import ws.daley.genealogy.gedcom.line.text.GcSourceDescriptiveTitleConcLine;
import ws.daley.genealogy.gedcom.link.GcMultimediaLinkLink;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcBlobStructure;
import ws.daley.genealogy.gedcom.structure.GcDateStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteStructure;
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
 * used in SUBMITTER_RECORD
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

public class GcMultimediaLinkRecord extends Gc_Structure
{
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("FORM", 0, 1, GcMultimediaFormatLine.class),
			new TagDescriptor("TITL", 0, 1, GcSourceDescriptiveTitleConcLine.class),
			new TagDescriptor("FILE", 0, 1, GcMultimediaFileReferenceLine.class),
			new TagDescriptor("NOTE", 0, Integer.MAX_VALUE, GcNoteStructure.class),
			new TagDescriptor("BLOB", 0, Integer.MAX_VALUE, GcBlobStructure.class),
			new TagDescriptor("OBJE", 0, 1, GcMultimediaLinkLink.class),
			new TagDescriptor("REFN", 0, Integer.MAX_VALUE, GcUserReferenceNumberStructure.class),
			new TagDescriptor("RIN", 0, 1, GcAutomatedRecordIdLine.class),
			new TagDescriptor("DATE", 0, 1, GcDateStructure.class)
		});
	}

	public GcMultimediaLinkRecord(GcBaseElement e, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "OBJE", null, TagDescriptorMap.newFromArray(_tagDescriptorMap, map), _vector);
		throw new RuntimeException("implementation");
	}
	
	@Override
    public boolean interpret() {
		throw new RuntimeException("needs special processing due to optional formats");
	}
};
