package ws.daley.genealogy.gedcom;

import ws.daley.genealogy.Ged2XML;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcChange extends GcBaseElement
{
	public GcSimpleDate Date = new GcSimpleDate();

	public GcChange() {}
	
	public GcChange(IGcBaseElement e) {
		super(e);
	}

	public Boolean Import(GedcomFile file)
	{
		int TopLevel = file.GetLevel();
	
		file.NextLine();
		if (!file.InterpretLine())
			return (Boolean.FALSE);
		while (file.GetLevel()>TopLevel)
		{
			switch(file.GetTag())
			{
			case TAG_DATE:
				if (!this.Date.Import(file))
					return Boolean.FALSE;
				// Unknown characters?
				while (file.GetText().charAt(0)==' ')
					file.AddIndex(1);
				if (file.GetText().charAt(0) != 0)
					file.PrintError(Ged2XML.ERROR_DATEFORMAT,Boolean.TRUE);
				break;
			case TAG_NOTE:
				file.PrintError(Ged2XML.ERROR_IGNORE,Boolean.TRUE);
				file.SkipStructure();
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
