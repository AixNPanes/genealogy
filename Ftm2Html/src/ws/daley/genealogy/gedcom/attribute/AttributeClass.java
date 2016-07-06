package ws.daley.genealogy.gedcom.attribute;


public class AttributeClass
{
	private Class<? extends Gc_Attribute> element;
	
	public AttributeClass(Class<? extends Gc_Attribute> _element) {
		this.element = _element;
	}

	public Class<? extends Gc_Attribute> getElement() {return this.element;}
}
