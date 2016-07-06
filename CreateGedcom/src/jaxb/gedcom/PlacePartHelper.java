package jaxb.gedcom;

import jaxb.gedcom.PlacePartElement;

class PlacePartHelper {
	static PlacePartElement createStreet(String value) {
		PlacePartElement street = new PlacePartElement();
		street.setType("Street");
		street.setLevel("6");
		street.setValue(value);
		return street;
	}

	static PlacePartElement createPostalCode(String value) {
		PlacePartElement postalCode = new PlacePartElement();
		postalCode.setType("PostalCode");
		postalCode.setLevel("5");
		postalCode.setValue(value);
		return postalCode;
	}

	static PlacePartElement createCity(String value) {
		PlacePartElement city = new PlacePartElement();
		city.setType("City");
		city.setLevel("4");
		city.setValue(value);
		return city;
	}

	static PlacePartElement createProvince(String value) {
		PlacePartElement province = new PlacePartElement();
		province.setType("Province");
		province.setLevel("2");
		province.setValue(value);
		return province;
	}

	static PlacePartElement createCountry(String value) {
		PlacePartElement country = new PlacePartElement();
		country.setType("Country");
		country.setLevel("1");
		country.setValue(value);
		return country;
	}

}
