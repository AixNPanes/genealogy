package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcNameLine extends Gc_Line
{
	public GcNameLine(GcBaseElement e)
	{
		super(e, "NAME", null);
	}
};
