package ws.daley.genealogy.gedcom;

import ws.daley.genealogy.Ged2XML;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.util.Util;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

// Class for a note structure (or note reference)
public class GcNote extends GcBaseElement
{
	public char[] NoteID = new char[Util.XREFSIZE+1];
	public STRING Note = new STRING();

	public GcNote() {}
	
	public GcNote(GcBaseElement e)
	{
		super(e);
	}

	public Boolean Import(GedcomFile file)
	{
		// NB! Remember to check for structured notes!
		int TopLevel = file.GetLevel();
		this.NoteID = file.GetPointer();
		this.Note = new STRING(file.GetText());
		file.NextLine();
		if (!file.InterpretLine())
			return (Boolean.FALSE);
		while (file.GetLevel()>TopLevel)
		{
			switch(file.GetTag())
			{
			case TAG_CONT:
				this.Note.Add("\r\n");
				this.Note.Add(file.GetText());
				file.NextLine();
				break;
			case TAG_CONC:
				this.Note.Add(file.GetText());
				file.NextLine();
				break;
			case TAG_SOUR:
				file.PrintError(Ged2XML.ERROR_IGNORE,Boolean.TRUE);
				if (!file.SkipStructure())
					return Boolean.FALSE;
				break;
			default:
				file.IllegalTag();
				break;
			}
			if (!file.InterpretLine())
				return (Boolean.FALSE);
		}
		return (Boolean.TRUE);
	}
};
