package ws.daley.genealogy.gedcom;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcPlace extends GcBaseElement
{
	public STRING Place = new STRING();

	public GcPlace() {}
	
	public GcPlace(GcBaseElement e)
	{
		super(e);
	}

	public Boolean Import(GedcomFile file)
	{
		this.Place = new STRING(file.GetText());
		// TODO: FORM, SOUR, NOTE
		file.NextLine();
		return Boolean.TRUE;
	}
};
