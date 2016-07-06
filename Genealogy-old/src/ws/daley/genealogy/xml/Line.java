package ws.daley.genealogy.xml;

public class Line
{
	private static String[] eol = {"\r\n", "\n\r", "\n", "\r"};
	private String buffer = null;
	private int beginIndex = 0;
	private int endIndex = 0;
	private int len = 0;
	private int crlnLen = 0;
	
	public Line()
	{
		this.buffer = "";
		this.beginIndex = 0;
		this.endIndex = 0;
		this.len = 0;
	}
	
	public Line(int levelno, String tag, String parameters)
	{
		this(""+levelno+" "+tag+" "+parameters);
	}
	
	public Line(String text)
	{
		this(text, 0);
	}
	
	public Line(String text, int fromIndex)
	{
		this();
		this.buffer = text;
		this.beginIndex = fromIndex;
		this.endIndex = text.length();
		this.len = this.endIndex - this.beginIndex;
		this.crlnLen = 0;
		for(String s:eol)
		{
			int nl = text.indexOf(s, fromIndex);
			if (nl != -1)
			{
				this.endIndex = nl;
				this.crlnLen = s.length();
				this.len = this.endIndex - this.beginIndex;
				return;
			}
		}
	}
	
	public int getBegin() {return this.beginIndex;}
	public int getEnd() {return this.endIndex;}
	public int getLen() {return this.len;}
	public int getCrlnLen() {return this.crlnLen;}
	public String getLine() {return this.buffer.substring(this.beginIndex, this.endIndex);}
}
