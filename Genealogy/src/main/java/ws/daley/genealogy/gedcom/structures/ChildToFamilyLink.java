package ws.daley.genealogy.gedcom.structures;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;
import ws.daley.genealogy.gedcom.records.NoteStructure;

public class ChildToFamilyLink extends GedcomXMLRecord
	{
		/**
 n  FAMC @<XREF:FAM>@  {1:1}
    +1 PEDI <PEDIGREE_LINKAGE_TYPE>  {0:1}
    +1 <<NOTE_STRUCTURE>>  {0:M}
		 */
		public GedcomRecord pedi;
		public ArrayList<NoteStructure> note = new ArrayList<NoteStructure>();
		public ChildToFamilyLink(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "PEDI": this.pedi = subRecord; break;
					case "NOTE": this.note.add(new NoteStructure(subRecord)); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}