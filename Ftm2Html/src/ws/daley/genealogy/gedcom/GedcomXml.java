package ws.daley.genealogy.gedcom;

import java.io.FileWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import ws.daley.gedcom.AddrLine;
import ws.daley.gedcom.Changed;
import ws.daley.gedcom.ContactRec;
import ws.daley.gedcom.FileCreation;
import ws.daley.gedcom.GEDCOM;
import ws.daley.gedcom.HeaderRec;
import ws.daley.gedcom.IndividualRec;
import ws.daley.gedcom.MailAddress;
import ws.daley.gedcom.Name;
import ws.daley.gedcom.Note;
import ws.daley.gedcom.PlacePart;
import ws.daley.gedcom.Product;
import ws.daley.gedcom.xml.GxHeaderRec;
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
import ws.daley.genealogy.gedcom.line.GcNameLine;
import ws.daley.genealogy.gedcom.line.GcNoteConcLine;
import ws.daley.genealogy.gedcom.line.GcNoteContLine;
import ws.daley.genealogy.gedcom.line.GcPhoneLine;
import ws.daley.genealogy.gedcom.line.GcSexLine;
import ws.daley.genealogy.gedcom.line.GcSourceNameLine;
import ws.daley.genealogy.gedcom.line.GcSourceTitleLine;
import ws.daley.genealogy.gedcom.line.GcTimeLine;
import ws.daley.genealogy.gedcom.line.GcVersionLine;
import ws.daley.genealogy.gedcom.link.GcRepositoryLink;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.GcTags;
import ws.daley.genealogy.gedcom.structure.GcAddressStructure;
import ws.daley.genealogy.gedcom.structure.GcChangeDateDateStructure;
import ws.daley.genealogy.gedcom.structure.GcChangeDateStructure;
import ws.daley.genealogy.gedcom.structure.GcCharactersetStructure;
import ws.daley.genealogy.gedcom.structure.GcCorporationStructure;
import ws.daley.genealogy.gedcom.structure.GcDataStructure;
import ws.daley.genealogy.gedcom.structure.GcDateStructure;
import ws.daley.genealogy.gedcom.structure.GcGEDCStructure;
import ws.daley.genealogy.gedcom.structure.GcHeadSourceStructure;
import ws.daley.genealogy.gedcom.structure.GcLineageLinkedGedcomStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteGedcomContentDescriptionStructure;
import ws.daley.genealogy.gedcom.structure.GcNoteStructure;
import ws.daley.genealogy.gedcom.structure.GcPlaceStructure;
import ws.daley.genealogy.gedcom.structure.GcSourceNoteStructure;
import ws.daley.genealogy.gedcom.structure.record.GcHeaderRecord;
import ws.daley.genealogy.gedcom.structure.record.GcIndividualRecord;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVector;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVectorMap;
import ws.daley.genealogy.gedcom.xref.GcSubmissionXref;
import ws.daley.genealogy.gedcom.xref.GcSubmitterXref;

public class GedcomXml extends GEDCOM
{
	@SuppressWarnings("unused")
    private GcLineageLinkedGedcomStructure gedcom;
	
	public GedcomXml(GcLineageLinkedGedcomStructure gedcom)
	{
		this.gedcom = gedcom;
		BaseElementVectorMap vectorMap = gedcom.getVectorMap();
		
		buildSubmitterRecords(vectorMap);
		buildHeaderRecord(vectorMap);
		buildIndividualRecords(vectorMap.get("INDI"));
//		for(GcBaseElement element:vectorMap.get("FAM")) this.familyRecords.add((GcFamilyRecord)element);
//		for(GcBaseElement element:vectorMap.get("OBJE")) this.multimediaLinkRecords.add((GcMultimediaLinkRecord)element);
//		for(GcBaseElement element:vectorMap.get("NOTE")) this.noteRecords.add((GcNoteRecordRecord)element);
//		for(GcBaseElement element:vectorMap.get("REPO")) this.repositoryRecords.add((GcRepositoryRecord)element);
//		for(GcBaseElement element:vectorMap.get("SOUR")) this.sourceRecords.add((GcSourceRecord)element);
//		for(GcBaseElement element:vectorMap.get("SUBN")) this.submissionRecords.add((GcSubmissionRecord)element);
//		this.trailerRecord = (GcTrailerLine)vectorMap.get("TRLR").get(0);
	}
	
	private void buildSubmitterRecords(BaseElementVectorMap map)
	{
//		new TagDescriptor("NAME", 0, 1, GcNameLine.class),
//		new TagDescriptor("ADDR", 0, 1, GcAddressStructure.class),
//		new TagDescriptor("PHON", 0, 1, GcPhoneLine.class),
//		new TagDescriptor("OBJE", 0, 1, GcMultimediaLinkRecord.class),
//		new TagDescriptor("LANG", 0, 1, GcLanguageLine.class),
//		new TagDescriptor("RFN", 0, 1, GcRegisteredFileNumberLine.class),
//		new TagDescriptor("RIN", 0, 1, GcAutomatedRecordIdLine.class),
//		new TagDescriptor("CHAN", 0, 1, GcAutomatedRecordIdLine.class)
		
//   &lt;sequence>
//     &lt;element ref="{}Name"/>
//     &lt;element ref="{}MailAddress" maxOccurs="unbounded" minOccurs="0"/>
//     &lt;element ref="{}Phone" maxOccurs="unbounded" minOccurs="0"/>
//     &lt;element ref="{}Email" maxOccurs="unbounded" minOccurs="0"/>
//     &lt;element ref="{}URI" maxOccurs="unbounded" minOccurs="0"/>
//     &lt;element ref="{}Public" minOccurs="0"/>
//     &lt;element ref="{}Note" maxOccurs="unbounded" minOccurs="0"/>
//     &lt;element ref="{}Changed" maxOccurs="unbounded" minOccurs="0"/>
//     &lt;element ref="{}SameIndiv" maxOccurs="unbounded" minOccurs="0"/>
//   &lt;/sequence>
//   &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
//   &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}string" />
		

		BaseElementVector submitterRecords = map.get("SUBM");
		for(GcBaseElement submitterElement:submitterRecords)
		{
			ContactRec contactRecord = new ContactRec();
			for(GcBaseElement element:submitterElement.getElements())
			{
				GcTags.TAG tag = element.getTag();
				switch(tag) {
					case TAG_NAME:
						Name nam = new Name();
						nam.setValue(((GcNameLine)element).getParameters());
						contactRecord.setName(nam);
						break;
					case TAG_ADDR:
						MailAddress address = new MailAddress();
						for(GcBaseElement addressElement:element.getElements())
						{
//							new TagDescriptor("CONT", 0, Integer.MAX_VALUE, GcAddressLineCont.class),
//							new TagDescriptor("ADR1", 0, 1, GcAddressLine1.class),
//							new TagDescriptor("ADR2", 0, 1, GcAddressLine2.class),
//							new TagDescriptor("CITY", 0, 1, GcAddressCityLine.class),
//							new TagDescriptor("STAE", 0, 1, GcAddressStateLine.class),
//							new TagDescriptor("POST", 0, 1, GcAddressPostalCodeLine.class),
//							new TagDescriptor("CTRY", 0, 1, GcAddressCountryLine.class)
							GcTags.TAG addressTag = addressElement.getTag();
							PlacePart placePart = new PlacePart();
							AddrLine addrLine = new AddrLine();
							switch(addressTag) {
								case TAG_CONT:
									GcAddressLineCont addressLineCont = (GcAddressLineCont)addressElement;
									placePart.setValue(addressLineCont.getLineText());
									addrLine.getContent().add(placePart);
									address.getAddrLine().add(addrLine);
									break;
								case TAG_ADR1:
									GcAddressLine1 addressLine1 = (GcAddressLine1)addressElement;
									placePart.setValue(addressLine1.getLineText());
									addrLine.getContent().add(placePart);
									address.getAddrLine().add(addrLine);
									break;
								case TAG_ADR2:
									GcAddressLine2 addressLine2 = (GcAddressLine2)addressElement;
									placePart.setValue(addressLine2.getLineText());
									addrLine.getContent().add(placePart);
									address.getAddrLine().add(addrLine);
									break;
								case TAG_CITY:
									GcAddressCityLine addressCityLine = (GcAddressCityLine)addressElement;
									placePart.setValue(addressCityLine.getLineText());
									addrLine.getContent().add(placePart);
									address.getAddrLine().add(addrLine);
									break;
								case TAG_STAE:
									GcAddressStateLine addressStateLine = (GcAddressStateLine)addressElement;
									placePart.setValue(addressStateLine.getLineText());
									addrLine.getContent().add(placePart);
									address.getAddrLine().add(addrLine);
									break;
								case TAG_POST:
									GcAddressPostalCodeLine addressPostalCodeLine = (GcAddressPostalCodeLine)addressElement;
									placePart.setValue(addressPostalCodeLine.getLineText());
									addrLine.getContent().add(placePart);
									address.getAddrLine().add(addrLine);
									break;
								case TAG_CTRY:
									GcAddressCountryLine addressCountryLine = (GcAddressCountryLine)addressElement;
									placePart.setValue(addressCountryLine.getLineText());
									addrLine.getContent().add(placePart);
									address.getAddrLine().add(addrLine);
									break;
							}
							contactRecord.getMailAddress().add(address);
						}
						break;
					case TAG_PHON:
						break;
					case TAG_OBJE:
						break;
					case TAG_LANG:
						break;
					case TAG_RFN:
						break;
					case TAG_RIN:
						break;
					case TAG_CHAN:
						break;
				}
			}
		}
	}
	
	private void buildHeaderRecord(BaseElementVectorMap map)
	{
		GxHeaderRec gxHeaderRec = new GxHeaderRec((GcHeaderRecord)map.get("HEAD", 0));
		if (!gxHeaderRec.isEmpty())
			this.headerRec = gxHeaderRec;
	}
	
	private Date getChangeDate(GcChangeDateStructure changeDate)
	{
		if (changeDate == null)
			return null;
		BaseElementVectorMap changeMap = changeDate.getVectorMap();
		GcChangeDateDateStructure dateStructure = (GcChangeDateDateStructure)changeMap.get("DATE").get(0);
		String dateString = dateStructure.getParameters() + " ";
		GcTimeLine timeLine = (GcTimeLine)dateStructure.getVectorMap().get("TIME").get(0);
		if (timeLine != null)
			dateString += timeLine.getParameters();
		else
			dateString += "00:00:00";
		DateFormat df = DateFormat.getDateInstance();
		try{return df.parse(dateString);}
		catch(Throwable t) {return null;}
	}
	
	private IndividualRec individualRec(GcIndividualRecord gcIndividualRecord)
	{
		String key = gcIndividualRecord.getLine().getXref();
		BaseElementVectorMap vectorMap = gcIndividualRecord.getVectorMap();
		IndividualRec individualRecord = new IndividualRec();
		individualRecord.setId(key);

		BaseElementVector chan = vectorMap.get("CHAN");
		Date date = chan.size()==0?null:getChangeDate((GcChangeDateStructure)vectorMap.get("CHAN").get(0));
		if (date != null)
		{
			Changed changd = new Changed();
			changd.setDate(date.toString());
			individualRecord.getChanged().add(changd);
		}
		
//		for(GcBaseElement element:vectorMap.get("SOUR"))
//		{
//			individualRec.getEvidence().add(new XmlSourceRec((GcSourceCitationStructure)element));
//		}
		for(GcBaseElement element:vectorMap.get("NOTE"))
		{
			Note not = new Note();
			not.setValue(((GcNoteStructure)element).getText());
			individualRecord.getNote().add(not);
		}
//		for(GcBaseElement element:vectorMap.get("DEAT"))
//			individualRec.setDeathStatus(((GcIndividualDeathEvent)element));
		individualRecord.setGender(((GcSexLine)vectorMap.get("SEX").get(0)).getParameters());
			
//		individualRec.addGivenName(value);
//		individualRec.addMaidenName(value);
//		individualRec.addSurName(value);
//		individualRec.setId(value);
//		individualRec.setOccupation(value);
//		individualRec.setSubmitter(value);
//		individualRec.getAssocIndiv().add(arg0);
//		individualRec.getDupIndiv().add(arg0);
//		individualRec.getEnrichment().add(arg0);
//		individualRec.getEvidence().add(arg0);
//		individualRec.getExternalID().add(arg0);
//		individualRec.getIndivName().add(arg0);
//		individualRec.getPersInfo().add(arg0);
		
		return individualRecord;
	}
	
	private void buildIndividualRecords(BaseElementVector individualRecordVector)
	{
		this.individualRec = new ArrayList<IndividualRec>();
		for(GcBaseElement element:individualRecordVector) this.individualRec.add(individualRec((GcIndividualRecord)element));
	}
	
	public void print(String filename) throws Exception
	{
		ClassLoader classLoader = this.getClass().getClassLoader();
		JAXBContext jc = JAXBContext.newInstance("ws.daley.gedcom", this.getClass().getClassLoader(), Collections.<String,Object>emptyMap());
		// create a Marshaller and marshal to filename
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileWriter fw = new FileWriter(filename);
		m.marshal(this, fw);
		fw.close();	
	}

	/*
	 * <?xml version="1.0"?>
	 * <!DOCTYPE GEDCOM SYSTEM "http://gedcom.org/dtd/gedxml60.dtd">
	 * <GEDCOM xmlns:html="http//:www.w3c.org/TR/REC-html40/">
	 * <!-- Header Record -->
	 * <HeaderRec>
	 * 	<FileCreation Date="2 Oct 2000" Time="15:20:2.3">
	 * 		<Product>
	 * 			<ProductId>DAS</ProductId>
	 * 			<Version>6.3</Version>
	 * 			<Name>
	 * 				Deluxe Ancestral System
	 * 			</Name>
	 * 			<Supplier>
	 * 				<Link Target="ContactRec" Ref="CN001"/>
	 * 			</Supplier>
	 * 		</Product>
	 * 		<Copyright>Copyright 1999 So . . .</Copyright>
	 * 	</FileCreation>
	 * 	<Citation>
	 * 		<!-- This Citation is useful if the data in a file is from a single source -->
	 *		 . . .
	 * 	</Citation>
	 * 	<Submitter>...
	 * 		<Link Target="ContactRec" Ref="CN002"/>
	 * 	</Submitter>
	 * 	<Note>
	 * 		This material was prepared for the
	 * 		<IndivDoc><NameDoc><GivenNameDoc>John</GivenNameDoc>
	 * 		<SurNameDoc>Tate</SurNameDoc></NameDoc></IndivDoc> and <IndivDoc><NameDoc>
	 * 		<GivenNameDoc>Mary</GivenNameDoc> <SurNameDoc>Hamblin</SurNameDoc>
	 * 		</NameDoc></IndivDoc> Family Organization.
	 * 	</Note>
	 * </HeaderRec>
	 */
}
