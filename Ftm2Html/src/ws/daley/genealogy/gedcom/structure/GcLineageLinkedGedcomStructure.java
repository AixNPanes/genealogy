package ws.daley.genealogy.gedcom.structure;

/**
 * LINEAGE_LINKED_GEDCOM:= 
 * 
 * 	This is a model of the lineage-linked GEDCOM structure for submitting data to other lineage-linked 
 * 	GEDCOM processing systems. A header and a trailer record are required, and they can enclose any
 * 	number of data records. Tags from Appendix A (see page 71) must be used in the same context as
 * 	shown in the following form. User defined tags (see <NEW_TAG> on page 52) are discouraged but
 * 	when used must begin with an under-score.
 *  
 * 0 <<HEADER>>	{1:1)
 * 0 <<SUBMISSION_RECORD>>	{0:1)
 * 0 <<RECORD>>	{1:M}
 * 0 TRLR	{1:1}
 * 
 * RECORD:= [ 
 * n <<FAM_RECORD>>	{1:1}	
 * n <<INDIVIDUAL_RECORD>>	{1:1}	
 * n <<MULTIMEDIA_RECORD>>	{1:M}	
 * n <<NOTE_RECORD>>	{1:1}	
 * n <<REPOSITORY_RECORD>>	{1:1}	
 * n <<SOURCE_RECORD>>	{1:1}	
 * n <<SUBMITTER_RECORD>>	{1:1)
 * ]
 */

import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.record.GcFamilyRecord;
import ws.daley.genealogy.gedcom.structure.record.GcHeaderRecord;
import ws.daley.genealogy.gedcom.structure.record.GcIndividualRecord;
import ws.daley.genealogy.gedcom.structure.record.GcMultimediaLinkRecord;
import ws.daley.genealogy.gedcom.structure.record.GcNoteRecordRecord;
import ws.daley.genealogy.gedcom.structure.record.GcRepositoryRecord;
import ws.daley.genealogy.gedcom.structure.record.GcSourceRecord;
import ws.daley.genealogy.gedcom.structure.record.GcSubmissionRecord;
import ws.daley.genealogy.gedcom.structure.record.GcSubmitterRecord;
import ws.daley.genealogy.gedcom.structure.record.GcTrailerLine;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVector;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVectorMap;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

public class GcLineageLinkedGedcomStructure extends Gc_Structure
{
	@SuppressWarnings("hiding")
    private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("HEAD", 1, 1, GcHeaderRecord.class),
			new TagDescriptor("SUBN", 0, 1, GcSubmissionRecord.class),
			new TagDescriptor("FAM", 0, Integer.MAX_VALUE, GcFamilyRecord.class),
			new TagDescriptor("INDI", 0, Integer.MAX_VALUE, GcIndividualRecord.class),
			new TagDescriptor("OBJE", 0, Integer.MAX_VALUE, GcMultimediaLinkRecord.class),
			new TagDescriptor("NOTE", 0, Integer.MAX_VALUE, GcNoteRecordRecord.class),
			new TagDescriptor("REPO", 0, Integer.MAX_VALUE, GcRepositoryRecord.class),
			new TagDescriptor("SOUR", 0, Integer.MAX_VALUE, GcSourceRecord.class),
			new TagDescriptor("SUBM", 0, Integer.MAX_VALUE, GcSubmitterRecord.class),
			new TagDescriptor("TRLR", 0, 1, GcTrailerLine.class)
		});
	}
		
	public GcLineageLinkedGedcomStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "", null, tagDescriptorMap, _vector);
	}

	@Override
	public void emitXML(PrintStream stream, int indent) throws IOException
	{
		/**
		 * <?xml version="1.0"?>
		 * <!DOCTYPE GEDCOM SYSTEM "http://gedcom.org/dtd/gedxml60.dtd">
		 * <GEDCOM xmlns:html="http//:www.w3c.org/TR/REC-html40/">
		 */
		stream.println(this.tabs+"<?xml version=\"1.0\"?>");
		stream.println(this.tabs+"<!DOCTYPE GEDCOM SYSTEM \"http://gedcom.org/dtd/gedxml60.dtd\">");
		stream.println(this.tabs+"<GEDCOM xmlns:html=\"http//:www.w3c.org/TR/REC-html40/\">");
		// Clone vector map
		BaseElementVectorMap vMap = this.getVectorMap();
		BaseElementVectorMap v = (BaseElementVectorMap)vMap.clone();
		// Remove HEAD and TRLR entries
		BaseElementVector head = vMap.remove("HEAD");
		if (head.size() != 1)
			throw new RuntimeException("One and only one HEAD/HEADER tag allowed");
		BaseElementVector trlr = vMap.remove("TRLR");
		if (trlr.size() != 1)
			throw new RuntimeException("One and only one TRLR/TRAILER tag allowed");
		// do head processing
		head.get(0).emitXML(stream, indent+1);
		// do general processing
		super.emitXML(stream, indent+1);
		// do trlr processing
		trlr.get(0).emitXML(stream, indent+1);
		// restore vector map to original state
		vMap = v;
		stream.println(this.tabs+"</GEDCOM>");
	}
}
