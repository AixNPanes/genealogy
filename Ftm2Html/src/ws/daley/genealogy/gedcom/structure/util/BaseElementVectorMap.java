package ws.daley.genealogy.gedcom.structure.util;

import java.util.HashMap;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

public class BaseElementVectorMap extends HashMap<String, BaseElementVector>
{
	private static final long serialVersionUID = 1L;
	
	public GcBaseElement get(String key, int index)
	{
		BaseElementVector vector = super.get(key);
		if (vector == null || vector.size() == 0)
			return null;
		return vector.get(index);
	}
}
