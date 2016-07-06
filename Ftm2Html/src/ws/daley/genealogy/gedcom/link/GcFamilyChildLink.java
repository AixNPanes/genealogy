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
 * used in INDIVIDUAL_EVENT_STRUCTURE
 * 
 * n FAMC @<XREF:FAM>@
 */

public class GcFamilyChildLink extends Gc_Link
{
	public GcFamilyChildLink(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "FAMC", _vector);
	}
};
