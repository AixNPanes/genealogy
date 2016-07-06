package ws.daley.genealogy.gedcom.structure.util;

import ws.daley.genealogy.gedcom.object.GcBaseElement;

public class TagDescriptor
{
	private String tagName;
	private Counts count;
	private StructureClass clazz;
	
	public TagDescriptor(String _tagName, int _minimum, int _maximum, Class<? extends GcBaseElement> _class) {
		this.tagName = _tagName;
		this.count = new Counts(_minimum, _maximum);
		if (_class == null)
			throw new RuntimeException("class cannot be null.");
		this.clazz = new StructureClass(_class);
	}

	public String getTagName() {return this.tagName;}
	public void setTagName(String tagName) {this.tagName = tagName;}
	public Counts getCounts() {return this.count;}
	public void setCounts(Counts counte) {this.count = counte;}
	public StructureClass getClazz() {return this.clazz;}
	public void setClazz(StructureClass clazz) {this.clazz = clazz;}
}
