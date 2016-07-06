package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcNamePersonalAttribute;
import ws.daley.genealogy.gedcom.line.GcNamePieceGivenLine;
import ws.daley.genealogy.gedcom.line.GcNamePieceNicknameLine;
import ws.daley.genealogy.gedcom.line.GcNamePiecePrefixLine;
import ws.daley.genealogy.gedcom.line.GcNamePieceSuffixLine;
import ws.daley.genealogy.gedcom.line.GcNamePieceSurnameLine;
import ws.daley.genealogy.gedcom.line.GcNamePieceSurnamePrefixLine;
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
 * used in INDIVIDUAL_RECORD
 * n RESN <RESTRICTION_NOTICE>			{0:1} 
 *  * 
 * PERSONAL_NAME_STRUCTURE:= 
 * n NAME <NAME_PERSONAL>						{1:1} 
 * 	+1 NPFX <NAME_PIECE_PREFIX>					{0:1} 
 * 	+1 GIVN <NAME_PIECE_GIVEN>					{0:1} 
 * 	+1 NICK <NAME_PIECE_NICKNAME>				{0:1}
 * 	+1 SPFX <NAME_PIECE_SURNAME_PREFIX			{0:1} 
 * 	+1 SURN <NAME_PIECE_SURNAME>				{0:1} 
 * 	+1 NSFX <NAME_PIECE_SUFFIX>					{0:1}
 * 	+1 <<SOURCE_CITATION>>						{0:M} 
 * 	+1 <<NOTE_STRUCTURE>>						{0:M}
 */

public class GcPersonalNameStructure extends Gc_Structure
{
	private static TagDescriptorMap map = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("NPFX", 1, 1, GcNamePiecePrefixLine.class),
			new TagDescriptor("GIVN", 0, 1, GcNamePieceGivenLine.class),
			new TagDescriptor("NICK", 0, 1, GcNamePieceNicknameLine.class),
			new TagDescriptor("SPFX", 0, 1, GcNamePieceSurnamePrefixLine.class),
			new TagDescriptor("SURN", 0, 1, GcNamePieceSurnameLine.class),
			new TagDescriptor("NSFX", 0, 1, GcNamePieceSuffixLine.class),
			new TagDescriptor("SOUR", 0, 1, GcSourceCitationStructure.class),
			new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_PERSONAL", 0, 1, GcNamePersonalAttribute.class)
		});
	}
	
	public GcPersonalNameStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "GEDC", attributeDescriptorMap, map, _vector);
	}
};
