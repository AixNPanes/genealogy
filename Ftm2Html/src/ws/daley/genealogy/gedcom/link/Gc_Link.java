package ws.daley.genealogy.gedcom.link;

import java.util.Vector;
import java.util.Map.Entry;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.Gc_Structure;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVector;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVectorMap;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public abstract class Gc_Link extends Gc_Structure
{
	public TagDescriptorMap tagDescriptorMap;
	public BaseElementVectorMap vectorMap = new BaseElementVectorMap();  
	
	public Gc_Link() {}
	
	public Gc_Link(GcBaseElement e, String _type, Vector<GcBaseElement> _vector)
	{
		super(e, _type, null, null, _vector);
		this.type = _type;
	}
	
	public Gc_Link(GcBaseElement e, String _type, AttributeDescriptorMap attributeDescriptorMap, TagDescriptorMap map, Vector<GcBaseElement> _vector)
	{
		super(e, _type, attributeDescriptorMap, map, _vector);
		this.tagDescriptorMap = map;
		if (map != null)
			for(Entry<String, TagDescriptor> descriptor:map.entrySet()) {
				this.vectorMap.put(descriptor.getKey(), new BaseElementVector());
		}
	}
	
	@Override
    public boolean interpret() {
		this.parameters = getParameters();
		if (this.parameters.length() == 0)
			return false;
		if (this.elements.size() > 0)
			return false;
		if (this.line.getLink().length() > 0)
			return false;
		if (this.line.getXref().length() > 0)
			return false;
		return true;
	}
};
