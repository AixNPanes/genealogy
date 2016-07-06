package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcEncodedMultimediaLine;
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
 * used in MULTIMEDIA_LINK
 * 
 * n BLOB											{1:1} 
 * 	+1 CONT <ENCODED_MULTIMEDIA_LINE>				{1:M} 
 */

public class GcBlobStructure extends Gc_Structure
{
	public String data;
	private static TagDescriptorMap map = new TagDescriptorMap();
	
	static{
		map = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("CONT", 0, 1, GcEncodedMultimediaLine.class)
		});
	}
	
	public GcBlobStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "BLOB", null, map, _vector);
	}
};
