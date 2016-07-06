package ws.daley.genealogy.gedcom.object;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in HEAD
 * 
 * n DEST <RECEIVING_SYSTEM_NAME>					{0:1*}	
 */

public class GcDestinationLine extends GcBaseElement
{
	public String destination;

	public GcDestinationLine() {}
	
	public GcDestinationLine(GcBaseElement e)
	{
		super(e);
	}
	
	@Override
    public boolean interpret()
	{
		this.destination = getParameters();
		if (this.destination.length() == 0)
			return false;
		if (this.elements.size() > 0)
			return false;
		return true;
	}
};
