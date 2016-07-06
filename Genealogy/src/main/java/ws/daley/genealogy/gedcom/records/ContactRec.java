package ws.daley.genealogy.gedcom.records;

import ws.daley.genealogy.gedcom.GEDCOM2XML;
import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.structures.AddressStructure;

public class ContactRec extends GedcomXMLRecord
{
	/**
	 +2 CORP <NAME_OF_BUSINESS>  {0:1}
    +3 <<ADDRESS_STRUCTURE>>  {0:1}
	 */
	public String id;
	public String name;
	public AddressStructure addr;
	public GedcomRecord phone;
	public ContactRec(GedcomRecord record)
	{
		super(record);
		GEDCOM2XML.contactIdNo++;
		this.id = String.format("CN%03d", GEDCOM2XML.contactIdNo);
		this.name = record.lineValue;
		for(GedcomRecord subRecord:record.records)
			switch(subRecord.tag)
			{
				case "ADDR": this.addr = new AddressStructure(subRecord); break;
				case "PHON": this.phone = subRecord; break;
				default: throw new SyntaxException(record, subRecord);
			}
	}
}