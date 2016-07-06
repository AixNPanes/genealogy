package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.line.GcFormLine;
import ws.daley.genealogy.gedcom.line.GcTimeLine;
import ws.daley.genealogy.gedcom.line.GcVersionLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in HEADER
 *  
 * n GEDC											{1:1} 
 *   +1 VERS <VERSION_NUMBER>						{1:1}	
 *   +1 FORM <GEDCOM_FORM>						{1:1}	
 */

public class GcGEDCStructure extends Gc_Structure
{
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
			new TagDescriptor("VERS", 1, 1, GcVersionLine.class),
			new TagDescriptor("FORM", 0, 1, GcFormLine.class)
		});
	}
	
	public GcGEDCStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "GEDC", null, tagDescriptorMap, _vector);
	}

	public String getVersion() {return getParameterForKey("VERS");}
	public String getForm() {return getParameterForKey("FORM");}
};
