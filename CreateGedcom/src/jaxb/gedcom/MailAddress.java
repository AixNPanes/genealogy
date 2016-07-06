package jaxb.gedcom;

import jaxb.gedcom.AddrLineElement;
import jaxb.gedcom.MailAddressElement;

/**
 * A GEDCOM Mailing Address
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class MailAddress extends MailAddressElement {

	/**
	 * constructor
	 */
	public MailAddress() {
		super();
	}

	/**
	 * @deprecated
	 * @param value a generic address line
	 */
	@Deprecated
    public void addAddrLine(String value) {
		if (value != null) {
			AddrLineElement tmpAddrLine = new AddrLineElement();
			tmpAddrLine.getContent().add(value);
			super.getAddrLine().add(tmpAddrLine);
		}
	}

	/**
	 * @param value a street address line
	 */
	public void addStreet(String value) {
		if (value != null) {
			AddrLineElement tmpAddrLine = new AddrLineElement();
			tmpAddrLine.getContent().add(PlacePartHelper.createStreet(value));
			super.getAddrLine().add(tmpAddrLine);
		}
	}

	/**
	 * @param value a city address line
	 */
	public void addCity(String value) {
		if (value != null) {
			AddrLineElement tmpAddrLine = new AddrLineElement();
			tmpAddrLine.getContent().add(PlacePartHelper.createCity(value));
			super.getAddrLine().add(tmpAddrLine);
		}
	}

	/**
	 * @param value a province address line
	 */
	public void addProvince(String value) {
		if (value != null) {
			AddrLineElement tmpAddrLine = new AddrLineElement();
			tmpAddrLine.getContent().add(PlacePartHelper.createProvince(value));
			super.getAddrLine().add(tmpAddrLine);
		}
	}

	/**
	 * @param value a country address line
	 */
	public void addCountry(String value) {
		if (value != null) {
			AddrLineElement tmpAddrLine = new AddrLineElement();
			tmpAddrLine.getContent().add(PlacePartHelper.createCountry(value));
			super.getAddrLine().add(tmpAddrLine);
		}
	}

	/**
	 * @param value a postal code address line
	 */
	public void addPostalCode(String value) {
		if (value != null) {
			AddrLineElement tmpAddrLine = new AddrLineElement();
			tmpAddrLine.getContent().add(PlacePartHelper.createPostalCode(value));
			super.getAddrLine().add(tmpAddrLine);
		}
	}
}
