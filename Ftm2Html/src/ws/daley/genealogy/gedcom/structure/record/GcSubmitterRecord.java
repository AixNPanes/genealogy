package ws.daley.genealogy.gedcom.structure.record;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcAutomatedRecordIdLine;
import ws.daley.genealogy.gedcom.line.GcLanguageLine;
import ws.daley.genealogy.gedcom.line.GcNameLine;
import ws.daley.genealogy.gedcom.line.GcPhoneLine;
import ws.daley.genealogy.gedcom.line.GcRegisteredFileNumberLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.GcInputLine;
import ws.daley.genealogy.gedcom.structure.GcAddressStructure;
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
 * SUBMITTER_RECORD:=
 * 
 * n @<XREF:SUBM>@ SUBM								{1:1} 
 * 	+1 NAME <SUBMITTER_NAME>						{1:1} 
 * 	+1 <<ADDRESS_STRUCTURE>>						{0:1} 
 * 	+1 <<MULTIMEDIA_LINK>>							{0:M} 
 * 	+1 LANG <LANGUAGE_PREFERENCE>					{0:3} 
 * 	+1 RFN <SUBMITTER_REGISTERED_RFN>				{0:1} 
 * 	+1 RIN <AUTOMATED_RECORD_ID>					{0:1} 
 * 	+1 <<CHANGE_DATE>> 								{0:1}
 * 
 * <<ADDRESS_STRUCTURE>>:=
 * 
 * n ADDR <ADDRESS_LINE>							{0:1} 
 * 	+1 CONT <ADDRESS_LINE>							{0:M} 
 * 	+1 ADR1 <ADDRESS_LINE1>							{0:1} 
 * 	+1 ADR2 <ADDRESS_LINE2>							{0:1} 
 * 	+1 CITY <ADDRESS_CITY>							{0:1} 
 * 	+1 STAE <ADDRESS_STATE>							{0:1} 
 * 	+1 POST <ADDRESS_POSTAL_CODE>					{0:1} 
 * 	+1 CTRY <ADDRESS_COUNTRY>						{0:1} 
 * n PHON <PHONE_NUMBER>							{0:3)
 * 
 * <<MULTIMEDIA_LINK>>:=
 * 
 * MULTIMEDIA_LINK:=
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
 * 	+1 BLOB	{1:1} +2 CONT <ENCODED_MULTIMEDIA_LINE>	{1:M} 
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
 * <<CHANGE_DATE>>:= 
 * n CHAN											{1:1} 
 * 	+1 DATE <CHANGE_DATE>							{1:1}
 * 		+2 TIME <TIME_VALUE>						{0:1} 
 * 	+1 <<NOTE_STRUCTURE>>							{0:M} 
 * 
 * The change date is intended to only record the last change to a
 * record.  Some systems may want to manage the change process with
 * more detail, but it is sufficient for GEDCOM purposes to indicate
 * the last time that a record was modified.
 */

public class GcSubmitterRecord extends Gc_Structure
{
	public String xref;
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("NAME", 0, 1, GcNameLine.class),
				new TagDescriptor("ADDR", 0, 1, GcAddressStructure.class),
				new TagDescriptor("PHON", 0, 1, GcPhoneLine.class),
				new TagDescriptor("OBJE", 0, 1, GcMultimediaLinkRecord.class),
				new TagDescriptor("LANG", 0, 1, GcLanguageLine.class),
				new TagDescriptor("RFN", 0, 1, GcRegisteredFileNumberLine.class),
				new TagDescriptor("RIN", 0, 1, GcAutomatedRecordIdLine.class),
				new TagDescriptor("CHAN", 0, 1, GcAutomatedRecordIdLine.class)
		});
	}
	
	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		this.parameters = getParameters();
		this.xref = this.getLine().getXref();
		return true;
	}
	
	public GcSubmitterRecord(GcBaseElement e, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "SUBM", null, TagDescriptorMap.newFromArray(_tagDescriptorMap, map), _vector);
		interpret();
	}
	
	public String getXref() {return this.xref;}
};
