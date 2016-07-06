package ws.daley.genealogy.gedcom.link;

import java.util.Vector;

import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.GcMultimediaLinkStructure;
import ws.daley.genealogy.gedcom.structure.GcMultimediaLinkXrefStructure;
import ws.daley.genealogy.gedcom.structure.Gc_CombinationStructure;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * MULTIMEDIA_LINK: = 
 * 
 *   [          embedded form
 *   n  OBJE @<XREF:OBJE>@  {1:1}
 *   |          linked form
 *   n  OBJE           {1:1}
 *     +1 FORM <MULTIMEDIA_FORMAT>  {1:1}
 *     +1 TITL <DESCRIPTIVE_TITLE>  {0:1}
 *     +1 FILE <MULTIMEDIA_FILE_REFERENCE>  {1:1}
 *     +1 <<NOTE_STRUCTURE>>  {0:M}
 *   ]
 * 
 * 
 * This structure provides two options in handling the GEDCOM multimedia interface. The first alternative (embedded)
 *  includes all of the data, including the multimedia object, within the transmission file. The embedded method includes
 *  pointers to GEDCOM records that contain encoded image or sound objects. Each record represents a multimedia object or
 *  object fragment. An object fragment is created by breaking the multimedia files into several multimedia object records
 *  of 32K or less. These fragments are tied together by chaining from one multimedia object fragment to the next in sequence.
 *  This procedure will help manage the size of a multimedia GEDCOM record so that existing systems which are not expecting
 *  large multimedia records may discard the records without crashing due to the size of the record. Systems which handle
 *  embedded multimedia can reconstitute the multimedia fragments by decoding the object fragments and concatenating them
 *  to the assigned multimedia file. 
 * 
 * The second method allows the GEDCOM context to be connected to an external multimedia file. This process is only managed
 * by GEDCOM in the sense that the appropriate file name is included in the GEDCOM file in context, but the maintenance and
 * transfer of the multimedia files are external to GEDCOM. 
 */

public class GcMultimediaLinkLink extends Gc_CombinationStructure
{
	public GcMultimediaLinkLink(GcBaseElement e, Vector<GcBaseElement> _vector)
	{
		try {
			this.structure = new GcMultimediaLinkXrefStructure(e, _vector);
			return;
		} catch(Throwable t)
		{
			@SuppressWarnings("unused")
            int i = 0;
		}
		this.structure = new GcMultimediaLinkStructure(e, _vector);
	}
};
