package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n ADR1 <ADDRESS_LINE1>
 */

public class GcAddressLine1 extends GcAddressLine
{
	public GcAddressLine1(GcBaseElement e)
	{
		super(e, "ADR1");
	}
};
