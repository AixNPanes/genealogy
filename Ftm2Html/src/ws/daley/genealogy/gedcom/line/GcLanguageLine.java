package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcLanguageOfTextAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in HEAD
 *     +1 LANG <LANGUAGE_OF_TEXT>						{0:1}	
 */

public class GcLanguageLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("LANGUAGE_OF_TEXT", 0, 1, GcLanguageOfTextAttribute.class)
		});
	}
	
	public GcLanguageLine(GcBaseElement e)
	{
		super(e, "LANG", getAttributeMap());
	}
};
