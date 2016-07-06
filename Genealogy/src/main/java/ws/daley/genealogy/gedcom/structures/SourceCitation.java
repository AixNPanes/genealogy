package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;
import ws.daley.genealogy.gedcom.records.MultimediaLink;
import ws.daley.genealogy.gedcom.records.NoteStructure;

public class SourceCitation extends GedcomXMLRecord
	{
		/**
  n SOUR @<XREF:SOUR>@    {1:1} // systems using source records
    +1 PAGE <WHERE_WITHIN_SOURCE>  {0:1}
    +1 EVEN <EVENT_TYPE_CITED_FROM>  {0:1}
      +2 ROLE <ROLE_IN_EVENT>  {0:1}
    +1 DATA        {0:1}
      +2 DATE <ENTRY_RECORDING_DATE>  {0:1}
      +2 TEXT <TEXT_FROM_SOURCE>  {0:M}
        +3 [ CONC | CONT ] <TEXT_FROM_SOURCE>  {0:M}
    +1 QUAY <CERTAINTY_ASSESSMENT>  {0:1}
    +1 <<MULTIMEDIA_LINK>>  {0:M}
    +1 <<NOTE_STRUCTURE>>  {0:M}
  |              
  n SOUR <SOURCE_DESCRIPTION>  {1:1}   // Systems not using source records
    +1 [ CONC | CONT ] <SOURCE_DESCRIPTION>  {0:M}
    +1 TEXT <TEXT_FROM_SOURCE>  {0:M}
       +2 [CONC | CONT ] <TEXT_FROM_SOURCE>  {0:M}
    +1 <<NOTE_STRUCTURE>>  {0:M}
  |
   +1 SOUR <APPROVED_SYSTEM_ID>  {1:1}
      +2 VERS <VERSION_NUMBER>  {0:1}
      +2 NAME <NAME_OF_PRODUCT>  {0:1}
      +2 CORP <NAME_OF_BUSINESS>  {0:1}
        +3 <<ADDRESS_STRUCTURE>>  {0:1}
      +2 DATA <NAME_OF_SOURCE_DATA>  {0:1}
        +3 DATE <PUBLICATION_DATE>  {0:1}
        +3 COPR <COPYRIGHT_SOURCE_DATA>  {0:1}
		 */
		public GedcomRecord page;
		public EventTypeCitedFrom even;
		public GedcomRecord sourceData;
		public GedcomRecord quay;
		public ArrayList<MultimediaLink> multimediaLink = new ArrayList<MultimediaLink>();
		public ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
		public ArrayList<GedcomRecord> conc;
		public ArrayList<TextFromSource> textFromSource = new ArrayList<TextFromSource>();
		public GedcomRecord vers;
		public GedcomRecord name;
		public Corporation corp;
		public GedcomRecord _link;
		public GedcomRecord _foot;

		public SourceCitation(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "PAGE": this.page = subRecord; break;
					case "EVEN": this.even = new EventTypeCitedFrom(subRecord); break;
					case "DATA": this.sourceData = subRecord; break;
					case "QUAY": this.quay = subRecord; break;
					case "OBJE": this.multimediaLink.add(new MultimediaLink(subRecord)); break;
					case "NOTE": this.noteStructure.add(new NoteStructure(subRecord)); break;
					case "CONC": this.conc.add(subRecord); break;
					case "CONT": this.conc.add(subRecord); break;
					case "TEXT": this.textFromSource.add(new TextFromSource(subRecord)); break;
					case "VERS": this.vers = subRecord; break;
					case "NAME": this.name = subRecord; break;
					case "CORP": this.corp = new Corporation(subRecord); break;
					case "_LINK": this._link = subRecord; break;
					case "_FOOT": this._foot = subRecord; break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}