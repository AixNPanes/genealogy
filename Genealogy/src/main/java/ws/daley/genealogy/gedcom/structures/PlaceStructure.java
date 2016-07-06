package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;
import ws.daley.genealogy.gedcom.records.NoteStructure;

public class PlaceStructure extends GedcomXMLRecord
	{
		/**
n PLAC <PLACE_VALUE>  {1:1}
    +1 FORM <PLACE_HIERARCHY>  {0:1}
    +1 <<SOURCE_CITATION>>  {0:M}
    +1 <<NOTE_STRUCTURE>>  {0:M}
   		 */
		public GedcomRecord form;
		public ArrayList<SourceCitation> sourceCitation = new ArrayList<SourceCitation>();
		public ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
		public PlaceStructure(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "FORM": this.form = subRecord; break;
					case "SOUR": this.sourceCitation.add(new SourceCitation(subRecord)); break;
					case "NOTE": this.noteStructure.add(new NoteStructure(subRecord)); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}