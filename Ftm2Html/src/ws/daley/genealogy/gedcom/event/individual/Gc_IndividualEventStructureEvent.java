package ws.daley.genealogy.gedcom.event.individual;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.event.Gc_Event;
import ws.daley.genealogy.gedcom.line.GcAgeLine;
import ws.daley.genealogy.gedcom.line.GcAgencyLine;
import ws.daley.genealogy.gedcom.line.GcCauseLine;
import ws.daley.genealogy.gedcom.line.GcCopyrightLine;
import ws.daley.genealogy.gedcom.line.GcDateLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcAddressStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteStructure;
import ws.daley.genealogy.gedcom.structure.GcPlaceStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceCitationStructure;
import ws.daley.genealogy.gedcom.structure.record.GcMultimediaLinkRecord;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in INDIVIDUAL_RECORD:=
 * 
 * 
 * INDIVIDUAL_EVENT_STRUCTURE:=
 * [ 
 * n [ BIRT | CHR ] [Y|<NULL>]					{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * 	+1 FAMC @<XREF:FAM>@						{0:1} 
 * | 
 * n [ DEAT | BURI | CREM ] [Y|<NULL>] 			{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n ADOP [Y|<NULL>]							{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * 	+1 FAMC @<XREF:FAM>@						{0:1} 
 * 		+2 ADOP <ADOPTED_BY_WHICH_PARENT>		{0:1} 
 * | 
 * n [ BAPM | BARM | BASM | BLES ] [Y|<NULL>]	{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ CHRA | CONF | FCOM | ORDN ] [Y|<NULL>]	{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ NATU | EMIG | IMMI ] [Y|<NULL>]			{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ CENS | PROB | WILL] [Y|<NULL>]			{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n [ GRAD | RETI ] [Y|<NULL>]					{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1} 
 * | 
 * n EVEN										{1:1} 
 * 	+1 <<EVENT_DETAIL>>							{0:1}
 * ]
 * 
 * EVENT_DETAIL:= 
 * n TYPE <EVENT_DESCRIPTOR>	{0:1}	p.46 
 * n DATE <DATE_VALUE>	{0:1}	p.45/44 
 * n <<PLACE_STRUCTURE>>	{0:1}	p.37 
 * n <<ADDRESS_STRUCTURE>>	{0:1}	p.33 
 * n AGE <AGE_AT_EVENT>	{0:1}	p.41 
 * n AGNC <RESPONSIBLE_AGENCY>	{0:1}	p.54 
 * n CAUS <CAUSE_OF_EVENT>	{0:1}	p.42 
 * n <<SOURCE_CITATION>>	{0:M}	p.37 
 * n <<MULTIMEDIA_LINK>>	{0:M}	p.36,29 
 * n <<NOTE_STRUCTURE>>	{0:M}	p.37
 */

public abstract class Gc_IndividualEventStructureEvent extends Gc_Event
{
	public static TagDescriptorMap parentTagMap = new TagDescriptorMap();
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		parentTagMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("BIRT", 0, Integer.MAX_VALUE, GcIndividualBirthEvent.class),
				new TagDescriptor("CHR",  0, Integer.MAX_VALUE, GcIndividualChristeningEvent.class),
				new TagDescriptor("DEAT", 0, Integer.MAX_VALUE, GcIndividualDeathEvent.class),
				new TagDescriptor("BURI", 0, Integer.MAX_VALUE, GcIndividualBurialEvent.class),
				new TagDescriptor("CREM", 0, Integer.MAX_VALUE, GcIndividualCremationEvent.class),
				new TagDescriptor("ADOP", 0, Integer.MAX_VALUE, GcIndividualAdoptionEvent.class),
				new TagDescriptor("BAPM", 0, Integer.MAX_VALUE, GcIndividualBaptismEvent.class),
				new TagDescriptor("BARM", 0, Integer.MAX_VALUE, GcIndividualBarmitzvahEvent.class),
				new TagDescriptor("BASM", 0, Integer.MAX_VALUE, GcIndividualBasmitzvahEvent.class),
				new TagDescriptor("BLES", 0, Integer.MAX_VALUE, GcIndividualBlessingEvent.class),
				new TagDescriptor("CHRA", 0, Integer.MAX_VALUE, GcIndividualAdultChristeningEvent.class),
				new TagDescriptor("CONF", 0, Integer.MAX_VALUE, GcIndividualConfirmationEvent.class),
				new TagDescriptor("FCOM", 0, Integer.MAX_VALUE, GcIndividualFirstCommunionEvent.class),
				new TagDescriptor("ORDN", 0, Integer.MAX_VALUE, GcIndividualOrdinationEvent.class),
				new TagDescriptor("NATU", 0, Integer.MAX_VALUE, GcIndividualNaturalizationEvent.class),
				new TagDescriptor("EMIG", 0, Integer.MAX_VALUE, GcIndividualEmigrationEvent.class),
				new TagDescriptor("IMMI", 0, Integer.MAX_VALUE, GcIndividualImmigrationEvent.class),
				new TagDescriptor("CENS", 0, Integer.MAX_VALUE, GcIndividualCensusEvent.class),
				new TagDescriptor("PROB", 0, Integer.MAX_VALUE, GcIndividualProbateEvent.class),
				new TagDescriptor("WILL", 0, Integer.MAX_VALUE, GcIndividualWillEvent.class),
				new TagDescriptor("GRAD", 0, Integer.MAX_VALUE, GcIndividualBirthEvent.class),
				new TagDescriptor("RETI", 0, Integer.MAX_VALUE, GcIndividualBirthEvent.class),
				new TagDescriptor("EVEN", 0, Integer.MAX_VALUE, GcIndividualBirthEvent.class),
		});
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("TYPE", 0, 1, GcCopyrightLine.class),
				new TagDescriptor("DATE", 0, 1, GcDateLine.class),
				new TagDescriptor("PLAC", 0, 1, GcPlaceStructure.class),
				new TagDescriptor("ADDR", 0, 1, GcAddressStructure.class),
				new TagDescriptor("AGE", 0, 1, GcAgeLine.class),
				new TagDescriptor("AGNC", 0, 1, GcAgencyLine.class),
				new TagDescriptor("CAUS", 0, 1, GcCauseLine.class),
				new TagDescriptor("SOUR", 0, 1, GcSourceCitationStructure.class),
				new TagDescriptor("OBJE", 0, 1, GcMultimediaLinkRecord.class),
				new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class)
		});
	}

	public Gc_IndividualEventStructureEvent() {}
	
	public Gc_IndividualEventStructureEvent(GcBaseElement e, String _type, AttributeDescriptorMap attributeDescriptorMap, TagDescriptorMap map, Vector<GcBaseElement> _vector)
	{
		super(e, _type, attributeDescriptorMap, TagDescriptorMap.newFromArray(Gc_IndividualEventStructureEvent.map, map), _vector);
	}
	
	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		this.parameters = getParameters();
		if (this.parameters.length() == 0)
			return false;
		if (this.elements.size() > 0)
			return false;
		if (this.line.getLink().length() > 0)
			return false;
		if (this.line.getXref().length() > 0)
			return false;
		return true;
	}
};
