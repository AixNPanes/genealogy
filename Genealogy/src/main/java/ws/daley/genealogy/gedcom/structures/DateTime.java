package ws.daley.genealogy.gedcom.structures;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class DateTime extends GedcomXMLRecord
{
	/**
+1 DATE <CHANGE_DATE>  {1:1}
  +2 TIME <TIME_VALUE>  {0:1}
	 */
	public GedcomRecord time = null;
	public DateTime(GedcomRecord record)
	{
		super(record);
		for(GedcomRecord subRecord:record.records)
			switch(subRecord.tag)
			{
				case "Time": this.time = subRecord; break;
				default: throw new SyntaxException(record, subRecord);
			}
	}
}