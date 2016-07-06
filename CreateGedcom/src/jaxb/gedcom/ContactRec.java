package jaxb.gedcom;

import java.util.HashMap;



/**
 * A GEDCOM Contact record.
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class ContactRec extends ContactRecElement {
	private static HashMap<String, ContactRec> records = new HashMap<String, ContactRec>();

	/**
	 * Create a new ContactRec and record it in the in-memory database.
	 * @param key the id of the ContactRec being created.
	 * @see #getRecord(String)
	 */
	public ContactRec(String key) {
		super();
		super.setId(key);
		records.put(key, this);
	}

	/**
	 * Retrieve the ContactRec identified by key from the in-memory database.
	 * @param key the id of the ContactRec being retrieved
	 * @return the instance of ContactRec 
	 */
	static public ContactRec getRecord(String key) {
		return records.get(key);
	}

	/**
	 * Indicate that this contact is the same as an individual in the GEDCOM 
	 * @param indi
	 */
	public void setSameIndiv(IndividualRec indi) {
		LinkElement link = new LinkElement();
		link.setTarget("IndividualRec");
		link.setRef(indi);
		SameIndivElement sameIndi = new SameIndivElement();
		sameIndi.setLink(link);
		super.getSameIndiv().add(sameIndi);
	}

	/**
	 * If value is not null, set the Name of the contact
	 * @param value
	 */
	public void setName(String value) {
		if (value != null) {
			NameElement name = new NameElement();
			name.setValue(value);
			super.setName(name);
		}
	}

	/**
	 * If value is not null, add an e-mail address for the contact.
	 * @param value
	 */
	public void addEmail(String value) {
		if (value != null) {
			super.getEmail().add(value);
		}
	}

	/**
	 * If value is not null, add an URI (e.g. web site URL) for the contact.
	 * @param value
	 */
	public void addURI(String value) {
		if (value != null) {
			super.getURI().add(value);
		}
	}

	/**
	 * If value is not null, add a mailing address for the contact
	 * @param address
	 */
	public void addMailAddress(MailAddress address) {
		super.getMailAddress().add(address);
	}

	/**
	 * If value is not null, add a phone number for the contact
	 * @param value
	 * @param type the type of phone number (cell, work, home, ...)
	 */
	public void addPhone(String value, String type) {
		if (value != null) {
			PhoneElement phone = new PhoneElement();
			phone.setValue(value);
			phone.setType(type);
			super.getPhone().add(phone);
		}
	}
}
