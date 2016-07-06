package ws.daley.genealogy.xml;

import java.util.ArrayList;
import java.util.List;

public class XmlLinesGroup extends ArrayList<XmlLine>
{
    private static final long serialVersionUID = 1L;
    
	private XmlLinesGroup xmlLinesGroup = null;
	
	public XmlLinesGroup() {super();}
	
	public XmlLinesGroup(List<XmlLine> list)
	{
		this.xmlLinesGroup = new XmlLinesGroup();
		this.xmlLinesGroup.addAll(list);
	}
	
	public XmlLinesGroup(XmlLinesGroup section, int fromIndex, int toIndex)
	{
		this.xmlLinesGroup = new XmlLinesGroup();
		this.xmlLinesGroup.addAll(section.subList(fromIndex, toIndex));
	}
}
