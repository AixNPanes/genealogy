package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcGedcomContentDescriptionAttribute;
import ws.daley.genealogy.gedcom.line.GcNoteConcLine;
import ws.daley.genealogy.gedcom.line.GcNoteContLine;
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
 * n NOTE <GEDCOM_CONTENT_DESCRIPTION>				{0:1}	
 *     +1 [CONT|CONC] <GEDCOM_CONTENT_DESCRIPTION>	{0:M}
 */

public class GcNoteGedcomContentDescriptionStructure extends Gc_Structure
{
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("CONC", 0, 1, GcNoteConcLine.class),
				new TagDescriptor("CONT", 0, 1, GcNoteContLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("GEDCOM_CONTENT_DESCRIPTION", 0, 1, GcGedcomContentDescriptionAttribute.class)
		});
	}
	
	public GcNoteGedcomContentDescriptionStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "NOTE", attributeDescriptorMap, tagDescriptorMap, _vector);
	}

	public String getConc() {return this.getParameterForKey("CONC");}
	public String getCont() {return this.getParameterForKey("CONT");}
};
