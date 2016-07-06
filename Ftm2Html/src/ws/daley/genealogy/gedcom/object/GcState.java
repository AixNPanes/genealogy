package ws.daley.genealogy.gedcom.object;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcState extends GcBaseElement
{
	public String state;

	public GcState() {}
	
	public GcState(GcBaseElement e)
	{
		super(e);
	}
	
	@Override
    public boolean interpret() {
		this.state = getParameters();
		if (this.state.length() == 0)
			return false;
		if (this.elements.size() > 0)
			return false;
		return true;
	}
};
