package ws.daley.genealogy.gedcom.structure.record;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcAutomatedRecordIdLine;
import ws.daley.genealogy.gedcom.line.GcDateLine;
import ws.daley.genealogy.gedcom.line.GcEmailAddressLine;
import ws.daley.genealogy.gedcom.line.GcPhoneLine;
import ws.daley.genealogy.gedcom.line.GcRepositoryNameLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcAddressStructure;
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
 * used in LINEAGE_LINKED_GEDCOM:=
 * 
 * REPOSITORY_RECORD:=
 * n @<XREF:REPO>@ REPO	{1:1} 
 * 	+1 NAME <NAME_OF_REPOSITORY> 	{0:1}	p.50 
 * 	+1 <<ADDRESS_STRUCTURE>>	{0:1}	p.33 
 * 	+1 <<NOTE_STRUCTURE>>	{0:M}	p.37 
 * 	+1 REFN <USER_REFERENCE_NUMBER>	{0:M}	p.57, 57 
 * 		+2 TYPE <USER_REFERENCE_TYPE>	{0:1}	p.57 
 * 	+1 RIN <AUTOMATED_RECORD_ID>	{0:1}	p.42 
 * 	+1 <<CHANGE_DATE>>	{0:1}	p.33
 */

public class GcRepositoryRecord extends Gc_Structure
{
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("NAME", 0, 1, GcRepositoryNameLine.class),
				new TagDescriptor("ADDR", 0, 1, GcAddressStructure.class),
				new TagDescriptor("PHON", 0, 1, GcPhoneLine.class),
				new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class),
				new TagDescriptor("REFN", 0, 1, GcUserReferenceNumberStructure.class),
				new TagDescriptor("RIN", 0, 1, GcAutomatedRecordIdLine.class),
				new TagDescriptor("DATE", 0, 1, GcDateLine.class),
				new TagDescriptor("EMAIL", 0, 1, GcEmailAddressLine.class)
		});
	}
	
	public GcRepositoryRecord(GcBaseElement e, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "DATA", null, TagDescriptorMap.newFromArray(_tagDescriptorMap, map), _vector);
	}
};
