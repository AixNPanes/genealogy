package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class AddressStructure extends GedcomXMLRecord
	{
		/**
 n  ADDR <ADDRESS_LINE>  {0:1}
    +1 CONT <ADDRESS_LINE>  {0:M}
    +1 ADR1 <ADDRESS_LINE1>  {0:1}
    +1 ADR2 <ADDRESS_LINE2>  {0:1}
    +1 CITY <ADDRESS_CITY>  {0:1}
    +1 STAE <ADDRESS_STATE>  {0:1}
    +1 POST <ADDRESS_POSTAL_CODE>  {0:1}
    +1 CTRY <ADDRESS_COUNTRY>  {0:1}
  n  PHON <PHONE_NUMBER>  {0:3}		 */
		public ArrayList<GedcomRecord> cont = new ArrayList<GedcomRecord>();
		public String adr1 = null;
		public String adr2 = null;
		public String city = null;
		public String state = null;
		public String post = null;
		public String country = null;
		public AddressStructure(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
				switch(subRecord.tag)
				{
					case "CONT": this.cont.add(subRecord); break;
					default: throw new SyntaxException(record, subRecord);
				}
			if (this.cont.size() != 1)
				throw new RuntimeException();
			Pattern pl = Pattern.compile("(?<city>\\w+),\\s+(?<state>\\w+)\\s+(?<post>[-0-9]+)");
			Matcher ml = pl.matcher(this.cont.get(0).lineValue.trim());
			if (ml.matches())
			{
				this.city = ml.group("city");
				this.state = ml.group("state");
				this.post = ml.group("post");
			}
		}
	}