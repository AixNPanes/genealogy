package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class SourceCitationSourData extends GedcomXMLRecord
{
	/**
+1 DATA        {0:1}
  +2 DATE <ENTRY_RECORDING_DATE>  {0:1}
  +2 TEXT <TEXT_FROM_SOURCE>  {0:M}
    +3 [ CONC | CONT ] <TEXT_FROM_SOURCE>  {0:M}
	 */
	public GedcomRecord date;
	public ArrayList<TextFromSource> textFromSource = new ArrayList<TextFromSource>();
	public SourceCitationSourData(GedcomRecord record)
	{
		super(record);
		for(GedcomRecord subRecord:record.records)
		{
			switch(subRecord.tag)
			{
				case "DATE": this.date = subRecord; break;
				case "TEXT": this.textFromSource.add(new TextFromSource(subRecord)); break;
				default: throw new SyntaxException(record, subRecord);
			}
		}
	}
}