package jaxb.gedcom;

import jaxb.gedcom.LinkElement;
import jaxb.gedcom.SupplierElement;

/**
 * A GEDCOM Supplier
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class Supplier extends SupplierElement {

	/**
	 * constructor
	 */
	public Supplier() {
		super();
	}

	/**
	 * The contact record for this supplier.  Note the contact record must be added
	 * to the GEDCOM.
	 * @param cr
	 */
	public void setLink(ContactRec cr) {
		LinkElement link = new LinkElement();
		link.setTarget("ContactRec");
		link.setRef(cr);
		super.setLink(link);
	}
}
