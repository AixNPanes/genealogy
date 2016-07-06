package ws.daley.genealogy.gedcom;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

//#include <windows.h>
//#include <crtdbg.h>

public class IdTable
{
	private String s;
	
	public long GetSize(){return this.s.length();}
	public char[] Get(int i){return this.s.substring(i-1).toCharArray();}
	
	public IdTable()
	{
		this.s = "";
	}
	
	public long Add(char str)
	{
		this.s += str;
		return GetSize();
	}
	
	public long Add(char[] str)
	{
		this.s += new String(str);
		return GetSize();
	}
	
	public long Find(char[] str)
	{
		return (this.s.indexOf(new String(str))+1);
	}
};
