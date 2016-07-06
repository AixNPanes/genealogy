package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.MultimediaLink;
import ws.daley.genealogy.gedcom.records.NoteStructure;

public class IndividualEventStructureEVEN3 extends IndividualEventStructureEVEN2
	{
		/**
  n[ BIRT | CHR ] [Y|<NULL>]  {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
    +1 FAMC @<XREF:FAM>@  {0:1}
    |
  n  ADOP [Y|<NULL>]  {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
    +1 FAMC @<XREF:FAM>@  {0:1}
      +2 ADOP <ADOPTED_BY_WHICH_PARENT>  {0:1}
		 */
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
		@SuppressWarnings("hiding")
		public GedcomRecord type;
		@SuppressWarnings("hiding")
		public GedcomRecord date;
		@SuppressWarnings("hiding")
		public PlaceStructure plac;
		@SuppressWarnings("hiding")
		public AddressStructure addr;
		@SuppressWarnings("hiding")
		public GedcomRecord phone;
		@SuppressWarnings("hiding")
		public GedcomRecord age;
		@SuppressWarnings("hiding")
		public GedcomRecord agnc;
		@SuppressWarnings("hiding")
		public ArrayList<SourceCitation> sourceCitation = new ArrayList<SourceCitation>();
		@SuppressWarnings("hiding")
		public ArrayList<MultimediaLink> multimediaLink = new ArrayList<MultimediaLink>();
		@SuppressWarnings("hiding")
		public ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
		public GedcomRecord famc;
		public IndividualEventStructureEVEN3(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
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
					case "FAMC": this.famc = subRecord; break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}