package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n CONT <ADDRESS_LINE> 
 */

public class GcAddressLineCont extends GcAddressLine
{
	public GcAddressLineCont(GcBaseElement e)
	{
		super(e, "CONT");
	}
};
