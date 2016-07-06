package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcDateLDSOrdinanceLine;
import ws.daley.genealogy.gedcom.line.GcDateLine;
import ws.daley.genealogy.gedcom.line.GcTempleCodeLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcIndividualMilitaryStructure extends Gc_Structure
{
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("DATE", 1, 1, GcDateLine.class),
			new TagDescriptor("PLAC", 0, 1, GcDateLDSOrdinanceLine.class),
			new TagDescriptor("SOUR", 0, 1, GcTempleCodeLine.class),
		});
	}
	
	public GcIndividualMilitaryStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "_MILT", null, map, _vector);
	}
};
