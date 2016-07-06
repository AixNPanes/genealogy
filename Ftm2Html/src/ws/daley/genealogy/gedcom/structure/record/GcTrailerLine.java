package ws.daley.genealogy.gedcom.structure.record;

import ws.daley.genealogy.gedcom.line.Gc_Line;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * called from LINEAGE_LINKED_GEDCOM 
 * 0 TRLR	{1:1}
 */

public class GcTrailerLine extends Gc_Line
{
	public GcTrailerLine(GcBaseElement e)
	{
		super(e, "TRLR", null);
	}
};
