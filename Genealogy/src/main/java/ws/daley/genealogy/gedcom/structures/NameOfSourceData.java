package ws.daley.genealogy.gedcom.structures;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class NameOfSourceData extends GedcomXMLRecord
{
	public GedcomRecord date;
	public GedcomRecord copr;
	public NameOfSourceData(GedcomRecord record)
	{
		super(record);
		for(GedcomRecord subRecord:record.records)
		{
			switch(subRecord.tag)
			{
				case "DATE": this.date = subRecord; break;
				case "COPR": this.copr = subRecord; break;
				default: throw new SyntaxException(record, subRecord);
			}
		}
	}
}