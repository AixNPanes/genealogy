package ws.daley.genealogy.gedcom.object;

public class TokenRange
{
	public int start=0;
	public int end=0;
	public int len=0;
	
	public TokenRange(){}
	
	public TokenRange(int start, int end)
	{
		setLimits(start, end);
	}
	
	public void setLimits(int start, int end)
	{
		this.start = start;
		setEnd(end);
	}
	
	public void setEnd(int end)
	{
		this.end = end;
		this.len = end - this.start;
	}
}

