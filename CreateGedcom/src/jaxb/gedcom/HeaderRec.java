package jaxb.gedcom;

import jaxb.gedcom.HeaderRecElement;
import jaxb.gedcom.LinkElement;
import jaxb.gedcom.SubmitterElement;

/**
 * A GEDCOM Header record.
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class HeaderRec extends HeaderRecElement {

	/**
	 * constructor
	 */
	public HeaderRec() {
		super();
	}

	/**
	 * Set the reference to the contact record that contains the
	 * contact information for the contact who created
	 * the GEDCOM.
	 * <p>
	 * <i>IMPORTANT</i> This ContactRec must have already been added to the
 	 * GEDCOM earlier via the GEDCOM.addContactRec(ContactRec) method.
	 * @param contact 
 	 *  
	 * @see GEDCOM#setSubmitter(ContactRec)
	 * @see GEDCOM#addContactRec(ContactRec)
	 */
	public void setSubmitter(ContactRec contact) {
		SubmitterElement submitter = new SubmitterElement();
		LinkElement link = new LinkElement();
		link.setTarget("ContactRec");
		link.setRef(contact);
		submitter.setLink(link);
		super.setSubmitter(submitter);
	}
}
