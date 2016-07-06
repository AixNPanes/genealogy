package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class CountryAlias extends PlaceAlias implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, CountryAlias> countryAliasMap = new HashMap<String, CountryAlias>();
	public static HashMap<String, CountryAlias> getCountryAliasMap() {return countryAliasMap;} 
	public static void setCountryAliasMap(HashMap<String, CountryAlias> map) {countryAliasMap = map;} 
	
	public CountryAlias() {super();}
	
	public CountryAlias(String name,  String alias)
	{
		super(name, PlaceType.TYPE.STATE, alias);
		if (name != null && name.length() > 0)
			countryAliasMap.put(name.toLowerCase(), this);
		if (alias != null && alias.length() > 0)
			countryAliasMap.put(alias.toLowerCase(), this);
	}
	
	public static CountryAlias getCountryAlias(String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		CountryAlias countryAlias;
		if (name != null && name.length() > 0)
		{
			countryAlias = countryAliasMap.get(name.toLowerCase());
			if (countryAlias != null)
				return countryAlias;
		}
		if (abbrev != null && abbrev.length() > 0)
		{
			countryAlias = countryAliasMap.get(abbrev.toLowerCase());
			if (countryAlias != null)
				return countryAlias;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				countryAlias = countryAliasMap.get(alias.getAlias().toLowerCase());
				if (countryAlias != null)
					return countryAlias;
			}
		return null;
	}
}
