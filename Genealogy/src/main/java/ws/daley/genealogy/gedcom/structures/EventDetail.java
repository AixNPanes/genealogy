package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;
import ws.daley.genealogy.gedcom.records.MultimediaLink;
import ws.daley.genealogy.gedcom.records.NoteStructure;

public class EventDetail extends GedcomXMLRecord
	{
		/**
EVENT_DETAIL: = 
  n  TYPE <EVENT_DESCRIPTOR>  {0:1}
  n  DATE <DATE_VALUE>  {0:1}
  n  <<PLACE_STRUCTURE>>  {0:1}
  n  <<ADDRESS_STRUCTURE>>  {0:1}
  n  AGE <AGE_AT_EVENT>  {0:1}
  n  AGNC <RESPONSIBLE_AGENCY>  {0:1}
  n  CAUS <CAUSE_OF_EVENT>  {0:1}
  n  <<SOURCE_CITATION>>  {0:M}
    +1 <<NOTE_STRUCTURE>>  {0:M}
    +1 <<MULTIMEDIA_LINK>>  {0:M}
  n  <<MULTIMEDIA_LINK>>  {0:M}
  n  <<NOTE_STRUCTURE>>  {0:M}
		*/
		public ArrayList<GedcomRecord> conc = new ArrayList<GedcomRecord>();
		public GedcomRecord type;
		public GedcomRecord date;
		public PlaceStructure plac;
		public AddressStructure addr;
		public GedcomRecord phone;
		public GedcomRecord age;
		public GedcomRecord agnc;
		public ArrayList<SourceCitation> sourceCitation = new ArrayList<SourceCitation>();
		public ArrayList<MultimediaLink> multimediaLink = new ArrayList<MultimediaLink>();
		public ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
		public EventDetail(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "CONC": this.conc.add(subRecord); break;
					case "TYPE": this.type = subRecord; break;
					case "DATE": this.date = subRecord; break;
					case "PLAC": this.plac = new PlaceStructure(subRecord); break;
					case "ADDR": this.date = subRecord; break;
					case "PHON": this.phone = subRecord; break;
					case "AGE": this.date = subRecord; break;
					case "AGNC": this.date = subRecord; break;
					case "SOUR": this.sourceCitation.add(new SourceCitation(subRecord)); break;
					case "OBJE": this.multimediaLink.add(new MultimediaLink(subRecord)); break;
					case "NOTE": this.noteStructure.add(new NoteStructure(subRecord)); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}