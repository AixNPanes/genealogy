package ws.daley.genealogy.gedcom.structure.record;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcCopyrightLine;
import ws.daley.genealogy.gedcom.line.GcDestinationLine;
import ws.daley.genealogy.gedcom.line.GcFileLine;
import ws.daley.genealogy.gedcom.line.GcLanguageLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcCharactersetStructure;
import ws.daley.genealogy.gedcom.structure.GcDateStructure;
import ws.daley.genealogy.gedcom.structure.GcGEDCStructure;
import ws.daley.genealogy.gedcom.structure.GcHeadSourceStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteGedcomContentDescriptionStructure;
import ws.daley.genealogy.gedcom.structure.GcPlaceStructure;
import ws.daley.genealogy.gedcom.structure.Gc_Structure;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;
import ws.daley.genealogy.gedcom.xref.GcSubmissionXref;
import ws.daley.genealogy.gedcom.xref.GcSubmitterXref;

/**
 * used in LINEAGE_LINKED_GEDCOM
 * 
 * HEADER:= 
 * n HEAD												{1:1} 
 *     +1 SOUR <APPROVED_SYSTEM_ID>						{1:1}	
 *         +2 VERS <VERSION_NUMBER>						{0:1}	
 *         +2 NAME <NAME_OF_PRODUCT>					{0:1}	
 *         +2 CORP <NAME_OF_BUSINESS>					{0:1}	
 *             +3 <<ADDRESS_STRUCTURE>>					{0:1}	
 *         +2 DATA <NAME_OF_SOURCE_DATA>				{0:1}	
 *             +3 DATE <PUBLICATION_DATE>				{0:1}	
 *             +3 COPR <COPYRIGHT_SOURCE_DATA>			{0:1}	
 *     +1 DEST <RECEIVING_SYSTEM_NAME>					{0:1*}	
 *     +1 DATE <TRANSMISSION_DATE>						{0:1}	
 *         +2 TIME <TIME_VALUE>							{0:1}	
 *     +1 SUBM @XREF:SUBM@								{1:1}	
 *     +1 SUBN @XREF:SUBN@								{0:1}	
 *     +1 FILE <FILE_NAME>								{0:1}	
 *     +1 COPR <COPYRIGHT_GEDCOM_FILE>					{0:1}	
 *     +1 GEDC											{1:1} 
 *         +2 VERS <VERSION_NUMBER>						{1:1}	
 *         +2 FORM <GEDCOM_FORM>						{1:1}	
 *     +1 CHAR <CHARACTER_SET>							{1:1}	
 *         +2 VERS <VERSION_NUMBER>						{0:1}	
 *     +1 LANG <LANGUAGE_OF_TEXT>						{0:1}	
 *     +1 PLAC											{0:1} 
 *         +2 FORM <PLACE_HIERARCHY>					{1:1}	
 *     +1 NOTE <GEDCOM_CONTENT_DESCRIPTION>				{0:1}	
 *         +2 [CONT|CONC] <GEDCOM_CONTENT_DESCRIPTION>	{0:M}
 * 
 * NOTE: 
 * 	Submissions to the Family History Department for Ancestral File
 * 	submission or for clearing temple ordinances  must use a
 * 	DESTination of ANSTFILE or TempleReady.  
 */

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcHeaderRecord extends Gc_Structure
{
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("SOUR", 1, 1, GcHeadSourceStructure.class),
			new TagDescriptor("DEST", 0, 1, GcDestinationLine.class),
			new TagDescriptor("DATE", 0, 1, GcDateStructure.class),
			new TagDescriptor("SUBM", 0, 1, GcSubmitterXref.class),
			new TagDescriptor("SUBN", 0, 1, GcSubmissionXref.class),
			new TagDescriptor("FILE", 0, 1, GcFileLine.class),
			new TagDescriptor("COPR", 0, 1, GcCopyrightLine.class),
			new TagDescriptor("GEDC", 0, 1, GcGEDCStructure.class),
			new TagDescriptor("CHAR", 0, 1, GcCharactersetStructure.class),
			new TagDescriptor("LANG", 0, 1, GcLanguageLine.class),
			new TagDescriptor("PLAC", 0, 1, GcPlaceStructure.class),
			new TagDescriptor("NOTE", 0, 1, GcNoteGedcomContentDescriptionStructure.class)
		});
	}

	public GcHeaderRecord() {}
	
	public GcHeaderRecord(GcBaseElement e, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "HEAD", null, TagDescriptorMap.newFromArray(_tagDescriptorMap, tagDescriptorMap), _vector);
	}
	
	@Override
    public String getXmlTag() {return "HeaderRec";}

	public GcHeadSourceStructure getSource() {return (GcHeadSourceStructure)this.getElementForKey("SOUR");}
	public GcDestinationLine getDestination() {return (GcDestinationLine)this.getElementForKey("DEST");}
	public GcDateStructure getDate() {return (GcDateStructure)this.getElementForKey("DATE");}
	public GcSubmitterXref getSubmitter() {return (GcSubmitterXref)this.getElementForKey("SUBM");}
	public GcSubmissionXref getSubmission() {return (GcSubmissionXref)this.getElementForKey("SUBN");}
	public GcFileLine getFile() {return (GcFileLine)this.getElementForKey("FILE");}
	public GcCopyrightLine getCopyright() {return (GcCopyrightLine)this.getElementForKey("COPR");}
	public GcGEDCStructure getGEDC() {return (GcGEDCStructure)this.getElementForKey("GEDC");}
	public GcCharactersetStructure getCharacterset() {return (GcCharactersetStructure)this.getElementForKey("CHAR");}
	public GcLanguageLine getLanguage() {return (GcLanguageLine)this.getElementForKey("LANG");}
	public GcPlaceStructure getPlace() {return (GcPlaceStructure)this.getElementForKey("PLAC");}
	public GcNoteGedcomContentDescriptionStructure getGedcomContentDescription() {return (GcNoteGedcomContentDescriptionStructure)this.getElementForKey("NOTE");}
};
