package ws.daley.genealogy.gedcom.object;

import java.util.Vector;

public class GcInputLine
{
	protected boolean eof = false;
	protected String lineText;
	protected int lineno = 1;
	protected int linenext = 0;
	protected int crlflen = 0;
	protected int levelno = -99;
	protected GcTags.TAG gcTag = GcTags.TAG.TAG_UNDEFINED;

	protected Vector<TokenRange> tokens = new Vector<TokenRange>();
	protected int nTokens = -1;
	protected TokenRange line = new TokenRange();
	protected TokenRange level = new TokenRange();
	protected TokenRange xref = new TokenRange();
	protected TokenRange link = new TokenRange();
	protected TokenRange tag = new TokenRange();
	protected TokenRange text = new TokenRange();
	

	public GcInputLine(String s)
	{
		initLine(s,0);
	}

	public GcInputLine(GcInputLine l)
	{
		initLine(l.lineText, l.linenext);
		this.lineno = l.lineno+1;
	}
	
	private void initLine(String s, int p)
	{
		if (p >= s.length()) {
			this.eof = true;
			return;
		}
		this.lineText = s;
		int indexlf = s.indexOf('\n', p);
		int indexcr = s.indexOf('\r', p);
		this.line.setLimits(p, indexlf<indexcr?indexlf:indexcr);
		int max = indexlf<indexcr?indexcr:indexlf;
		this.crlflen = (this.line.end+1)==max?2:1;
		this.linenext = this.line.end+this.crlflen;
		interpret();
	}
	
	public static Vector<GcBaseElement> getBaseElements(String s)
	{
		Vector<GcBaseElement> elements = new Vector<GcBaseElement>();
		GcInputLine line = new GcInputLine(s);
		GcBaseElement element = new GcBaseElement(line);
		while(!line.eof){
			elements.add(element);
			line = new GcInputLine(line);
			element = new GcBaseElement(line);
		}
		return elements;
	}
	
	public static Vector<GcBaseElement> getLevels(Vector<GcBaseElement> elements)
	{
		Vector<GcBaseElement> newElements = new Vector<GcBaseElement> ();
		if (elements.size() > 0)
		{
			int level = elements.get(0).getLevel();
			Vector<Integer> leveln = new Vector<Integer>();
			for(int i = 0; i < elements.size(); i++)
			{
				IGcBaseElement element = elements.get(i);
				int elementLevel = element.getLevel();
				if (elementLevel<level)
					level = elementLevel;
				if (elementLevel==level)
				{
					leveln.add(new Integer(i));
				}
			}
			leveln.add(new Integer(elements.size()));
			for(int i = 0; i < leveln.size()-1; i++)
			{
				Vector<GcBaseElement> subElements = new Vector<GcBaseElement> ();
				for(int j = leveln.get(i)+1; j < leveln.get(i+1); j++)
				{
					subElements.add(elements.get(j));
				}
				elements.get(leveln.get(i)).setElements(getLevels(subElements));
				newElements.add(elements.get(leveln.get(i)));
			}
		}
		return newElements;
	}
	
	public String getAToken(TokenRange t)
	{
		if (t == null)
			return "";
		return this.lineText.substring(t.start, t.end);
	}
	
	private TokenRange getToken(int i) {
		if (i >= this.nTokens || i < 0)
			return new TokenRange();
		return this.tokens.get(i);
	}
	
	private boolean isLink(String s) {
		return s.matches("^@[^@]+@$");
	}
	
	private boolean isLink(TokenRange t) {
		return isLink(getAToken(t));
	}
	
	private boolean isNumeric(String s) {
		try{Integer.parseInt(s); return true;}
		catch(Throwable t) {return false;}
	}
	
	private boolean isNumeric(TokenRange t) {
		return isNumeric(getAToken(t));
	}

	public GcTags.TAG getTag(String s) {
		return GcTags.getTag(s);
	}

	public GcTags.TAG getTag(TokenRange t) {
		return GcTags.getTag(getAToken(t));
	}
	
	private void interpret() {
		// Line formats are as follows(?):
		// level tag [linked | text]
		// level link tag [text]
		int levelToken=0, tagToken=-1, linkToken=-1, linkedToken=-1, textToken=-1;
		getTokens();
		this.nTokens = this.tokens.size();
		this.level = getToken(levelToken);
		if (!isNumeric(this.level))
			throw new RuntimeException("level is not numeric");
		this.levelno = Integer.parseInt(getAToken(this.level));
		switch(this.nTokens) {
			case 1:
				break;
			case 2:
				tagToken = 1;
				break;
			default:
				if (isLink(getToken(1))) {
					linkToken = 1;
					tagToken = 2;
					if (this.nTokens >= 3)
						textToken = 3;
				} else {
					tagToken = 1;
					if (isLink(getToken(2))) {
						linkedToken = 2;
						textToken = 3;
					} else
						textToken = 2;
				}
		}

		this.tag = getToken(tagToken);
		this.xref = getToken(linkToken);
		this.link = getToken(linkedToken);
		this.text = getToken(textToken);
		// but text spans all remaining tokens on line
		if (textToken == 2) {
			this.text.setEnd(this.line.end);
		}

		this.gcTag = getTag(this.tag);
	}
	
	public String getText()
	{
		if (this.text.start == this.lineText.length())
			return "";
		return this.lineText.substring(this.text.start, this.text.end);
	}
	
	public void getTokens()
	{
		this.tokens = new Vector<TokenRange>();
		int p = this.line.start;
		TokenRange token;
		while(p < this.line.end) {
			while(p < this.line.end && " \t".indexOf(this.lineText.charAt(p)) >= 0)
				p++;
			if (p < this.line.end)
			{
				token = new TokenRange(p, p);
				while(p < this.line.end && " \t".indexOf(this.lineText.charAt(p)) < 0)
					p++;
				token.setEnd(p);
				this.tokens.add(token);
			}
		}
	}
	
	public GcTags.TAG getTag() {return this.gcTag;}
	public int getLevel() {return this.levelno;}
	public int getLineno() {return this.lineno;}
	public TokenRange getParametersTokenRange() {return this.text;}
	public String getParameters() {return this.lineText.substring(this.text.start, this.text.end);}
	public String getXref() {return getAToken(this.xref);}
	public String getLink() {return getAToken(this.link);}
}
