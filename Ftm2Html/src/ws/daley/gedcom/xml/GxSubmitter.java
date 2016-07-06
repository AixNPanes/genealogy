package ws.daley.gedcom.xml;

import ws.daley.gedcom.Link;
import ws.daley.gedcom.Submitter;
import ws.daley.genealogy.gedcom.xref.GcSubmitterXref;

public class GxSubmitter extends Submitter
{
//    @XmlElement(name = "Link", required = true)
//    protected Link link;

    public GxSubmitter() {}

	public GxSubmitter(GcSubmitterXref submitterXref)
	{
		this();
		
		if (submitterXref != null)
		{
			String xref = submitterXref.getParameters();
			if (xref != null && xref.length() != 0)
			{
				Link submitterLink = new Link();
				submitterLink.setTarget(xref);
				submitterLink.setRef("Submitter");
				this.setLink(submitterLink);
			}
		}
	}
	
	public static boolean isEmpty(Submitter submitter)
	{
		return submitter.getLink()!=null;
	}
}
