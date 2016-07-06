package ws.daley.genealogy.gedcom.line;

import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcReceivingSystemNameAttribute;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in HEAD 
 *  n DEST <RECEIVING_SYSTEM_NAME>					{0:1*}	
 * 
 * NOTE: 
 * 	Submissions to the Family History Department for Ancestral File
 * 	submission or for clearing temple ordinances  must use a
 * 	DESTination of ANSTFILE or TempleReady.  
 */

public class GcDestinationLine extends Gc_Line
{
	private static AttributeDescriptorMap getAttributeMap()
	{
		return AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("RECEIVING_SYSTEM_NAME", 0, 1, GcReceivingSystemNameAttribute.class),
		});
	}
	
	public GcDestinationLine(GcBaseElement e)
	{
		super(e, "DEST", getAttributeMap());
	}
};
