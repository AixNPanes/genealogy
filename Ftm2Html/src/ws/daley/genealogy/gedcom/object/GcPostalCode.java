package ws.daley.genealogy.gedcom.object;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcPostalCode extends GcBaseElement
{
	public String postalCode;

	public GcPostalCode() {}
	
	public GcPostalCode(GcBaseElement e)
	{
		super(e);
	}
	
	@Override
    public boolean interpret()
	{
		this.postalCode = getParameters();
		if (this.postalCode.length() == 0)
			return false;
		if (this.elements.size() > 0)
			return false;
		return true;
	}
};
