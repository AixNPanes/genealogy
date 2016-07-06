package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcSourceCallNumberAttribute;
import ws.daley.genealogy.gedcom.line.GcSourceMediaTypeLine;
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
 * n CALN <SOURCE_CALL_NUMBER>		{0:M}
 * 	+1 MEDI <SOURCE_MEDIA_TYPE>		{0:1}
 * 
 * This structure is used within a source record to point to a name and address record of the holder of the source document.
 * Formal and informal repository name and addresses are stored in the REPOSITORY_RECORD. Informal repositories include owner's
 * of an unpublished work or of a rare published source, or a keeper of personal collections. An example would be the owner of
 * a family Bible containing unpublished family genealogical entries. More formal repositories, such as the Family History
 * Library, should show a call number of the source at that repository. The call number of that source should be recorded
 * using a subordinate CALN tag. Systems which do not structure a repository name and address interface should store the
 * information about where the source record is stored in the <<NOTE_STRUCTURE>> of this structure. 
 */

public class GcSourceCallNumberStructure extends Gc_Structure
{
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings({ "hiding", "unused" })
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("MEDI", 0, Integer.MAX_VALUE, GcSourceMediaTypeLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("CALN", 0, 1, GcSourceCallNumberAttribute.class)
		});
	}
	
	public GcSourceCallNumberStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "DATA", null, tagDescriptorMap, _vector);
	}
};
