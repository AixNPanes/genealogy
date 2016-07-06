package ws.daley.gedcom;


class PlacePartHelper {
	static PlacePart createStreet(String value) {
		PlacePart street = new PlacePart();
		street.setType("Street");
		street.setLevel("6");
		street.setValue(value);
		return street;
	}

	static PlacePart createPostalCode(String value) {
		PlacePart postalCode = new PlacePart();
		postalCode.setType("PostalCode");
		postalCode.setLevel("5");
		postalCode.setValue(value);
		return postalCode;
	}

	static PlacePart createCity(String value) {
		PlacePart city = new PlacePart();
		city.setType("City");
		city.setLevel("4");
		city.setValue(value);
		return city;
	}

	static PlacePart createProvince(String value) {
		PlacePart province = new PlacePart();
		province.setType("Province");
		province.setLevel("2");
		province.setValue(value);
		return province;
	}

	static PlacePart createCountry(String value) {
		PlacePart country = new PlacePart();
		country.setType("Country");
		country.setLevel("1");
		country.setValue(value);
		return country;
	}

}
