package ws.daley.genealogy.gedcom.structure;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.Vector;
import java.util.Map.Entry;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.line.Gc_Line;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVector;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVectorMap;
import ws.daley.genealogy.gedcom.structure.util.Counts;
import ws.daley.genealogy.gedcom.structure.util.StructureClass;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public abstract class Gc_Structure extends Gc_Line implements IGc_Structure
{
	protected TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	protected BaseElementVectorMap vectorMap = new BaseElementVectorMap();  

	public Gc_Structure() {}
	
	public Gc_Structure(@SuppressWarnings("unused") IGcBaseElement e)
	{
		throw new RuntimeException("Unsupported");
	}
	
	public Gc_Structure(GcBaseElement e, String type, AttributeDescriptorMap _attributeDescriptorMap, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, type, _attributeDescriptorMap);
		mergeMapItems(_tagDescriptorMap);
		buildVectorMap(_vector);
	}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.structure.IGv_Structure#buildVectorMap(java.util.Vector)
	 */
	public void buildVectorMap(Vector<GcBaseElement> vector)
	{
		if (vector != null)
			for(IGcBaseElement element:vector)
			{
				String tagName = element.getTagName();
				if ("FAM".equals(tagName))
				{
					@SuppressWarnings("unused")
                    int i = 0;
				}
				TagDescriptor tagDescriptor = this.tagDescriptorMap.get(tagName);
				if (tagDescriptor == null)
					throw new RuntimeException("Tag "+tagName+" not valid for "+this.getClass().getName());
				BaseElementVector baseElementVector = this.vectorMap.get(tagName);
				if (baseElementVector == null)
					throw new RuntimeException("Tag "+tagName+" not in map for "+this.getClass().getName());
				StructureClass clazz = tagDescriptor.getClazz();
				GcBaseElement newElement = clazz.newInstance(element, tagName, /*tagDescriptorMap*/ (TagDescriptorMap)null, element.getElements());
				baseElementVector.add(newElement);
				this.vectorMap.put(tagName, baseElementVector);
			}
	}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.structure.IGv_Structure#mergeMapItems(ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap)
	 */
	public void mergeMapItems(TagDescriptorMap _tagDescriptorMap)
	{
		if (_tagDescriptorMap != null)
		{
			// add new map items to existing ones
			for(Entry<String, TagDescriptor> descriptor:_tagDescriptorMap.entrySet())
			{
				String key = descriptor.getKey();
				this.tagDescriptorMap.put(key, descriptor.getValue());
				this.vectorMap.put(key, new BaseElementVector());
			}
		}
	}
	
	protected String getParameterForKey(String key)
	{
		GcBaseElement element = getElementForKey(key);
		if (element != null)
		{
			return this.getParameters();
		}
		return null;
	}
	
	protected String getLinkForKey(String key)
	{
		GcBaseElement element = getElementForKey(key);
		if (element != null)
		{
			return this.getLine().getLink();
		}
		return null;
	}
	
	protected String getXrefForKey(String key)
	{
		GcBaseElement element = getElementForKey(key);
		if (element != null)
		{
			return this.getLine().getLink();
		}
		return null;
	}
	
	protected GcBaseElement getElementForKey(String key)
	{
		BaseElementVector vector = this.vectorMap.get(key);
		if (vector != null && vector.size()>0)
		{
			return vector.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.structure.IGv_Structure#interpretTag(java.lang.String, ws.daley.genealogy.gedcom.object.GcBaseElement)
	 */
	public void interpretTag(String t, IGcBaseElement e) {
		if (t == null || t.length()==0)
			throw new RuntimeException("Tag cannot be null or empty.");
		TagDescriptor descriptor = this.tagDescriptorMap.get(t);
		if (descriptor == null)
			throw new RuntimeException("Tag("+t+") invalid.");
		Class<? extends GcBaseElement> clazz = descriptor.getClazz().getElement();
		try {
				BaseElementVector vector = this.vectorMap.get(t);
				if (vector == null)
					throw new RuntimeException("Tag("+t+") invalid.");
				Constructor<? extends GcBaseElement>constuctor = clazz.getConstructor(GcBaseElement.class);
				GcBaseElement element = constuctor.newInstance(e);
				vector.add(element);
				this.vectorMap.put(t, vector);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.structure.IGv_Structure#interpret()
	 */
	@Override
    public boolean interpret()
	{
		if (!super.interpret())
			return false;
		for(Entry<String, TagDescriptor> descriptor:this.tagDescriptorMap.entrySet()) {
			String tag = descriptor.getKey();
			TagDescriptor value = descriptor.getValue();
			BaseElementVector vector = this.vectorMap.get(tag);
			int siz = vector.size();
			Counts counts = value.getCounts();
			int min = counts.getMin();
			int max = counts.getMax();
			if (siz < min)
				return false;
			if (siz > max)
				return false;
			for(IGcBaseElement element:vector)
			{
				if (!element.interpret())
					return false;
			}
		}
//		mustNotHaveParameters();
//		mustHaveParameters();
		return true;
	}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.structure.IGv_Structure#mustNotHaveParameters()
	 */
	public void mustNotHaveParameters() {
		if (getParameters().length() > 0)
			throw new RuntimeException(this.type+" tag cannot have additional parameters("+getParameters()+")");
	}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.structure.IGv_Structure#mustHaveParameters()
	 */
	public void mustHaveParameters() {
		if (this.elements.size() > 0)
			throw new RuntimeException(this.type+" tag must have have subrecords");
	}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.structure.IGv_Structure#getVectorMap()
	 */
	public BaseElementVectorMap getVectorMap() {return this.vectorMap;}
	
	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.structure.IGv_Structure#getFirstStructureElement(java.lang.String)
	 */
	public IGcBaseElement getFirstStructureElement(String _tag)
	{
		Vector<GcBaseElement> vector = this.getVectorMap().get(_tag);
		if (vector == null || vector.size() == 0)
			return null;
		return vector.get(0);
	}

	/* (non-Javadoc)
	 * @see ws.daley.genealogy.gedcom.structure.IGv_Structure#emitXML(java.io.PrintStream, int)
	 */
	@Override
	public void emitXML(PrintStream stream, int indent) throws IOException
	{
		String tag = getXmlTag();
		stream.println(getTabs(indent)+"<"+tag+">");
		for(Entry<String, BaseElementVector> vector:this.getVectorMap().entrySet())
		{
			for(IGcBaseElement element: vector.getValue())
			{
				element.emitXML(stream, indent+1);
			}
		}
		stream.println(getTabs(indent)+"</"+tag+">");
	}
};
