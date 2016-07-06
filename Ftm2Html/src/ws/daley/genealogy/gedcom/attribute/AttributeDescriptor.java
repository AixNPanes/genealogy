package ws.daley.genealogy.gedcom.attribute;

import ws.daley.genealogy.gedcom.structure.util.Counts;

public class AttributeDescriptor
{
	private String attributeName;
	private Counts count;
	private AttributeClass clazz;
	
	public AttributeDescriptor(String _attributeName, int _minimum, int _maximum, Class<? extends Gc_Attribute> _class)
	{
		this.attributeName = _attributeName;
		this.count = new Counts(_minimum, _maximum);
		if (_class == null)
			throw new RuntimeException("class cannot be null.");
		this.clazz = new AttributeClass(_class);
	}

	public String getAttributeName() {return this.attributeName;}
	public void setAttributeName(String attributeName) {this.attributeName = attributeName;}
	public Counts getCounts() {return this.count;}
	public void setCounts(Counts counte) {this.count = counte;}
	public AttributeClass getClazz() {return this.clazz;}
	public void setClazz(AttributeClass clazz) {this.clazz = clazz;}
}
