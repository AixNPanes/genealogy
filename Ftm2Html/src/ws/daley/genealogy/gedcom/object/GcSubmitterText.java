package ws.daley.genealogy.gedcom.object;

import java.util.Vector;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcSubmitterText extends GcBaseElement
{
	public String retrn = "";
	
	public GcSubmitterText() {}
	
	public GcSubmitterText(IGcBaseElement e)
	{
		super(e);
	}
	
	@Override
    public boolean interpret() {
		@SuppressWarnings("unused")
		Vector<GcBaseElement> newElements = new Vector<GcBaseElement>(this.elements.size());
		for(IGcBaseElement e:this.elements)
		{
			switch(e.getTag())
			{
				case TAG_UNDEFINED:
//					gcSource = new GcSource(e);
//					gcSource.interpret();
//					newElements.add(gcSource);
					break;
				default:
					return false;
			}
		}
		return true;
	}
};
