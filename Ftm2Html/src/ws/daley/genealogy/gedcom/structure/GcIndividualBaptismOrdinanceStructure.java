package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcDateLDSOrdinanceLine;
import ws.daley.genealogy.gedcom.line.GcLDSBaptismDateStatusLine;
import ws.daley.genealogy.gedcom.line.GcPlaceLivingOrdinanceLine;
import ws.daley.genealogy.gedcom.line.GcTempleCodeLine;
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
 * used in LDS_INDIVIDUAL_ORDINANCE:= 
 * [ 
 * n [ BAPL | CONL ] {1:1} 
 * 	+1 STAT <LDS_BAPTISM_DATE_STATUS>	{0:1}	p.48 
 * 	+1 DATE <DATE_LDS_ORD>	{0:1}	p.44 
 * 	+1 TEMP <TEMPLE_CODE>	{0:1}	p.56 
 * 	+1 PLAC <PLACE_LIVING_ORDINANCE>	{0:1}	p.53 
 * 	+1 <<SOURCE_CITATION>>	{0:M}	p.37 
 * 	+1 <<NOTE_STRUCTURE>>	{0:M}	p.37 
 * | 
 * n ENDL	{1:1} 
 * 	+1 STAT <LDS_ENDOWMENT_DATE_STATUS>	{0:1}	p.48 
 * 	+1 DATE <DATE_LDS_ORD>	{0:1}	p.44 
 * 	+1 TEMP <TEMPLE_CODE>	{0:1}	p.56 
 * 	+1 PLAC <PLACE_LIVING_ORDINANCE>	{0:1}	p.53 
 * 	+1 <<SOURCE_CITATION>>	{0:M}	p.37 
 * 	+1 <<NOTE_STRUCTURE>>	{0:M}	p.37 
 * | 
 * n SLGC	{1:1} 
 * 	+1 STAT <LDS_CHILD_SEALING_DATE_STATUS>	{0:1}	p.48 
 * 	+1 DATE <DATE_LDS_ORD>	{0:1}	p.44 
 * 	+1 TEMP <TEMPLE_CODE>	{0:1}	p.56 
 * 	+1 PLAC <PLACE_LIVING_ORDINANCE>	{0:1}	p.53 
 * 	+1 FAMC @<XREF:FAM>@	{1:1}	p.57 
 * 	+1 <<SOURCE_CITATION>>	{0:M}	p.37 
 * 	+1 <<NOTE_STRUCTURE>>	{0:M}	p.37 
 * ]
 */

public class GcIndividualBaptismOrdinanceStructure extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("STAT", 1, 1, GcLDSBaptismDateStatusLine.class),
			new TagDescriptor("DATE", 0, 1, GcDateLDSOrdinanceLine.class),
			new TagDescriptor("TEMP", 0, 1, GcTempleCodeLine.class),
			new TagDescriptor("PLAC", 0, 1, GcPlaceLivingOrdinanceLine.class),
			new TagDescriptor("SOUR", 0, 1, GcSourceCitationStructure.class),
			new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class)
		});
	}
	
	public GcIndividualBaptismOrdinanceStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "BAPL", null, map, _vector);
	}
};
