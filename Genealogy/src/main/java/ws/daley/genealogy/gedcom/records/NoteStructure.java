package ws.daley.genealogy.gedcom.records;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;

public class NoteStructure extends GedcomXMLRecord
	{
		/**
  n  NOTE @<XREF:NOTE>@  {1:1}
    +1 SOUR @<XREF:SOUR>@  {0:M}
  |
  n  NOTE [<SUBMITTER_TEXT> | <NULL>]  {1:1}
    +1 [ CONC | CONT ] <SUBMITTER_TEXT>  {0:M}
    +1 SOUR @<XREF:SOUR>@  {0:M}
    */
		public ArrayList<GedcomRecord> sour = new ArrayList<GedcomRecord>();
		public ArrayList<GedcomRecord> conc = new ArrayList<GedcomRecord>();
		public NoteStructure(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "SOUR": this.sour.add(subRecord); break;
					case "CONC": this.conc.add(subRecord); break;
					case "CONT": this.conc.add(subRecord); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}