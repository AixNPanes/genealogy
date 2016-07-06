package ws.daley.genealogy.gedcom;

import org.apache.xerces.dom.NodeImpl;

public class GedRepositoryRecord extends GedID
{
	private String gedAddress = null;
	private String gedEMail = null;
	private String gedName = null;
	private String gedPhone = null;
	
	public GedRepositoryRecord(NodeImpl node)
    {
	    super(node);
    }

//    @Override
//    public void reorg()
//    {
//		if (this.reorganized)
//			return;
//	    super.reorg();
//	    GedAddress address = (GedAddress)getChildObject(this, "Address");
//	    if (address != null)
//	    	this.gedAddress = address.getAddress();
//	    GedEMail email = (GedEMail)getChildObject(this, "EMail");
//	    if (email != null)
//	    	this.gedEMail = email.getEMail();
//	    GedName name = (GedName)getChildObject(this, "Name");
//	    if (name != null)
//	    	this.gedName = name.getName();
//	    GedPhone phone = (GedPhone)getChildObject(this, "Phone");
//	    if (phone != null)
//	    	this.gedPhone = phone.getPhone();
//    }

    public String getGedAddress() {return this.gedAddress;}
    public String getGedEMail() {return this.gedEMail;}
    public String getGedName() {return this.gedName;}
    public String getGedPhone() {return this.gedPhone;}
}
