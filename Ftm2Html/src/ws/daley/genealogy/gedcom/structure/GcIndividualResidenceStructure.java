package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.gedcom.attribute.individual.Gc_AttributeIndividual;
import ws.daley.genealogy.gedcom.object.GcBaseElement;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * n RESI 											{1:1} 
 * 	+1 <<EVENT_DETAIL>>								{0:1}	p.33 
 */

public class GcIndividualResidenceStructure extends Gc_AttributeIndividual
{
	public GcIndividualResidenceStructure() {}
	
	public GcIndividualResidenceStructure(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		super(e, "RESI", null, null, _vector);
	}
};
