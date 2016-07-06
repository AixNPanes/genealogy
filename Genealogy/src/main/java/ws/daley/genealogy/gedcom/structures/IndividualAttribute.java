package ws.daley.genealogy.gedcom.structures;

import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class IndividualAttribute extends GedcomXMLRecord
{
	public EventDetail event;
	public IndividualAttribute(GedcomRecord record)
	{
		super(record);
		throw new RuntimeException();
	}
}