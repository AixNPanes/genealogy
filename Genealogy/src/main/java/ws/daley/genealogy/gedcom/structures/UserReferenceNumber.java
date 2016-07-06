package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class UserReferenceNumber extends GedcomXMLRecord
{
	/**
+1 REFN <USER_REFERENCE_NUMBER>  {0:M}
  +2 TYPE <USER_REFERENCE_TYPE>  {0:1}
	 */
	public GedcomRecord type;
	public ArrayList<GedcomRecord> sour = new ArrayList<GedcomRecord>(); // undocumented
	public UserReferenceNumber(GedcomRecord record)
	{
		super(record);
		for(GedcomRecord subRecord:record.records)
		{
			switch(subRecord.tag)
			{
				case "TYPE": this.type = subRecord; break;
				case "SOUR": this.sour.add(subRecord); break;
				default: throw new SyntaxException(record, subRecord);
			}
		}
	}
}