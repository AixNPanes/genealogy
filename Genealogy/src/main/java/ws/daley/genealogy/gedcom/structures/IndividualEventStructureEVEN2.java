package ws.daley.genealogy.gedcom.structures;

import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;

public class IndividualEventStructureEVEN2 extends EventDetail
{
	public String yNull;
	public IndividualEventStructureEVEN2(GedcomRecord record)
	{
		super(record);
		this.yNull = record.lineValue;
	}
}