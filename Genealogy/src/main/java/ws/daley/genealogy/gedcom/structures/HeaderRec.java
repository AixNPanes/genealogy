package ws.daley.genealogy.gedcom.structures;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class HeaderRec extends GedcomXMLRecord
{
	/**
	 * 
+1 SOUR <APPROVED_SYSTEM_ID>  {1:1}
  +2 VERS <VERSION_NUMBER>  {0:1}
  +2 NAME <NAME_OF_PRODUCT>  {0:1}
  +2 CORP <NAME_OF_BUSINESS>  {0:1}
    +3 <<ADDRESS_STRUCTURE>>  {0:1}
  +2 DATA <NAME_OF_SOURCE_DATA>  {0:1}
    +3 DATE <PUBLICATION_DATE>  {0:1}
    +3 COPR <COPYRIGHT_SOURCE_DATA>  {0:1}
+1 DEST <RECEIVING_SYSTEM_NAME>  {0:1*}
+1 DATE <TRANSMISSION_DATE>  {0:1}
  +2 TIME <TIME_VALUE>  {0:1}
+1 SUBM @<XREF:SUBM>@  {1:1}
+1 SUBN @<XREF:SUBN>@  {0:1}
+1 FILE <FILE_NAME>  {0:1}
+1 COPR <COPYRIGHT_GEDCOM_FILE>  {0:1}
+1 GEDC        {1:1}
  +2 VERS <VERSION_NUMBER>  {1:1}
  +2 FORM <GEDCOM_FORM>  {1:1}
+1 CHAR <CHARACTER_SET>  {1:1}
  +2 VERS <VERSION_NUMBER>  {0:1}
+1 LANG <LANGUAGE_OF_TEXT>  {0:1}
+1 PLAC        {0:1}
  +2 FORM <PLACE_HIERARCHY>  {1:1}
+1 NOTE <GEDCOM_CONTENT_DESCRIPTION>  {0:1}
  +2 [CONT|CONC] <GEDCOM_CONTENT_DESCRIPTION>  {0:M}
	 */
	public HeaderRecSour sour = null;
	public GedcomRecord dest = null;
	public GedcomRecord date = null;
	public GedcomRecord subm = null;
	public GedcomRecord subn = null;
	public GedcomRecord file = null;
	public GedcomRecord copr = null;
	public GedcomRecord gedc = null;
	public GedcomRecord char_ = null;
	public GedcomRecord lang = null;
	public GedcomRecord plac = null;
	public GedcomRecord note = null;
	public HeaderRec(GedcomRecord record)
	{
		super(record);
		for(GedcomRecord subRecord:record.records)
		{
			switch(subRecord.tag)
			{
				case "SOUR": this.sour = new HeaderRecSour(subRecord); break;
				case "DEST": this.dest = subRecord; break;
				case "DATE": this.date = subRecord; break;
				case "SUBM": this.subm = subRecord; break;
				case "SUBN": this.subn = subRecord; break;
				case "FILE": this.file = subRecord; break;
				case "COPR": this.copr = subRecord; break;
				case "GEDC": this.gedc = subRecord; break;
				case "CHAR": this.char_ = subRecord; break;
				case "LANG": this.lang = subRecord; break;
				case "PLAC": this.plac = subRecord; break;
				case "NOTE": this.note = subRecord; break;
				default: throw new SyntaxException(record, subRecord);
			}
		}
	}
	@Override
	public String toString()
	{
		Date currentDate = new GregorianCalendar().getTime();
		if (this.sour == null)
			throw new RuntimeException();
		return " <HeaderRec>\n" +
				"  <FileCreation Date=\"" + (new SimpleDateFormat("dd MMM yyyy").format(currentDate)) + " Time=\"" + (new SimpleDateFormat("HH:mm:ss.SS").format(currentDate)) + "\">\n" +
				"   <Product>\n" +
				"    <ProductId>FTM</ProductId>\n" +
				"    <Version>" + this.sour.getVersionId() + "</Version>\n" +
				"    <Name>" + this.sour.name.lineValue.trim() + "</Name>\n" +
				"    <MailAddress>\n" +
				"     <AddrLine><Addresse>" + this.sour.corp.data + "</Addresse></AddrLine>\n" +
				"     <AddrLine>" + this.sour.corp.addr.data + "</AddrLine>\n" +
				"     <AddrLine>\n" + 
				"      <NamePart Level=\"4\" Type=\"town\">" + this.sour.corp.addr.city + "</NamePart>\n" +
				"      <NamePart Level=\"2\" Type=\"state\">" + this.sour.corp.addr.state + "</NamePart>\n" +
				"     </AddrLine>\n" +
				"     <AddrLine>\n" + 
				"      <NamePart Level=\"5\" Type=\"postal code\">" + this.sour.corp.addr.post + "</NamePart>\n" +
				"     </AddrLine>\n" +
				"    </MailAddress>\n" +
				"    <Phone>" + this.sour.corp.phon.lineValue + "</phone>\n" +
				"   </Product>\n" +
				"  </FileCreation>\n" +
				" </HeaderRec>";
	}
}