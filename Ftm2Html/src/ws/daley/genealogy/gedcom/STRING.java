package ws.daley.genealogy.gedcom;
/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

//#include <windows.h>
//#include <crtdbg.h>

public class STRING
{
	private String s;

	public STRING()
	{
		this.s = "";
	}

	public STRING(char[] text)
	{
		this.s = new String(text);
		trimToLength();
	}

	public STRING(String text)
	{
		this.s = text;
		trimToLength();
	}

	public String Get()
	{
		return (this.s!=null)?this.s:"";
	}

	public long Size()
	{
		return this.s.length();
	}

	public void Add(STRING string)
	{
		this.s += string.s;
		trimToLength();
	}

	public void Add(char[] string)
	{
		this.s += new String(string);
		trimToLength();
	}

	public void Add(char c)
	{
		this.s += new String(new char[] {c});
		trimToLength();
	}

	public void Add(String string)
	{
		this.s += string;
		trimToLength();
	}

	public void Add(char[] string,@SuppressWarnings("unused") int length)
	{
		Add(string);
	}

	public void Set(char[] text)
	{
		this.s = new String(text);
		trimToLength();
	}

	public void Set(char[] text,@SuppressWarnings("unused") int length)
	{
		Set(text);
	}

	//Returns the length of the string
	public String Get(int len)
	{
		int l = len>this.s.length()?this.s.length():len;
		return (new String(this.s)).substring(0, l);
	}

	// Converts the string to a valid character data XML string.
	public STRING ConvertToXML()
	{
		STRING text = new STRING(new char[]{});
		
		for (int i=0;i<this.s.length();i++)
		{
			if (this.s.charAt(i)=='<')
				text.Add("&lt;");
			else if (this.s.charAt(i)=='&')
				text.Add("&amp;");
			else if (this.s.charAt(i)=='>')
				text.Add("&gt;");
			else if (this.s.charAt(i)=='\"')
				text.Add("&quot;");
			else if (this.s.charAt(i)=='\'')
				text.Add("&apos;");
			else
				text.Add(this.s.charAt(i));
		}
		return text;
	}
	
	public boolean equals(STRING str)
	{
		return this.s.equals(str.s);
	}
	
	public boolean equals(String str)
	{
		return this.s.equals(str);
	}
	
	public boolean equals(char[] str)
	{
		return this.s.equals(new String(str));
	}
	
	private void trimToLength()
	{
		int i = this.s.indexOf('\0');
		if (i >=0)
			this.s = this.s.substring(0, i);
	}
};
