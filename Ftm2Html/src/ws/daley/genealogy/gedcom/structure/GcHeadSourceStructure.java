package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcApprovedSystemIdAttribute;
import ws.daley.genealogy.gedcom.line.GcSourceNameLine;
import ws.daley.genealogy.gedcom.line.GcSourceTitleLine;
import ws.daley.genealogy.gedcom.line.GcVersionLine;
import ws.daley.genealogy.gedcom.link.GcRepositoryLink;
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
 * n SOUR <APPROVED_SYSTEM_ID>						{1:1}	
 *     +1 VERS <VERSION_NUMBER>						{0:1}	
 *     +1 NAME <NAME_OF_PRODUCT>					{0:1}	
 *     +1 CORP <NAME_OF_BUSINESS>					{0:1}	
 *         +2 <<ADDRESS_STRUCTURE>>					{0:1}	
 *     +1 DATA <NAME_OF_SOURCE_DATA>				{0:1}	
 *         +1 DATE <PUBLICATION_DATE>				{0:1}	
 *         +1 COPR <COPYRIGHT_SOURCE_DATA>			{0:1}	
 */

public class GcHeadSourceStructure extends Gc_Structure
{
	public String approvedSystemId;
	
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("VERS", 0, 1, GcVersionLine.class),
			new TagDescriptor("NAME", 0, 1, GcSourceNameLine.class),
			new TagDescriptor("CORP", 0, 1, GcCorporationStructure.class),
			new TagDescriptor("DATA", 0, 1, GcDataStructure.class),
			new TagDescriptor("TITL", 0, 1, GcSourceTitleLine.class),
			new TagDescriptor("REPO", 0, 1, GcRepositoryLink.class),
			new TagDescriptor("NOTE", 0, 1, GcSourceNoteStructure.class),
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("APPROVED_SYSTEM_ID", 0, 1, GcApprovedSystemIdAttribute.class)
		});
	}

	public GcHeadSourceStructure() {}
	
	public GcHeadSourceStructure(GcBaseElement e, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "SOUR", attributeDescriptorMap, TagDescriptorMap.newFromArray(_tagDescriptorMap, tagDescriptorMap), _vector);
		this.approvedSystemId = getParameters();
	}
	
	public String getVersion() {return this.getParameterForKey("VERS");}
	public String getName() {return this.getParameterForKey("NAME");}
	public GcCorporationStructure getCorporation() {return (GcCorporationStructure)this.getElementForKey("CORP");}
	public GcDataStructure getData() {return (GcDataStructure)this.getElementForKey("DATA");}
	public String getSourceTitleLine() {return this.getParameterForKey("TITL");}
	public GcSourceNoteStructure getNote() {return (GcSourceNoteStructure)this.getElementForKey("NOTE");}
	public String getRepositoryLink() {return this.getLinkForKey("REPO");}
};
