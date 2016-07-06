package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedSubmitter extends GedDocument
{
	private GedIndividualRecord submitter = null;
	
	public GedSubmitter(NodeImpl node)
    {
	    super(node);
    }
//
//    @Override
//    public void reorg()
//    {
//		if (this.reorganized)
//			return;
//	    super.reorg();
//	    String submitterId = getChildValue(this);
//	    getGedcomHTML().getIndividualRecords().get(submitterId);
//    }

    public GedIndividualRecord getSubmitter() {return this.submitter;}
}
