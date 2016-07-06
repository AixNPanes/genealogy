package ws.daley.genealogy.gedcom.xref;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.Gc_Structure;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public abstract class Gc_Xref extends Gc_Structure
{
	public String link;

	public Gc_Xref() {}
	
	public Gc_Xref(GcBaseElement e, String _type)
	{
		super(e, _type, null, null, null);
	}
	
	public Gc_Xref(GcBaseElement e, String _type, AttributeDescriptorMap _attributeDescriptorMap, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, _type, _attributeDescriptorMap, _tagDescriptorMap, _vector);
	}
	
	@Override
    public boolean interpret() {
		if (getParameters().length() > 0)
			return false;
		if (this.elements.size() > 0)
			return false;
		this.link = this.line.getXref();
		if (this.link.length() == 0)
			return false;
		return true;
	}
};
