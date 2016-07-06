package ws.daley.genealogy.gedcom.xref;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcDescendantXref extends Gc_Xref
{
	public GcDescendantXref(GcBaseElement e)
	{
		super(e, "DESI");
	}
};
