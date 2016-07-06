package ws.daley.genealogy.gedcom;

import java.util.Vector;

import ws.daley.genealogy.Ged2XML;
import ws.daley.genealogy.gedcom.object.GcTags;
import ws.daley.genealogy.gedcom.structure.record.GcSourceRecord;
import ws.daley.genealogy.gedcom.util.Util;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcSourceCite
{
	// Structured source cites:
	public char[] sourceID = new char[Util.XREFSIZE+1];
	public STRING page = new STRING();
	public STRING quality = new STRING();;
	// Unstructured source cites:
	public GcString sourceDescription = new GcString();
	public long newSourceID;
	// Common attributes:
	public GcString text = new GcString();
	public GcNote note = new GcNote();

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

	public Boolean Import(GedcomFile file,@SuppressWarnings("unused") Vector<GcSourceRecord> Sources)
	{
		int DataLevel,TopLevel;
	
		TopLevel = file.GetLevel();
		if (file.GetPointer()[0] != 0)
		{
			// Structured source
			this.sourceID = file.GetPointer();
			file.NextLine();
			if (!file.InterpretLine())
				return (Boolean.FALSE);
			while (file.GetLevel()>TopLevel)
			{
				switch(file.GetTag())
				{
				case TAG_PAGE:
					this.page = new STRING(file.GetText());
					file.NextLine();
					break;
				case TAG_QUAY:
					this.quality = new STRING(file.GetText());
					file.NextLine();
					break;
				case TAG_DATA:
					DataLevel = file.GetLevel();
					file.NextLine();
					if (!file.InterpretLine())
						return Boolean.FALSE;
					while (file.GetLevel()>DataLevel)
					{
						if (file.GetTag()==GcTags.TAG.TAG_DATE)
						{
							file.PrintError(Ged2XML.ERROR_IGNORE,Boolean.TRUE);
							file.SkipStructure();
						}
						else if (file.GetTag()==GcTags.TAG.TAG_TEXT)
						{
							if (!this.text.Import(file))
								return Boolean.FALSE;
						}
						else
							file.IllegalTag();
						if (!file.InterpretLine())
							return Boolean.FALSE;
					}
					break;
				case TAG_NOTE:
					if (!this.note.Import(file))
						return Boolean.FALSE;
					break;
				default:
					file.IllegalTag();
					break;
				}
				if (!file.InterpretLine())
					return (Boolean.FALSE);
			}
		}
		else
		{
			// Unstructured source
			this.sourceID[0] = 0;
			do
			{
				if (file.GetTag()==GcTags.TAG.TAG_CONT)
					this.sourceDescription.text.Add("\r\n");
				this.sourceDescription.text.Add(file.GetText());
				file.NextLine();
				if (!file.InterpretLine())
					return (Boolean.FALSE);
			}
			while (file.GetTag()==GcTags.TAG.TAG_CONT || file.GetTag()==GcTags.TAG.TAG_CONC);
			while (file.GetLevel()>TopLevel)
			{
				switch(file.GetTag())
				{
				case TAG_TEXT:
					if (!this.text.Import(file))
						return Boolean.FALSE;
					break;
				case TAG_NOTE:
					if (!this.note.Import(file))
						return Boolean.FALSE;
					break;
				default:
					file.IllegalTag();
					break;
				}
				if (!file.InterpretLine())
					return (Boolean.FALSE);
			}
		}
		return (Boolean.TRUE);
	}
};
