package ws.daley.genealogy.gedcom.records;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;

public class MultimediaLink extends GedcomXMLRecord
	{
		/**
  n  OBJE @<XREF:OBJE>@  {1:1}	         // embedded form
  |          
  n  OBJE           {1:1}				// linked form
    +1 FORM <MULTIMEDIA_FORMAT>  {1:1}
    +1 TITL <DESCRIPTIVE_TITLE>  {0:1}
    +1 FILE <MULTIMEDIA_FILE_REFERENCE>  {1:1}
    +1 <<NOTE_STRUCTURE>>  {0:M}		 */
		public GedcomRecord form;
		public GedcomRecord titl;
		public GedcomRecord file;
		public ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
		public MultimediaLink(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "FORM": this.form = subRecord; break;
					case "TITL": this.titl = subRecord; break;
					case "FILE": this.file = subRecord; break;
					case "TEXT": this.noteStructure.add(new NoteStructure(subRecord)); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}