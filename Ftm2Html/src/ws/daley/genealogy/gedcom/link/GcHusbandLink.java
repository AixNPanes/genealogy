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
 * n HUSB @<XREF:INDI>@						{0:1} 
 */

public class GcHusbandLink extends Gc_Link
{
	public GcHusbandLink(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "HUSB", _vector);
	}
};
