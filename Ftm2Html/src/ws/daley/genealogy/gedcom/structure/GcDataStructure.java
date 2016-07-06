package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcNameOfSourceDataAttribute;
import ws.daley.genealogy.gedcom.line.GcCopyrightLine;
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
 * used in SOUR
 * 
 * n DATA <NAME_OF_SOURCE_DATA>					{0:1}	
 *     +1 DATE <PUBLICATION_DATE>				{0:1}	
 *     +1 COPR <COPYRIGHT_SOURCE_DATA>			{0:1}	
 */

public class GcDataStructure extends Gc_Structure
{
	public String data;
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("DATE", 0, 1, GcDateLine.class),
				new TagDescriptor("COPR", 0, 1, GcCopyrightLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("NAME_OF_SOURCE_DATA", 0, 1, GcNameOfSourceDataAttribute.class)
		});
	}
	
	public GcDataStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "DATA", attributeDescriptorMap, tagDescriptorMap, _vector);
	}
	
	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		this.data = getParameters();
		return true;
	}
};
