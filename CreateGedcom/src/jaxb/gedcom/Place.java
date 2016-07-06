package jaxb.gedcom;

import jaxb.gedcom.PlaceElement;
import jaxb.gedcom.PlaceNameElement;

/**
 * A GEDCOM Place
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class Place extends PlaceElement {

	private PlaceNameElement placeName = new PlaceNameElement();

	/**
	 * constructor
	 */
	public Place() {
		super();
		super.setPlaceName(placeName);
	}

	// public void addStreet(String value) {
	// if (value != null) {
	// super.getContent().add(PlacePartHelper.createStreet(value));
	// }
	// }
	//	
	// public void addPostalCode(String value) {
	// if (value != null) {
	// super.getContent().add(PlacePartHelper.createPostalCode(value));
	// }
	// }
	//	
	/**
	 * @param value the city of the place
	 */
	public void addCity(String value) {
		if (value != null) {
			placeName.getContent().add(PlacePartHelper.createCity(value));
		}
	}

	/**
	 * @param value the province/state of the place
	 */
	public void addProvince(String value) {
		if (value != null) {
			placeName.getContent().add(PlacePartHelper.createProvince(value));
		}
	}
	/**
	 * @param value the country of the place
	 */
	public void addCountry(String value) {
		if (value != null) {
			placeName.getContent().add(PlacePartHelper.createCountry(value));
		}
	}
}
