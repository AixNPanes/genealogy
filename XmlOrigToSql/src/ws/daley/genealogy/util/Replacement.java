package ws.daley.genealogy.util;

public class Replacement
{
	private String replacementRegex;
	private String replacementString;
	
	public Replacement(String regex, String replacement)
	{
		this.replacementRegex = regex;
		this.replacementString = replacement;
	}
	
	public String translatePattern(String str)
	{
		return str.replaceAll(this.replacementRegex, this.replacementString);
	}
}
