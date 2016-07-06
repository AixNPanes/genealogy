package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;
import ws.daley.genealogy.gedcom.records.NoteStructure;

public class SpouseToFamilyLink extends GedcomXMLRecord
	{
		/**
 n FAMS @<XREF:FAM>@  {1:1}
    +1 <<NOTE_STRUCTURE>>  {0:M}
		 */
		public ArrayList<NoteStructure> note = new ArrayList<NoteStructure>();
		public SpouseToFamilyLink(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "NOTE": this.note.add(new NoteStructure(subRecord)); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}