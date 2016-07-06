package ws.daley.genealogy.gedcom.link;

import java.util.Vector;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in FAM_RECORD
 * 
 * n WIFE @<XREF:INDI>@						{0:1} 
 */

public class GcWifeLink extends Gc_Link
{
	public GcWifeLink(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "WIFE", _vector);
	}
};
