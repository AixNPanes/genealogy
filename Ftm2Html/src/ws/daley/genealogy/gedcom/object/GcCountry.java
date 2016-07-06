package ws.daley.genealogy.gedcom.object;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcCountry extends GcBaseElement
{
	public String country;

	public GcCountry() {}
	
	public GcCountry(GcBaseElement e)
	{
		super(e);
	}
	
	@Override
    public boolean interpret()
	{
		this.country = getParameters();
		if (this.country.length() == 0)
			return false;
		if (this.elements.size() > 0)
			return false;
		return true;
	}
};
