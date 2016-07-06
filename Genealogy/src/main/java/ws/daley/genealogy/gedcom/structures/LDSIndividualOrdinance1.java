package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;
import ws.daley.genealogy.gedcom.records.NoteStructure;

public class LDSIndividualOrdinance1 extends GedcomXMLRecord
	{
		/**
  n  [ BAPL | CONL | ENDL ]  {1:1}
    +1 STAT <LDS_BAPTISM_DATE_STATUS>  {0:1}
    +1 DATE <DATE_LDS_ORD>  {0:1}
    +1 TEMP <TEMPLE_CODE>  {0:1}
    +1 PLAC <PLACE_LIVING_ORDINANCE>  {0:1}
    +1 <<SOURCE_CITATION>>  {0:M}
    +1 <<NOTE_STRUCTURE>>  {0:M}
   		 */
		public GedcomRecord stat;
		public GedcomRecord date;
		public GedcomRecord temp;
		public GedcomRecord plac;
		public ArrayList<SourceCitation> sour = new ArrayList<SourceCitation>();
		public ArrayList<NoteStructure> note = new ArrayList<NoteStructure>();
		public LDSIndividualOrdinance1(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "STAT": this.stat = subRecord; break;
					case "DATE": this.date = subRecord; break;
					case "TEMP": this.temp = subRecord; break;
					case "PLAC": this.plac = subRecord; break;
					case "SOUR": this.sour.add(new SourceCitation(subRecord)); break;
					case "NOTE": this.note.add(new NoteStructure(subRecord)); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}