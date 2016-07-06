package ws.daley.genealogy.gedcom;

import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.GcTags;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcPhone extends GcBaseElement
{
	public String phone = new String();
	
	public String gcPhone;

	public GcPhone() {}
	
	public GcPhone(GcBaseElement e) {
		super(e);
	}
	
	@Override
    public boolean interpret()
	{
		this.gcPhone = getParameters();
		if (this.gcPhone.length() == 0)
			return false;
		if (this.elements.size() > 0)
			return false;
		return true;
	}

	public Boolean Import(GedcomFile file)
	{
		file.NextLine();
		if (!file.InterpretLine())
			return (Boolean.FALSE);
		while (file.GetLevel()>0)
		{
			if (file.GetTag() == GcTags.TAG.TAG_PHON)
			{
				String s = file.GetText();
				if (!s.matches(" *[(][0-9]{3}[)] *[0-9]{3} *- *[0-9]{3} *"))
					throw new RuntimeException("phone number error");
			}
			else
			{
				file.IllegalTag();
			}
			if (!file.InterpretLine())
				return (Boolean.FALSE);
		}
		return (Boolean.TRUE);
	}
};
