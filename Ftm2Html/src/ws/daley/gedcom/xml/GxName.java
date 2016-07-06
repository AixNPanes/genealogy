package ws.daley.gedcom.xml;

import ws.daley.gedcom.Name;
import ws.daley.genealogy.gedcom.structure.record.GcHeaderRecord;

public class GxName extends Name
{
//    @XmlValue
//    protected String value;
//    @XmlAttribute(name = "Type")
//    protected String type;
//    @XmlAttribute(namespace = "http://www.w3.org/XML/1998/namespace")
//    protected String lang;

    public GxName() {}

	public GxName(GcHeaderRecord headerRecord)
	{
		this();
	}
	
	public String getNameValue() {return this.getValue();}
	public String getNameType() {return this.getType();}
	public String getNameLang() {return this.getLang();}
	
	public static boolean isEmpty(Name name)
	{
		return name.getValue() == null || name.getValue().length() == 0;
	}
}

