package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcDateLine;
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
 * 	n DATA	{0:1} 
 * 	+1 DATE <ENTRY_RECORDING_DATE>				{0:1} 
 * 	+1 TEXT <TEXT_FROM_SOURCE>					{0:M} 
 * 		+2 [ CONC | CONT ] <TEXT_FROM_SOURCE>	{0:M} 
 */

public class GcSourceCitationDataStructure extends Gc_Structure
{
	public String data;
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("DATE", 0, 1, GcDateLine.class),
				new TagDescriptor("TEXT", 0, 1, GcTextFromSourceStructure.class)
		});
	}
	
	public GcSourceCitationDataStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "DATA", null, tagDescriptorMap, _vector);
	}
};
