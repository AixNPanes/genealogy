package jaxb.gedcom;

import jaxb.gedcom.NameElement;
import jaxb.gedcom.ProductElement;

/**
 * A GEDCOM Product
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class Product extends ProductElement {

	/**
	 * constructor
	 */
	public Product() {
		super();
	}

	/**
	 * @param value the name of the product
	 */
	public void setName(String value) {
		if (value != null) {
			NameElement name = new NameElement();
			name.setValue(value);
			super.setName(name);
		}
	}
}
