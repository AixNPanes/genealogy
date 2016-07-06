package ws.daley.gedcom.xml;

import javax.xml.bind.annotation.XmlElement;

import ws.daley.gedcom.Citation;
import ws.daley.gedcom.ContactRec;
import ws.daley.gedcom.FileCreation;
import ws.daley.gedcom.HeaderRec;
import ws.daley.gedcom.Name;
import ws.daley.gedcom.Note;
import ws.daley.gedcom.Product;
import ws.daley.gedcom.Submitter;
import ws.daley.genealogy.gedcom.line.GcAddressCityLine;
import ws.daley.genealogy.gedcom.line.GcAddressCountryLine;
import ws.daley.genealogy.gedcom.line.GcAddressLine1;
import ws.daley.genealogy.gedcom.line.GcAddressLine2;
import ws.daley.genealogy.gedcom.line.GcAddressLineCont;
import ws.daley.genealogy.gedcom.line.GcAddressPostalCodeLine;
import ws.daley.genealogy.gedcom.line.GcAddressStateLine;
import ws.daley.genealogy.gedcom.line.GcCopyrightLine;
import ws.daley.genealogy.gedcom.line.GcDateLine;
import ws.daley.genealogy.gedcom.line.GcDestinationLine;
import ws.daley.genealogy.gedcom.line.GcFileLine;
import ws.daley.genealogy.gedcom.line.GcFormLine;
import ws.daley.genealogy.gedcom.line.GcFormPlaceHierarchyLine;
import ws.daley.genealogy.gedcom.line.GcLanguageLine;
import ws.daley.genealogy.gedcom.line.GcNoteConcLine;
import ws.daley.genealogy.gedcom.line.GcNoteContLine;
import ws.daley.genealogy.gedcom.line.GcPhoneLine;
import ws.daley.genealogy.gedcom.line.GcSourceNameLine;
import ws.daley.genealogy.gedcom.line.GcSourceTitleLine;
import ws.daley.genealogy.gedcom.line.GcTimeLine;
import ws.daley.genealogy.gedcom.line.GcVersionLine;
import ws.daley.genealogy.gedcom.link.GcRepositoryLink;
import ws.daley.genealogy.gedcom.structure.GcAddressStructure;
import ws.daley.genealogy.gedcom.structure.GcCharactersetStructure;
import ws.daley.genealogy.gedcom.structure.GcCorporationStructure;
import ws.daley.genealogy.gedcom.structure.GcDataStructure;
import ws.daley.genealogy.gedcom.structure.GcDateStructure;
import ws.daley.genealogy.gedcom.structure.GcGEDCStructure;
import ws.daley.genealogy.gedcom.structure.GcHeadSourceStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteGedcomContentDescriptionStructure;
import ws.daley.genealogy.gedcom.structure.GcPlaceStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceNoteStructure;
import ws.daley.genealogy.gedcom.structure.record.GcHeaderRecord;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVectorMap;
import ws.daley.genealogy.gedcom.xref.GcSubmissionXref;
import ws.daley.genealogy.gedcom.xref.GcSubmitterXref;

public class GxHeaderRec extends HeaderRec
{
//    @XmlElement(name = "FileCreation", required = true)
//    protected FileCreation fileCreation;
//    @XmlElement(name = "Citation")
//    protected Citation citation;
//    @XmlElement(name = "Submitter", required = true)
//    protected Submitter submitter;
//    @XmlElement(name = "Note")
//    protected Note note;
	
	public GxHeaderRec(GcHeaderRecord headerRecord)
	{
		super();
		GcHeadSourceStructure gcHeadSourceStructure = headerRecord.getSource();
		GcDestinationLine gcDestinationLine = headerRecord.getDestination();
		GcDateStructure gcDateStructure = headerRecord.getDate();
		GcSubmitterXref gcSubmitterXref = headerRecord.getSubmitter();
		GcSubmissionXref gcSubmissionXref = headerRecord.getSubmission();
		GcFileLine gcFileLine = headerRecord.getFile();
		GcCopyrightLine gcCopyrithtLine = headerRecord.getCopyright();
		GcGEDCStructure gcGEDCStructure = headerRecord.getGEDC();
		GcCharactersetStructure gcCharactersetStructure = headerRecord.getCharacterset();
		GcLanguageLine gcLanguageLine = headerRecord.getLanguage();
		GcPlaceStructure gcPlaceStructure = headerRecord.getPlace();
		GcNoteGedcomContentDescriptionStructure gcNoteGedcomContentDescriptionStructure = headerRecord.getGedcomContentDescription();

		GxFileCreation gxFileCreation = new GxFileCreation(headerRecord);
		if (!gxFileCreation.empty())
			this.fileCreation = gxFileCreation;
		GxCitatiom gxCitation = new GxCitation();
		if (!gxCitation.empty())
			this.citation = gxCitation;
		GxSubmitter gxSubmitter = new GxSubmitter();
		if (!gxSubmitter.empty())
			this.submitter = gxSubmitter;
		GxFileCreationNote gxFileCreationNote = new GxFileCreationNote();
		if (!gxFileCreationNote.empty())
			this.note = gxFileCreationNote;
		
		GcNoteGedcomContentDescriptionStructure gcNoteGedcomContentDescriptionStructure = null;
		BaseElementVectorMap gcHeaderRecordMap = null;
		GcHeadSourceStructure gcHeadSourceStructure = null;
		GcDestinationLine gcDestinationLine = null;
		GcDateStructure gcDateStructure = null;
		GcSubmitterXref gcSubmitterXref = null;
		GcSubmissionXref gcSubmissionXref = null;
		GcFileLine gcFileLine = null;
		GcCopyrightLine gcCopyrightLine = null;
		GcGEDCStructure gcGEDCStructure = null;
		GcLanguageLine gcLanguageLine = null;
		GcPlaceStructure gcPlaceStructure = null;
		GcCharactersetStructure gcCharactersetStructure = null;
		BaseElementVectorMap gcHeadSourceStructureMap = null;
		GcVersionLine gcVersionLine = null;
		GcSourceNameLine gcSourceNameLine = null;
		GcCorporationStructure gcCorporationStructure = null;;
		GcDataStructure gcDataStructure = null;
		GcSourceTitleLine gcSourceTitleLine = null;
		GcRepositoryLink gcRepositoryLink = null;;
		GcSourceNoteStructure gcSourceNoteStructure = null;
		BaseElementVectorMap gcCorporationStructureMap = null;
		GcAddressStructure gcAddressStructure = null;
		GcPhoneLine gcPhoneLine = null;
		BaseElementVectorMap gcAddressStructureMap = null;
		GcAddressLineCont gcAddressLineCont = null;
		GcAddressLine1 gcAddressLine1 = null;
		GcAddressLine2 gcAddressLine2 = null;
		GcAddressCityLine gcAddressCityLine = null;
		GcAddressStateLine gcAddressStateLine = null;
		GcAddressPostalCodeLine gcAddressPostalCodeLine = null;
		GcAddressCountryLine gcAddressCountryLine = null;
		BaseElementVectorMap gcDataStructureMap = null;
		GcDateLine gcDateLine = null;
		GcCopyrightLine gcCopyrightLine2 = null;
		BaseElementVectorMap gcDateStructureMap = null;
		GcTimeLine gcTimeLine = null;
		BaseElementVectorMap gcGEDCStructureMap = null;
		GcVersionLine gcVersionLine2 = null;
		GcFormLine gcFormLine = null;
		BaseElementVectorMap gcCharactersetStructureMap = null;
		GcVersionLine gcVersionLine3 = null;
		BaseElementVectorMap gcPlaceStructureMap = null;
		GcFormPlaceHierarchyLine gcFormPlaceHierarchyLine = null;
		BaseElementVectorMap gcNoteGedcomContentDescriptionStructureMap = null;
		GcNoteConcLine gcNoteConcLine = null;
		GcNoteContLine gcNoteContLine = null;

		if (headerRecord != null)
		{
			gcHeaderRecordMap = headerRecord.getVectorMap();
			if (gcHeaderRecordMap != null)
			{
				gcHeadSourceStructure = (GcHeadSourceStructure)gcHeaderRecordMap.get("SOUR", 0);
				gcDestinationLine = (GcDestinationLine)gcHeaderRecordMap.get("DEST", 0);
				gcDateStructure = (GcDateStructure)gcHeaderRecordMap.get("DATE", 0);
				gcSubmitterXref = (GcSubmitterXref)gcHeaderRecordMap.get("SUBM", 0);
				gcSubmissionXref = (GcSubmissionXref)gcHeaderRecordMap.get("SUBN", 0);
				gcFileLine = (GcFileLine)gcHeaderRecordMap.get("FILE", 0);
				gcCopyrightLine = (GcCopyrightLine)gcHeaderRecordMap.get("COPR", 0);
				gcGEDCStructure = (GcGEDCStructure)gcHeaderRecordMap.get("GEDC", 0);
				gcCharactersetStructure = (GcCharactersetStructure)gcHeaderRecordMap.get("CHAR", 0);
				gcLanguageLine = (GcLanguageLine)gcHeaderRecordMap.get("LANG", 0);
				gcPlaceStructure = (GcPlaceStructure)gcHeaderRecordMap.get("PLAC", 0);
				gcNoteGedcomContentDescriptionStructure = (GcNoteGedcomContentDescriptionStructure)gcHeaderRecordMap.get("NOTE", 0);
			}
			
			if (gcHeadSourceStructure != null)
			{
				gcHeadSourceStructureMap = gcHeadSourceStructure.getVectorMap();
				if (gcHeadSourceStructureMap != null)
				{
					gcVersionLine = (GcVersionLine)gcHeadSourceStructureMap.get("VERS", 0);
					gcSourceNameLine = (GcSourceNameLine)gcHeadSourceStructureMap.get("NAME", 0);
					gcCorporationStructure = (GcCorporationStructure)gcHeadSourceStructureMap.get("CORP", 0);
					gcDataStructure = (GcDataStructure)gcHeadSourceStructureMap.get("DATA", 0);
					gcSourceTitleLine = (GcSourceTitleLine)gcHeadSourceStructureMap.get("TITL", 0);
					gcRepositoryLink = (GcRepositoryLink)gcHeadSourceStructureMap.get("REPO", 0);
					gcSourceNoteStructure = (GcSourceNoteStructure)gcHeadSourceStructureMap.get("NOTE", 0);
				}
			}
			
			if (gcCorporationStructureMap != null)
			{
				gcCorporationStructureMap = gcCorporationStructure.getVectorMap();
				if (gcCorporationStructureMap != null)
				{
					gcAddressStructure = (GcAddressStructure)gcCorporationStructureMap.get("ADDR", 0);
					if (gcAddressStructure != null)
					{
						gcAddressStructureMap = gcAddressStructure.getVectorMap();
						if (gcAddressStructureMap != null)
						{
							gcAddressLineCont = (GcAddressLineCont)gcAddressStructureMap.get("CONT", 0);
							gcAddressLine1 = (GcAddressLine1)gcAddressStructureMap.get("ADR1", 0);
							gcAddressLine2 = (GcAddressLine2)gcAddressStructureMap.get("ADR2", 0);
							gcAddressCityLine = (GcAddressCityLine)gcAddressStructureMap.get("CITY", 0);
							gcAddressStateLine = (GcAddressStateLine)gcAddressStructureMap.get("STAE", 0);
							gcAddressPostalCodeLine = (GcAddressPostalCodeLine)gcAddressStructureMap.get("POST", 0);
							gcAddressCountryLine = (GcAddressCountryLine)gcAddressStructureMap.get("CTRY", 0);
						}
					}
					gcPhoneLine = (GcPhoneLine)gcCorporationStructureMap.get("PHON", 0);
				}
			}
			
			if (gcDataStructureMap != null)
			{
				gcDataStructureMap = gcDataStructure.getVectorMap();
				if (gcDataStructureMap != null)
				{
					gcDateLine = (GcDateLine)gcDataStructureMap.get("DATE", 0);
					gcCopyrightLine2 = (GcCopyrightLine)gcDataStructureMap.get("COPR", 0);
				}
			}
			
			if (gcDateStructureMap != null)
			{
				gcDateStructureMap = gcDateStructure.getVectorMap();
				if (gcDateStructureMap != null)
					gcTimeLine = (GcTimeLine)gcDateStructureMap.get("TIME", 0);
			}
			
			if (gcGEDCStructureMap != null)
			{
				gcGEDCStructureMap = gcGEDCStructure.getVectorMap();
				if (gcGEDCStructureMap != null)
				{
					gcVersionLine2 = (GcVersionLine)gcGEDCStructureMap.get("VERS", 0);
					gcFormLine = (GcFormLine)gcGEDCStructureMap.get("FORM", 0);
				}
			}
			
			if (gcCharactersetStructureMap != null)
			{
				gcCharactersetStructureMap = gcCharactersetStructure.getVectorMap();
				if (gcCharactersetStructureMap != null)
					gcVersionLine3 = (GcVersionLine)gcCharactersetStructureMap.get("VERS", 0);
			}
			
			if (gcPlaceStructureMap != null)
			{
				gcPlaceStructureMap = gcPlaceStructure.getVectorMap();
				if (gcPlaceStructureMap != null)
					gcFormPlaceHierarchyLine = (GcFormPlaceHierarchyLine)gcCharactersetStructureMap.get("FORM", 0);
			}
			
			if (gcNoteGedcomContentDescriptionStructureMap != null)
			{
				gcNoteGedcomContentDescriptionStructureMap = gcNoteGedcomContentDescriptionStructure.getVectorMap();
				if (gcNoteGedcomContentDescriptionStructureMap != null)
				{
					gcNoteConcLine = (GcNoteConcLine)gcNoteGedcomContentDescriptionStructureMap.get("CONC", 0);
					gcNoteContLine = (GcNoteContLine)gcNoteGedcomContentDescriptionStructureMap.get("CONT", 0);
				}
			}
		}
		
		ContactRec contactRecord = new ContactRec();
		contactRecord.setId("CB001");
		FileCreation fileCreation = new FileCreation();
//		fileCreation.setCopyright(value);
		if (gcTimeLine != null)
			fileCreation.setDate(gcTimeLine.getParameters());
		Product product = new Product();
		Name productName = new Name();
		product.setName(productName);
		if (gcSourceNameLine != null)
			product.setProductId(gcSourceNameLine.getParameters());
//		Supplier supplier = new Supplier();
//		Link supplierLink = new Link();
//		supplierLink.setRef("Supplier");
////		supplierLink.setTarget(value);
//		supplier.setLink(supplierLink);
//		product.setSupplier(supplier);
		if (gcVersionLine != null)
			product.setVersion(gcVersionLine.getParameters());
		fileCreation.setProduct(product);
//		fileCreation.setTime(value);
		this.setFileCreation(fileCreation);
//		Citation citation = new Citation();
//		citation.setCaption(value);
//		citation.setLink(value);
//		citation.setWhenRecorded(value);
//		citation.setWhereInSource(value);
//		this.headerRec.setCitation(citation);
//		Submitter submitter = new Submitter();
//		Link submitterLink = new Link();
//		if (gcSubmitterXref!= null && gcSubmitterXref.getLine() != null)
//			submitterLink.setTarget(gcSubmitterXref.getLine().getLink());
//		submitterLink.setRef("Submitter");
//		submitter.setLink(submitterLink);
//		this.headerRec.setSubmitter(submitter);
//		Note productNote = new Note();
//		productNote.setLang(value);
//		productNote.setValue(value);
//		this.headerRec.setNote(productNote);
	}
	
	public static boolean isEmpty(HeaderRec headerRec)
	{
		if (headerRec == null)
			return true;
		return GxFileCreation.isEmpty(headerRec.getFileCreation()) ||
			GxSubmitter.isEmpty(headerRec.getSubmitter());
	}
}
