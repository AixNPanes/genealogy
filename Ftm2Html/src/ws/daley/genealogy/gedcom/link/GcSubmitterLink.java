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
 * used in SUBMISSION_RECORD
 * n SUBM @<XREF:SUBM>@					{0:M} 
 */

public class GcSubmitterLink extends Gc_Link
{
	public GcSubmitterLink(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "SUBM", _vector);
	}
};
