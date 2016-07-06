package ws.daley.genealogy.gedcom.attribute;

import java.util.HashMap;
import java.util.Map;

public class AttributeDescriptorMap extends HashMap<String, AttributeDescriptor>
{
	private static final long serialVersionUID = 1L;
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptor descriptor1, AttributeDescriptor descriptor2)
	{
		return newFromArray(new AttributeDescriptor[]{descriptor1}, new AttributeDescriptor[]{descriptor2});
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptor descriptor1, AttributeDescriptorMap descriptorMap2)
	{
		return newFromArray(new AttributeDescriptor[]{descriptor1}, descriptorMap2==null?null:descriptorMap2.toArray());
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptor descriptor1, AttributeDescriptor[] descriptors2)
	{
		return newFromArray(new AttributeDescriptor[]{descriptor1}, descriptors2);
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptorMap descriptorMap1, AttributeDescriptor descriptor2)
	{
		return newFromArray(descriptorMap1==null?null:descriptorMap1.toArray(), new AttributeDescriptor[]{descriptor2});
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptor[] descriptors1, AttributeDescriptor descriptor2)
	{
		return newFromArray(descriptors1, new AttributeDescriptor[]{descriptor2});
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptorMap descriptorMap1, AttributeDescriptorMap descriptorMap2)
	{
		return newFromArray(descriptorMap1==null?null:descriptorMap1.toArray(), descriptorMap2==null?null:descriptorMap2.toArray());
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptorMap descriptorMap1, AttributeDescriptor[] descriptors2)
	{
		return newFromArray(descriptorMap1==null?null:descriptorMap1.toArray(), descriptors2);
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptor[] descriptors1, AttributeDescriptorMap descriptorMap2)
	{
		return newFromArray(descriptors1, descriptorMap2==null?null:descriptorMap2.toArray());
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptor descriptor)
	{
		return newFromArray(new AttributeDescriptor[]{descriptor}, (AttributeDescriptor[])null);
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptor[] descriptors)
	{
		return newFromArray(descriptors, (AttributeDescriptor[])null);
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptorMap descriptorMap)
	{
		return newFromArray(descriptorMap==null?null:descriptorMap.toArray(), (AttributeDescriptor[])null);
	}
	
	public static AttributeDescriptorMap newFromArray(AttributeDescriptor[] descriptors1, AttributeDescriptor[] descriptors2)
	{
		AttributeDescriptorMap map = new AttributeDescriptorMap();
		if (descriptors1 != null)
			for(AttributeDescriptor descriptor:descriptors1)
				map.put(descriptor.getAttributeName(), descriptor);
		if (descriptors2 != null)
			for(AttributeDescriptor descriptor:descriptors2)
				map.put(descriptor.getAttributeName(), descriptor);
		return map;
	}
	
	public AttributeDescriptor[] toArray()
	{
		AttributeDescriptor[] array = new AttributeDescriptor[this.size()];
		int i = 0;
		for(Map.Entry<String, AttributeDescriptor> entry:this.entrySet())
			array[i++] = entry.getValue();
		return array;
	}
}
