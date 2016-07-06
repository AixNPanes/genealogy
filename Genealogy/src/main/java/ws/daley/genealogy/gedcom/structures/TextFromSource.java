package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class TextFromSource extends GedcomXMLRecord
{
	/**
      +2 TEXT <TEXT_FROM_SOURCE>  {0:M}
        +3 [ CONC | CONT ] <TEXT_FROM_SOURCE>  {0:M}
		 */
	public ArrayList<GedcomRecord> conc = new ArrayList<GedcomRecord>();
	public TextFromSource(GedcomRecord record)
	{
		super(record);
		for(GedcomRecord subRecord:record.records)
		{
			switch(subRecord.tag)
			{
				case "CONT": this.conc.add(subRecord); break;
				case "CONC": this.conc.add(subRecord); break;
				default: throw new SyntaxException(record, subRecord);
			}
		}
	}
}