package ws.daley.genealogy.gedcom;

import java.util.Vector;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

//#include <windows.h>
//#include <crtdbg.h>

class IdList extends Vector<Long>
{
	private static final long serialVersionUID = 1L;

	public long GetSize(){return super.size();}
	public long Get(int i){return super.get(i-1).longValue();}
	
	public IdList()
	{
		super();
	}
	
	public long Add(long id)
	{
		super.add(id);
		return super.size();
	}
};
