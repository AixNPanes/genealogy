package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcCharacterSetAttribute;
import ws.daley.genealogy.gedcom.line.GcVersionLine;
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
 * used in HEAD
 * 
 * n CHAR <CHARACTER_SET>							{1:1}	
 *     +1 VERS <VERSION_NUMBER>						{0:1}	
 */

public class GcCharactersetStructure extends Gc_Structure
{
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("VERS", 0, 1, GcVersionLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("CHARACTER_SET", 0, 1, GcCharacterSetAttribute.class)
		});
	}

	public GcCharactersetStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "CHAR", attributeDescriptorMap, tagDescriptorMap, _vector);
	}

	public String getVersion() {return getParameterForKey("VERS");}
};
