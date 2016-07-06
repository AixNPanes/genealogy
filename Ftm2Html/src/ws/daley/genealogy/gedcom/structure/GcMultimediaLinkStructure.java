package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourceDescriptionAttribute;
import ws.daley.genealogy.gedcom.line.GcMultimediaFileReferenceLine;
import ws.daley.genealogy.gedcom.line.GcMultimediaFormatLine;
import ws.daley.genealogy.gedcom.line.text.GcSourceDescriptiveTitleConcLine;
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
 * MULTIMEDIA_LINK: = 
 * 
 *   [          embedded form
 *   n  OBJE @<XREF:OBJE>@  {1:1}
 *   |          linked form
 *   n  OBJE           {1:1}
 *     +1 FORM <MULTIMEDIA_FORMAT>  {1:1}
 *     +1 TITL <DESCRIPTIVE_TITLE>  {0:1}
 *     +1 FILE <MULTIMEDIA_FILE_REFERENCE>  {1:1}
 *     +1 <<NOTE_STRUCTURE>>  {0:M}
 *   ]
 * 
 * 
 * This structure provides two options in handling the GEDCOM multimedia interface. The first alternative (embedded)
 *  includes all of the data, including the multimedia object, within the transmission file. The embedded method includes
 *  pointers to GEDCOM records that contain encoded image or sound objects. Each record represents a multimedia object or
 *  object fragment. An object fragment is created by breaking the multimedia files into several multimedia object records
 *  of 32K or less. These fragments are tied together by chaining from one multimedia object fragment to the next in sequence.
 *  This procedure will help manage the size of a multimedia GEDCOM record so that existing systems which are not expecting
 *  large multimedia records may discard the records without crashing due to the size of the record. Systems which handle
 *  embedded multimedia can reconstitute the multimedia fragments by decoding the object fragments and concatenating them
 *  to the assigned multimedia file. 
 * 
 * The second method allows the GEDCOM context to be connected to an external multimedia file. This process is only managed
 * by GEDCOM in the sense that the appropriate file name is included in the GEDCOM file in context, but the maintenance and
 * transfer of the multimedia files are external to GEDCOM. 
 */

public class GcMultimediaLinkStructure extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("FORM", 1, 1, GcMultimediaFormatLine.class),
				new TagDescriptor("TITL", 0, 1, GcSourceDescriptiveTitleConcLine.class),
				new TagDescriptor("FILE", 1, 1, GcMultimediaFileReferenceLine.class),
				new TagDescriptor("NOTE", 0, Integer.MAX_VALUE, GcNoteStructure.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_DESCRIPTION", 0, 1, GcSourceDescriptionAttribute.class)
		});
	}
	
	public GcMultimediaLinkStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "SOUR", attributeDescriptorMap, map, _vector);
	}
};
