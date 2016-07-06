package ws.daley.genealogy.gedcom.xref;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in HEAD 
 *  n SUBN @XREF:SUBN@								{1:1}	
 */

public class GcSubmissionXref extends Gc_Xref
{
	public GcSubmissionXref(GcBaseElement e)
	{
		super(e, "SUBN");
	}
};
