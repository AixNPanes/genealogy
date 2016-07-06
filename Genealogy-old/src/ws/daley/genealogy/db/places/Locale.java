package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Locale extends PlaceStructure implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	
	
	@Transient
	private static HashMap<String, Locale> localeMap = new HashMap<String, Locale>();
	public static HashMap<String, Locale> getLocaleMap() {return localeMap;}
	public void setLocaleMap(HashMap<String, Locale> map) {localeMap = map;}
	 
	@OneToOne
	private Country country;
	public Country getCountry() {return this.country;}
	public void setCountry(Country country) {this.country = country;}
	   
	@OneToOne
	private State state;
	public State getState() {return this.state;}
	public void setState(State state) {this.state = state;}

	@OneToOne
	private County county;
	public County getCounty() {return this.county;}
	public void setCounty(County county) {this.county = county;}
	   
	@OneToOne
	private City city;
	public City getCity() {return this.city;}
	public void setCity(City city) {this.city = city;}
	
	public Locale() {super();} 
	
	private Locale(Country localeCountry, State localeState, County localeCounty, City localeCity, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		super(name, PlaceType.TYPE.LOCALE, abbrev, aliases);
		if (name != null && name.length() > 0)
			localeMap.put(name.toLowerCase(), this);
		if (abbrev != null && abbrev.length() > 0)
			localeMap.put(abbrev.toLowerCase(), this);
		if (aliases != null)
			for(PlaceAlias alias:aliases)
				localeMap.put(alias.getName().toLowerCase(), this);
		this.country = localeCountry;
		this.state = localeState;
		this.county = localeCounty;
		this.city = localeCity;
	}
	
	public static Locale getLocale(String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		Locale locale;
		if (name != null)
		{
			locale = localeMap.get(name.toLowerCase());
			if (locale != null)
				return locale;
			}
		if (abbrev != null)
		{
			locale = localeMap.get(abbrev.toLowerCase());
			if (locale != null)
				return locale;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				locale = localeMap.get(alias.getAlias().toLowerCase());
				if (locale != null)
					return locale;
			}
		return null;
	}
	
	public static Locale getLocale(Country localeCountry, State localeState, County localeCounty, City localeCity, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		Locale locale = getLocale(name, abbrev, aliases);
		if (locale != null)
			return locale;
		return new Locale(localeCountry, localeState, localeCounty, localeCity, name, abbrev, aliases);
	}
}
