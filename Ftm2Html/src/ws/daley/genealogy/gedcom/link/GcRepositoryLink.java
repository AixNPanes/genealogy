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
 * n REPO @<XREF:REPO>@					{0:M} 
 */

public class GcRepositoryLink extends Gc_Link
{
	public GcRepositoryLink(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "REPO", _vector);
	}
};
