package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * CHANGE_DATE:= 
 * n CHAN																	{1:1} 
 * 	+1 DATE <CHANGE_DATE>												{1:1} 
 * 		+2 TIME <TIME_VALUE>											{0:1} 
 * 	+1 <<NOTE_STRUCTURE>>												{0:M} 
 * 
 * The change date is intended to only record the last change to a record.  
 * Some systems may want to manage the change process with more detail, but it is 
 * sufficient for GEDCOM purposes to indicate the last time that a record was 
 * modified.
 */

public class GcChangeDateStructure extends Gc_Structure
{
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("DATE", 1, 1, GcChangeDateDateStructure.class),
				new TagDescriptor("NOTE", 0, Integer.MAX_VALUE, GcNoteStructure.class)
		});
	}

	public GcChangeDateStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "CHAN", null, tagDescriptorMap, _vector);
	}
};
