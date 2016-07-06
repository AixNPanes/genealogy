package ws.daley.genealogy.gedcom.xref;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n @<XREF:FAM>@ FAM
 */

public class GcFamilyXref extends Gc_Xref
{
	public GcFamilyXref(GcBaseElement e)
	{
		super(e, "FAM");
	}
};
