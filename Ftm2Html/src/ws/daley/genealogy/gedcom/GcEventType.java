package ws.daley.genealogy.gedcom;

import java.util.Vector;

import ws.daley.genealogy.gedcom.object.GcTags;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcEventType
{
	public GcTags.TAG tag;
	public int roles;
	public STRING description = new STRING();

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

	public static int Find(Vector<GcEventType> list,GcTags.TAG tag,char[] descr)
	{
		for (int i=0;i<list.size();i++)
		{
			if (list.get(i).tag==tag && list.get(i).description.equals(descr))
				return (i+1);
		}
		return 0;
	}
};
