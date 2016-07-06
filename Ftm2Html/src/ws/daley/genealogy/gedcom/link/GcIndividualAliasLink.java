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
 * used in INDIVIDUAL_RECORD
 * n ALIA @<XREF:INDI>@					{0:M} 
 */

public class GcIndividualAliasLink extends Gc_Link
{
	public GcIndividualAliasLink(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "ALIA", _vector);
	}
};
