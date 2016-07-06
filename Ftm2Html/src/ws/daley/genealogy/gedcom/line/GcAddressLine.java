package ws.daley.genealogy.gedcom.line;

import java.util.Map;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcAddressLineAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n ADDR <ADDRESS_LINE>
 */

public class GcAddressLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap(AttributeDescriptorMap map)
	{
		AttributeDescriptorMap attributeDescriptorMap =  AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ADDRESS_LINE", 0, 1, GcAddressLineAttribute.class),
		});
		if (map != null)
			for(Map.Entry<String, AttributeDescriptor> entry: map.entrySet())
				attributeDescriptorMap.put(entry.getKey(), entry.getValue());
		return attributeDescriptorMap;
	}
	
	public GcAddressLine(GcBaseElement e, AttributeDescriptorMap attributeDescriptorMap)
	{
		this(e, "ADDR", getAttributeMap(attributeDescriptorMap));
	}

	protected GcAddressLine(GcBaseElement e, String tag, AttributeDescriptorMap attributeDescriptorMap)
	{
		super(e, tag, getAttributeMap(attributeDescriptorMap));
	}

	protected GcAddressLine(GcBaseElement e, String tag)
	{
		super(e, tag, getAttributeMap(null));
	}
};
