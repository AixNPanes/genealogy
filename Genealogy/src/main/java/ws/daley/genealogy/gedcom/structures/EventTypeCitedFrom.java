package ws.daley.genealogy.gedcom.structures;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class EventTypeCitedFrom extends GedcomXMLRecord
{
	/**
+1 EVEN <EVENT_TYPE_CITED_FROM>  {0:1}
  +2 ROLE <ROLE_IN_EVENT>  {0:1}
	 */
	public GedcomRecord role;
	public EventTypeCitedFrom(GedcomRecord record)
	{
		super(record);
		for(GedcomRecord subRecord:record.records)
		{
			switch(subRecord.tag)
			{
				case "ROLE": this.role = subRecord; break;
				default: throw new SyntaxException(record, subRecord);
			}
		}
	}
}