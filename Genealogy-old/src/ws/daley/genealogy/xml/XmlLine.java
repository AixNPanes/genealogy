package ws.daley.genealogy.xml;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import ws.daley.genealogy.util.MultipleReplacement;
import ws.daley.genealogy.util.Replacement;

public class XmlLine
{
	private static Pattern p = Pattern.compile("^(\\d+)(\\s+((\\S+)(\\s+(\\S.*)?)?))$");
	private static Pattern linkPattern = Pattern.compile("^@[^@]+@$");
	private static MultipleReplacement replacements = new MultipleReplacement(new Replacement[] {
			new Replacement("<", "&lt;"),	
			new Replacement(">", "&gt;"),	
			new Replacement("\"", "&quot;"),	
			new Replacement("'", "&#39;"),	
			new Replacement("&", "&amp;"),	
			});
	private int levelNo = 0;
	private String tag = null;
	private String id = null;
	private String parameters = null;
	
	public XmlLine(String parms)
	{
		this(parms, 0);
	}
	
	public XmlLine(String parms, int ind)
	{
		this(new Line(parms, ind));
	}
	
	public XmlLine(int level, String tag, String parms)
	{
		this(new Line(level, tag, parms));
	}
	
	public XmlLine(Line line)
	{
		this(line, false);
	}
	
	public XmlLine(Line line, boolean translate)
	{
		String lineText = line.getLine().trim();
		Matcher m = p.matcher(lineText);
		boolean b = m.matches();
		if (!b)
			throw new RuntimeException("no match");
		this.levelNo = Integer.parseInt(m.group(1));
		this.tag = m.group(4);
		if (this.tag != null)
		{
			Matcher linkMatcher = linkPattern.matcher(this.tag);
			if (linkMatcher.matches())
			{
				this.id = this.tag;
				this.tag = m.group(6);
			}
			else
				this.parameters = m.group(6);
		}
		else
		{
			this.parameters = m.group(3);
		}
		if (translate && this.parameters != null && this.parameters.length() > 0)
			this.parameters = replacements.translatePatterns(this.parameters);
		if ("CONT".equals(this.tag))
		{
			this.parameters = "\n"+this.parameters;
			this.tag = "CONC";
		}
	}
	
	public int getLevelNo() {return this.levelNo;}
	public String getTag() {return this.tag;}
	public String getId() {return this.id;}
	public String getParameters() {return this.parameters;}
}
