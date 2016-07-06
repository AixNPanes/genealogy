package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcCertaintyAssessmentAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in SOURCE_CITATION
 * 
 * n QUAY <CERTAINTY_ASSESSMENT>					{0:1} 
 */

public class GcCertaintyAssessmentLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("CERTAINTY_ASSESSMENT", 0, 1, GcCertaintyAssessmentAttribute.class),
		});
	}

	public GcCertaintyAssessmentLine(GcBaseElement e)
	{
		super(e, "QUAY", getAttributeMap());
	}
};
