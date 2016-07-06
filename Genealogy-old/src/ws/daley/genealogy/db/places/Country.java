package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Country extends PlaceStructure implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, Country> countryMap = new HashMap<String, Country>();
	public static HashMap<String, Country> getCountryMap() {return countryMap;}
	public static void setCountryMap(HashMap<String, Country> map) {countryMap = map;}
	
	public Country() {super();}
	
	public Country(String name) {super(name, PlaceType.TYPE.COUNTRY, null, null);}
	
	private Country(String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		super(name, PlaceType.TYPE.COUNTRY, abbrev, aliases);
		if (name != null && name.length() > 0)
			countryMap.put(name.toLowerCase(), this);
		if (abbrev != null && abbrev.length() > 0)
			countryMap.put(abbrev.toLowerCase(), this);
		if (aliases != null)
			for(PlaceAlias alias:aliases)
				countryMap.put(alias.getName().toLowerCase(), this);
	}
	
	public static Country getCountry(String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		Country country;
		if (name != null)
		{
			country = countryMap.get(name.toLowerCase());
			if (country != null)
				return country;
			}
		if (abbrev != null)
		{
			country = countryMap.get(abbrev.toLowerCase());
			if (country != null)
				return country;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				country = countryMap.get(alias.getAlias().toLowerCase());
				if (country != null)
					return country;
			}
		return null;
	}
	
	public static Country getCountry(@SuppressWarnings("unused") Place place, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		Country country = getCountry(name, abbrev, aliases);
		if (country != null)
			return country;
		CountryAlias alias = CountryAlias.getCountryAlias(name, abbrev, aliases);
		if (alias != null)
			return new Country(alias.getName(), alias.getAlias(), aliases);
		return new Country(name, abbrev, aliases);
	}
}
