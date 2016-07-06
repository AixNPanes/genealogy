package ws.daley.genealogy.xml;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class XmlLinesSection
{
    private static final long serialVersionUID = 1L;
    
    private static final String tabs = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
	private XmlLine xmlLine = null;
	private ArrayList<XmlLinesSection> xmlLinesSection = new ArrayList<XmlLinesSection>();
	
	public XmlLinesSection() {}
	
	public XmlLinesSection(int levelno, String tag, String parms)
	{
		this(new XmlLine(levelno, tag, parms));
	}
	
	public XmlLinesSection(String s)
	{
		this(new XmlLine(s));
	}
	
	public XmlLinesSection(String s, int i)
	{
		this(new XmlLine(s, i));
	}
	
	public XmlLinesSection(Line line)
	{
		this(new XmlLine(line));
	}
	
	public XmlLinesSection(XmlLine line)
	{
		ArrayList<XmlLine> group = new ArrayList<XmlLine>(1);
		group.add(line);
		XmlLinesSection sect = new XmlLinesSection(group);
		this.xmlLine = sect.xmlLine;
		this.xmlLinesSection = sect.xmlLinesSection;
	}
	
	public XmlLinesSection(List<XmlLine> group)
	{
		if (group.size() == 0)
			return;
		if (group.size() == 1)
		{
			this.xmlLine = group.get(0);
			return;
		}
		ArrayList<Integer> level = getLevels(group);
		if (level.size() == 1)
		{
			this.xmlLine = group.get(0);
			if (group.size() > 1)
				this.xmlLinesSection = getSections(group.subList(1, group.size()));
		}
		else
		{
			if (group.size() > 0)
				this.xmlLinesSection = getSections(group);
		}
	}
	
	private ArrayList<XmlLinesSection> getSections(List<XmlLine> group)
	{
		ArrayList<XmlLinesSection> sections = new ArrayList<XmlLinesSection>();
		ArrayList<Integer> level = getLevels(group);
		for(int i = 0; i < level.size(); i++)
		{
			XmlLinesSection newSection = null;
			if (i < level.size()-1)
			{
				newSection = new XmlLinesSection(group.subList(level.get(i), level.get(i+1)));
			}
			else
			{
				newSection = new XmlLinesSection(group.subList(level.get(i), group.size()));
			}
			sections.add(newSection);
		}
		return sections;
	}
	
	private ArrayList<Integer> getLevels(List<XmlLine> group)
	{
		ArrayList<Integer> level = new ArrayList<Integer>();
		XmlLine line = group.get(0);
		level.add(0);
		for(int i = 1; i < group.size(); i++)
		{
			XmlLine curLine = group.get(i);
			if (curLine.getLevelNo() == line.getLevelNo())
			{
				line = curLine;
				level.add(i);
			}
		}
		return level;
	}
	
	public void generateXml(PrintStream ps, boolean checkLine)
	{
		if (checkLine)
		{
			if (this.xmlLine == null)
				throw new RuntimeException("XmlLinesSection missing line");
		}
		String leftTab = "";
		if (this.xmlLine != null)
		{
			leftTab = tabs.substring(0, this.xmlLine.getLevelNo()+1);	
			ps.print(leftTab+"<"+this.xmlLine.getTag()+(this.xmlLine.getLevelNo()==0?"Record":""));
			if (this.xmlLine.getId() != null && !"".equals(this.xmlLine.getId()))
				ps.print(" id=\""+this.xmlLine.getId()+"\"");
			if (this.xmlLine.getParameters() != null && !"".equals(this.xmlLine.getParameters()))
				ps.print(" value=\""+this.xmlLine.getParameters()+"\"");
			if (this.xmlLinesSection.size() != 0)
			{
				ps.println(">");
				for(XmlLinesSection section:this.xmlLinesSection)
					section.generateXml(ps, true);
				ps.println(leftTab+"</"+this.xmlLine.getTag()+(this.xmlLine.getLevelNo()==0?"Record":"")+">");
			}
			else
				ps.println("/>");
		}
		else
		{
			for(XmlLinesSection section:this.xmlLinesSection)
				section.generateXml(ps, true);
		}
	}
	
	public XmlLine getXmlLine() {return this.xmlLine;}
	public ArrayList<XmlLinesSection> getXmlLinesSection() {return this.xmlLinesSection;}
}
