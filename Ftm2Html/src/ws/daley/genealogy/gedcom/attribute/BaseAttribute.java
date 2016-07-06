package ws.daley.genealogy.gedcom.attribute;

import ws.daley.genealogy.gedcom.object.TokenRange;

public class BaseAttribute
{
	private String name = "";
	private TokenRange tokenRange = new TokenRange();
	
	public BaseAttribute() {}
	
	public BaseAttribute(BaseAttribute a)
	{
		this.name = a.name;
		this.tokenRange = a.tokenRange;
	}
	
	public BaseAttribute(String name, TokenRange tokenRange)
	{
		this.name = name;
		this.tokenRange = tokenRange;
	}
	
	public BaseAttribute(String name, int start, int end)
	{
		this.name = name;
		this.tokenRange = new TokenRange(start, end);
	}
	
	public String getName() {return this.name;}
	public TokenRange getTokenRange() {return this.tokenRange;}
}
