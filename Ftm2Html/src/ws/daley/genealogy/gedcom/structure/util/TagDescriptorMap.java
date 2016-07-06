package ws.daley.genealogy.gedcom.structure.util;

import java.util.Map;
import java.util.TreeMap;

public class TagDescriptorMap extends TreeMap<String, TagDescriptor>
{
	private static final long serialVersionUID = 1L;
	
	public static TagDescriptorMap newFromArray(TagDescriptor descriptor)
	{
		return newFromArray(new TagDescriptor[]{descriptor}, (TagDescriptor[])null);
	}
	
	public static TagDescriptorMap newFromArray(TagDescriptor[] descriptors)
	{
		return newFromArray(descriptors, (TagDescriptor[])null);
	}
	
	public static TagDescriptorMap newFromArray(TagDescriptorMap descriptorMap)
	{
		return newFromArray(descriptorMap==null?null:descriptorMap.toArray(), (TagDescriptor[])null);
	}
	
	public static TagDescriptorMap newFromArray(TagDescriptorMap descriptorMap, TagDescriptor descriptor)
	{
		return newFromArray(descriptorMap==null?null:descriptorMap.toArray(), new TagDescriptor[]{descriptor});
	}
	
	public static TagDescriptorMap newFromArray(TagDescriptor descriptor, TagDescriptor[] descriptors)
	{
		return newFromArray(new TagDescriptor[]{descriptor}, descriptors);
	}
	
	public static TagDescriptorMap newFromArray(TagDescriptorMap descriptorMap, TagDescriptor[] descriptors)
	{
		return newFromArray(descriptorMap==null?null:descriptorMap.toArray(), descriptors);
	}
	
	public static TagDescriptorMap newFromArray(TagDescriptorMap descriptorMap1, TagDescriptorMap descriptorMap2)
	{
		return newFromArray(descriptorMap1==null?null:descriptorMap1.toArray(), descriptorMap2==null?null:descriptorMap2.toArray());
	}
	
	public static TagDescriptorMap newFromArray(TagDescriptor[] descriptors, TagDescriptor descriptor)
	{
		return newFromArray(descriptors, new TagDescriptor[]{descriptor});
	}
	
	public static TagDescriptorMap newFromArray(TagDescriptor[] descriptors1, TagDescriptor[] descriptors2)
	{
		TagDescriptorMap map = new TagDescriptorMap();
		if (descriptors1 != null)
			for(TagDescriptor descriptor:descriptors1)
			{
				map.put(descriptor.getTagName(), descriptor);
			}
		if (descriptors2 != null)
			for(TagDescriptor descriptor:descriptors2)
			{
				TagDescriptor t = map.put(descriptor.getTagName(), descriptor);
				if (t != null)
				{
					if (! "NOTE".equals(t.getTagName()) && ! "OBJE".equals(t.getTagName()) && ! "SOUR".equals(t.getTagName()))
					{
						@SuppressWarnings("unused")
                        int i = 0;
					}
				}
			}
		return map;
	}
	
	public TagDescriptor[] toArray()
	{
		TagDescriptor[] array = new TagDescriptor[this.size()];
		int i = 0;
		for(Map.Entry<String, TagDescriptor> entry:this.entrySet())
			array[i++] = entry.getValue();
		return array;
	}
}
