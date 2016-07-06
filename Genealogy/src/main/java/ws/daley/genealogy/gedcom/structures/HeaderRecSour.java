package ws.daley.genealogy.gedcom.structures;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class HeaderRecSour extends GedcomXMLRecord
{
	/**
+1 SOUR <APPROVED_SYSTEM_ID>  {1:1}
  +2 VERS <VERSION_NUMBER>  {0:1}
  +2 NAME <NAME_OF_PRODUCT>  {0:1}
  +2 CORP <NAME_OF_BUSINESS>  {0:1}
    +3 <<ADDRESS_STRUCTURE>>  {0:1}
  +2 DATA <NAME_OF_SOURCE_DATA>  {0:1}
    +3 DATE <PUBLICATION_DATE>  {0:1}
    +3 COPR <COPYRIGHT_SOURCE_DATA>  {0:1}
	 */
	public GedcomRecord vers = null;
	public GedcomRecord name = null;
	public HeaderRecSourCorp corp = null;
	public GedcomRecord sourData = null;
	public HeaderRecSour(GedcomRecord record)
	{
		super(record);
		for(GedcomRecord subRecord:record.records)
			switch(subRecord.tag)
			{
				case "VERS": this.vers = subRecord; break;
				case "NAME": this.name = subRecord; break;
				case "CORP": this.corp = new HeaderRecSourCorp(subRecord); break;
				case "DATA": this.sourData = subRecord; break;
				default: throw new SyntaxException(record, subRecord);
			}
	}
	public String getVersionId()
	{
		Pattern pv = Pattern.compile("Family Tree Maker \\((.*)\\)");
		Matcher mv = pv.matcher(this.vers.line.group("linevalue"));
		if (mv.matches())
			return mv.group(1);
		return "";
	}
}