package ws.daley.genealogy.gedcom.structures;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class Corporation extends GedcomXMLRecord
{
	/**
 +2 CORP <NAME_OF_BUSINESS>  {0:1}
    +3 <<ADDRESS_STRUCTURE>>  {0:1}
	 */
	public AddressStructure addr;
	public GedcomRecord phone;
	public Corporation(GedcomRecord record)
	{
		super(record);
		for(GedcomRecord subRecord:record.records)
		{
			switch(subRecord.tag)
			{
				case "ADDR": this.addr = new AddressStructure(subRecord);break;
				case "PHON": this.phone = subRecord; break;
				default: throw new SyntaxException(record, subRecord);
			}
		}
	}
}