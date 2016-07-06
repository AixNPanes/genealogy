package ws.daley.genealogy.gedcom.structure.record;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSubmitterTextAttribute;
import ws.daley.genealogy.gedcom.line.GcAutomatedRecordIdLine;
import ws.daley.genealogy.gedcom.line.text.GcSubmitterTextConcLine;
import ws.daley.genealogy.gedcom.line.text.GcSubmitterTextContLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcChangeDateStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceCitationStructure;
import ws.daley.genealogy.gedcom.structure.GcUserReferenceNumberStructure;
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
 * NOTE_RECORD:=
 * n @<XREF:NOTE>@ NOTE <SUBMITTER_TEXT>								{1:1} 
 * 	+1 [ CONC | CONT] <SUBMITTER_TEXT>									{0:M} 
 * 	+1 <<SOURCE_CITATION>>												{0:M} 
 * 	+1 REFN <USER_REFERENCE_NUMBER>										{0:M}
 * 		+2 TYPE <USER_REFERENCE_TYPE>									{0:1} 
 * 	+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
 * 	+1 <<CHANGE_DATE>>													{0:1}
 */

public class GcNoteRecordRecord extends Gc_Xref
{
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
    private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("CONC", 0, 1, GcSubmitterTextConcLine.class),
				new TagDescriptor("CONT", 0, 1, GcSubmitterTextContLine.class),
				new TagDescriptor("SOUR", 0, 1, GcSourceCitationStructure.class),
				new TagDescriptor("REFN", 0, 1, GcUserReferenceNumberStructure.class),
				new TagDescriptor("CONT", 0, 1, GcAutomatedRecordIdLine.class),
				new TagDescriptor("CONT", 0, 1, GcChangeDateStructure.class),
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SUBMITTER_TEXT", 0, 1, GcSubmitterTextAttribute.class)
		});
	}
	
	public GcNoteRecordRecord(GcBaseElement e, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "NOTE", attributeDescriptorMap, TagDescriptorMap.newFromArray(_tagDescriptorMap, tagDescriptorMap), _vector);
	}
};
