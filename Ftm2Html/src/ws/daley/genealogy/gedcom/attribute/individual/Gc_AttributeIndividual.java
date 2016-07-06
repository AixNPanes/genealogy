package ws.daley.genealogy.gedcom.attribute.individual;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.line.GcAgeLine;
import ws.daley.genealogy.gedcom.line.GcAgencyLine;
import ws.daley.genealogy.gedcom.line.GcCauseLine;
import ws.daley.genealogy.gedcom.line.GcCopyrightLine;
import ws.daley.genealogy.gedcom.line.GcDateLine;
import ws.daley.genealogy.gedcom.line.GcPhoneLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcAddressStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteStructure;
import ws.daley.genealogy.gedcom.structure.GcPlaceStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceCitationStructure;
import ws.daley.genealogy.gedcom.structure.Gc_Structure;
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
 * used in INDIVIDUAL_ATTRIBUTE_STRUCTURE:=
 * [ 
 * n CAST <CASTE_NAME> 	{1:1}	p.42 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n DSCR <PHYSICAL_DESCRIPTION> 	{1:1}	p.53 
 * +1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n EDUC <SCHOLASTIC_ACHIEVEMENT> 	{1:1}	p.55 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n IDNO <NATIONAL_ID_NUMBER> 	{1:1}*	p.52 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n NATI <NATIONAL_OR_TRIBAL_ORIGIN> 	{1:1}	p.52 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n NCHI <COUNT_OF_CHILDREN> 	{1:1}	p.43 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n NMR <COUNT_OF_MARRIAGES> 	{1:1}	p.43 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n OCCU <OCCUPATION> 	{1:1}	p.52 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n PROP <POSSESSIONS> 	{1:1}	p.53 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n RELI <RELIGIOUS_AFFILIATION> 	{1:1}	p.54 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n RESI 	{1:1} 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n SSN <SOCIAL_SECURITY_NUMBER> 	{0:1}	p.55 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * | 
 * n TITL <NOBILITY_TYPE_TITLE>	{1:1}	p.52 
 * 	+1 <<EVENT_DETAIL>>	{0:1}	p.33 
 * ]
 * 
 * Note: The usage of IDNO requires that the subordinate TYPE tag be used
 * to define what kind of number is assigned to IDNO.
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

public abstract class Gc_AttributeIndividual extends Gc_Structure
{
//	public String parameters;
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("TYPE", 0, 1, GcCopyrightLine.class),
				new TagDescriptor("DATE", 0, 1, GcDateLine.class),
				new TagDescriptor("PLAC", 0, 1, GcPlaceStructure.class),
				new TagDescriptor("ADDR", 0, 1, GcAddressStructure.class),
				new TagDescriptor("PHON", 0, 1, GcPhoneLine.class),
				new TagDescriptor("AGE", 0, 1, GcAgeLine.class),
				new TagDescriptor("AGNC", 0, 1, GcAgencyLine.class),
				new TagDescriptor("CAUS", 0, 1, GcCauseLine.class),
				new TagDescriptor("SOUR", 0, 1, GcSourceCitationStructure.class),
				new TagDescriptor("OBJE", 0, 1, GcMultimediaLinkRecord.class),
				new TagDescriptor("NOTE", 0, 1, GcNoteStructure.class)
		});
	}

	public Gc_AttributeIndividual() {}
	
	public Gc_AttributeIndividual(GcBaseElement e, String _type, AttributeDescriptorMap _attributeDescriptorMap, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, _type, _attributeDescriptorMap, TagDescriptorMap.newFromArray(tagDescriptorMap, _tagDescriptorMap), _vector);
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
