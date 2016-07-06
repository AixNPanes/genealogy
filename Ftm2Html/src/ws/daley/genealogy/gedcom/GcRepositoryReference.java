package ws.daley.genealogy.gedcom;

import ws.daley.genealogy.gedcom.util.Util;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcRepositoryReference
{
	public GcNote Note = new GcNote();
	public STRING Callnumber= new STRING();
	public char[] Rep = new char[Util.XREFSIZE+1];

	public GcRepositoryReference(){this.Rep[0]=0;}

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

	public Boolean Import(GedcomFile file)
	{
		int TopLevel = file.GetLevel();
		this.Rep = file.GetPointer().toString().toCharArray();
		file.NextLine();
		if (!file.InterpretLine())
			return (Boolean.FALSE);
		while (file.GetLevel()>TopLevel)
		{
			switch(file.GetTag())
			{
			case TAG_CALN:
				this.Callnumber = new STRING(file.GetText());
				file.NextLine();
				break;
			case TAG_NOTE:
				if (!this.Note.Import(file))
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
