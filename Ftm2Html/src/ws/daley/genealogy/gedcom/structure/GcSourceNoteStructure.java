package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcSourceNoteStructure extends GcNoteStructure
{
	public GcSourceNoteStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, _vector);
	}
};
