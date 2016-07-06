package ws.daley.genealogy.gedcom;
/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

// Class for a string which may use CONT/CONC
public class GcString
{
	public STRING text = new STRING();

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

	public Boolean Import(GedcomFile file)
	{
		int TopLevel = file.GetLevel();
		this.text = new STRING(file.GetText());
		file.NextLine();
		if (!file.InterpretLine())
			return (Boolean.FALSE);
		while (file.GetLevel()>TopLevel)
		{
			switch(file.GetTag())
			{
			case TAG_CONT:
				this.text.Add("\n");
				this.text.Add(file.GetText());
				file.NextLine();
				break;
			case TAG_CONC:
				this.text.Add(file.GetText());
				file.NextLine();
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
