package ws.daley.genealogy.gedcom.line;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Map.Entry;

import ws.daley.genealogy.gedcom.attribute.AttributeClass;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.BaseAttributeMap;
import ws.daley.genealogy.gedcom.attribute.Gc_Attribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.GcTags;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;
import ws.daley.genealogy.gedcom.object.TokenRange;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public abstract class Gc_Line extends GcBaseElement implements IGc_Line
{
	public AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	public BaseAttributeMap attributeMap = new BaseAttributeMap();
	public String type;
	public String parameters;
	public Gc_Attribute attribute = null;

	public Gc_Line() {}
	
	public Gc_Line(IGcBaseElement e, String _type, AttributeDescriptorMap _attributeDescriptorMap)
	{
		super(e);
		this.type = _type;
		this.attributeDescriptorMap = _attributeDescriptorMap;
	}
	
	public String getXmlTag() {throw new RuntimeException("getXmlTag not implemented for "+this.getClass().getName());}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.line.IGc_Line#mergeMapItems(ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap)
	 */
	public void mergeMapItems(AttributeDescriptorMap _attributeDescriptorMap)
	{
		if (_attributeDescriptorMap != null)
		{
			// add new map items to existing ones
			for(Entry<String, AttributeDescriptor> descriptor:_attributeDescriptorMap.entrySet())
			{
				String key = descriptor.getKey();
				this.attributeDescriptorMap.put(key, descriptor.getValue());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.line.IGc_Line#interpret()
	 */
	@Override
    public boolean interpret()
	{
		if (this.attributeDescriptorMap != null && this.attributeDescriptorMap.size() > 0)
		{
			for(Map.Entry<String, AttributeDescriptor> entry: this.attributeDescriptorMap.entrySet())
			{
				if (interpretParms(entry.getValue()))
					return true;
			}
			return false;
		}
		return true;
	}
	
	private boolean interpretParms(AttributeDescriptor attributeDescriptor)
	{
		AttributeClass attributeClass = attributeDescriptor.getClazz();
		Class<? extends Gc_Attribute> clazz = attributeClass.getElement();
		try {
			Constructor<?> constructor = clazz.getConstructor(new Class<?>[]{Gc_Line.class, String.class});
			this.attribute = (Gc_Attribute)constructor.newInstance(new Object[]{this, attributeDescriptor.getAttributeName()});
			if (this.attribute.interpret())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.line.IGc_Line#emitXML(java.io.PrintStream, int)
	 */
	@SuppressWarnings("unused")
    @Override
	public void emitXML(PrintStream stream, int indent) throws IOException
	{
		stream.print(this.tabs+"<"+GcTags.getTagName(this.line.getTag()));
		if (this.attributeMap.size() > 0)
			for(Map.Entry<String, TokenRange> entry:this.attributeMap.entrySet())
			{
				throw new RuntimeException("implementation");
			}
		stream.println("/>");
	}
};
