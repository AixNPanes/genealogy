package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n ADR2 <ADDRESS_LINE2> 
 */

public class GcAddressLine2 extends GcAddressLine
{
	public GcAddressLine2(GcBaseElement e)
	{
		super(e, "ADR2");
	}
};
