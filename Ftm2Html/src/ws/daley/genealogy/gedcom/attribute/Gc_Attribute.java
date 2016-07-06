package ws.daley.genealogy.gedcom.attribute;

import ws.daley.genealogy.gedcom.line.Gc_Line;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.IGcBaseElement;
import ws.daley.genealogy.gedcom.structure.util.Counts;

public abstract class Gc_Attribute extends GcBaseElement
{
	public String parameters;
	public String type;
	public AttributeDescriptorMap attributeDescriptorMap = null;
	public BaseAttributeMap baseAttributeMap = new BaseAttributeMap();  

	public Gc_Attribute() {}
	
	public Gc_Attribute(@SuppressWarnings("unused") IGcBaseElement e)
	{
		throw new RuntimeException("Unsupported");
	}
	
	public Gc_Attribute(Gc_Line e, String _type)
	{
		super(e);
		this.attributeDescriptorMap = e.attributeDescriptorMap;
		this.parameters = e.getParameters().trim();
		this.type = _type;
	}
	
	@Override
    public boolean interpret()
	{
		if (this.attributeDescriptorMap.size() == 1)
		{
			AttributeDescriptor attributeDescriptor = this.attributeDescriptorMap.get(this.type);
			Counts counts = attributeDescriptor.getCounts();
			if (this.parameters.length() < counts.getMin())
				return false;
			if (this.parameters.length() > counts.getMax())
				return false;
			BaseAttribute attribute = new BaseAttribute(this.type, this.getParametersTokenRange());
			return true;
		}
		return false;
//			for(Entry<String, AttributeDescriptor> descriptor:attributeDescriptorMap.entrySet()) {
//				String tag = descriptor.getKey();
//				AttributeDescriptor value = descriptor.getValue();
//				BaseElementVector vector = vectorMap.get(tag);
//				int siz = vector.size();
//				Counts counts = value.getCounts();
//				int min = counts.getMin();
//				int max = counts.getMax();
//				if (siz < min)
//					throw new RuntimeException("Tag "+tag+" occurred "+siz+" times which is less than the minimum of "+min);
//				if (siz > max)
//					throw new RuntimeException("Tag "+tag+" occurred "+siz+" times which is more than the maximum of "+max);
//			}
//		mustNotHaveParameters();
//		mustHaveParameters();
	}
};
