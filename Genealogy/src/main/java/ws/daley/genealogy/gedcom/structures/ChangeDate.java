package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;
import ws.daley.genealogy.gedcom.records.NoteStructure;

public class ChangeDate extends GedcomXMLRecord
	{
		/**
 n  CHAN          {1:1}
    +1 DATE <CHANGE_DATE>  {1:1}
      +2 TIME <TIME_VALUE>  {0:1}
    +1 <<NOTE_STRUCTURE>>  {0:M}
   		 */
		public DateTime date;
		public ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
		public ChangeDate(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "DATE": this.date = new DateTime(subRecord); break;
					case "NOTE": this.noteStructure.add(new NoteStructure(subRecord)); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}