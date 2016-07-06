package ws.daley.genealogy.gedcom.structure.record;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.line.GcAutomatedRecordIdLine;
import ws.daley.genealogy.gedcom.line.GcFamilyFileLine;
import ws.daley.genealogy.gedcom.line.GcGenerationsOfAncestorsLine;
import ws.daley.genealogy.gedcom.line.GcGenerationsOfDescendantsLine;
import ws.daley.genealogy.gedcom.line.GcOrdinanceProcessFlagLine;
import ws.daley.genealogy.gedcom.line.GcTempleCodeLine;
import ws.daley.genealogy.gedcom.link.GcSubmitterLink;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
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
 * SUBMISSION_RECORD:=
 * n @XREF:SUBN@ SUBN						{1:1] 
 * 	+1 SUBM @XREF:SUBM@						{0:1} 
 * 	+1 FAMF <NAME_OF_FAMILY_FILE>			{0:1} 
 * 	+1 TEMP <TEMPLE_CODE>					{0:1}
 * 	+1 ANCE <GENERATIONS_OF_ANCESTORS>		{0:1} 
 * 	+1 DESC <GENERATIONS_OF_DESCENDANTS>	{0:1} 
 * 	+1 ORDI <ORDINANCE_PROCESS_FLAG>		{0:1} 
 * 	+1 RIN <AUTOMATED_RECORD_ID>			{0:1}
 * 
 * The sending system uses a submission record to send instructions
 * and information to the receiving system. TempleReady processes
 * submission records to determine which temple the cleared records
 * should be directed to. The submission record is also used for
 * communication between Ancestral File download requests and
 * TempleReady. Each GEDCOM transmission file should have only one
 * submission record. Multiple submissions are handled by creating
 * separate GEDCOM transmission files.
 */

public class GcSubmissionRecord extends Gc_Structure
{
	public String link;
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
    private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("SUBM", 0, 1, GcSubmitterLink.class),
				new TagDescriptor("FAMF", 0, 1, GcFamilyFileLine.class),
				new TagDescriptor("TEMP", 0, 1, GcTempleCodeLine.class),
				new TagDescriptor("ANCE", 0, 1, GcGenerationsOfAncestorsLine.class),
				new TagDescriptor("DESC", 0, 1, GcGenerationsOfDescendantsLine.class),
				new TagDescriptor("ORDI", 0, 1, GcOrdinanceProcessFlagLine.class),
				new TagDescriptor("RIN", 0, 1, GcAutomatedRecordIdLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{});
	}
	
	public GcSubmissionRecord(GcBaseElement e, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "SUBM", attributeDescriptorMap, TagDescriptorMap.newFromArray(_tagDescriptorMap, tagDescriptorMap), _vector);
	}
};
