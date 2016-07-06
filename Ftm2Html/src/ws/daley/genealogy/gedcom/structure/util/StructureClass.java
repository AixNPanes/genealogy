package ws.daley.genealogy.gedcom.structure.util;

import java.lang.reflect.Constructor;
import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;

public class StructureClass
{
	private Class<? extends GcBaseElement> element;
	
	public StructureClass(Class<? extends GcBaseElement> _element) {
		this.element = _element;
	}

	public Class<? extends GcBaseElement> getElement() {return this.element;}
	
	public Class<?> newXml(){throw new RuntimeException("not implemented");}
	
	public GcBaseElement newInstance(IGcBaseElement e, String tag, TagDescriptorMap tagDescriptorMap, Vector<GcBaseElement> vector)
	{
		Constructor<?>[] constructors = this.element.getConstructors();
		Vector<Throwable> exceptions = new Vector<Throwable>();
		Constructor<?> constructor = null;
		try {
			constructor = this.element.getConstructor(new Class<?>[]{GcBaseElement.class, AttributeDescriptorMap.class, TagDescriptorMap.class, Vector.class});
			return (GcBaseElement)constructor.newInstance(new Object[]{e, null, tagDescriptorMap, vector});
		} catch(Exception exception)
		{
			exceptions.add(exception);
		}
		try {
			constructor = this.element.getConstructor(new Class<?>[]{GcBaseElement.class, AttributeDescriptorMap.class, TagDescriptorMap.class});
			return (GcBaseElement)constructor.newInstance(new Object[]{e, null, tagDescriptorMap});
		} catch(Exception exception)
		{
			exceptions.add(exception);
		}
		try {
			constructor = this.element.getConstructor(new Class<?>[]{GcBaseElement.class, TagDescriptorMap.class, Vector.class});
			return (GcBaseElement)constructor.newInstance(new Object[]{e, tagDescriptorMap, vector});
		} catch(Exception exception)
		{
			exceptions.add(exception);
		}
		try {
			constructor = this.element.getConstructor(new Class<?>[]{GcBaseElement.class, Vector.class});
			return (GcBaseElement)constructor.newInstance(new Object[]{e, vector});
		} catch(Exception exception)
		{
			exceptions.add(exception);
		}
		try {
			constructor = this.element.getConstructor(new Class<?>[]{GcBaseElement.class, Vector.class});
			return (GcBaseElement)constructor.newInstance(new Object[]{e, vector});
		} catch(Exception exception)
		{
			exceptions.add(exception);
		}
		try {
			constructor = this.element.getConstructor(new Class<?>[]{GcBaseElement.class, String.class, AttributeDescriptorMap.class, Vector.class});
			return (GcBaseElement)constructor.newInstance(new Object[]{e, tag, null, vector});
		} catch(Exception exception)
		{
			exceptions.add(exception);
		}
		try {
			constructor = this.element.getConstructor(new Class<?>[]{GcBaseElement.class, String.class, AttributeDescriptorMap.class});
			return (GcBaseElement)constructor.newInstance(new Object[]{e, tag, null});
		} catch(Exception exception)
		{
			exceptions.add(exception);
		}
		try {
			constructor = this.element.getConstructor(new Class<?>[]{GcBaseElement.class});
			return (GcBaseElement)constructor.newInstance(new Object[]{e});
		} catch(Exception exception)
		{
			exceptions.add(exception);
		}
		for(Throwable t:exceptions)
		{
			if (! (t instanceof NoSuchMethodException))
			{
				System.out.println("----------------------------");
				t.printStackTrace();
//				if (t instanceof InvocationTargetException)
//				{
//					System.out.println("============================");
//					((InvocationTargetException)t).printStackTrace();
//				}
			}
		}
		throw new RuntimeException("No valid constructor found");
	}
}
