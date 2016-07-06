package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class SubLocale extends PlaceStructure implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	
	
	@Transient
	private static HashMap<String, SubLocale> subLocaleMap = new HashMap<String, SubLocale>();
	public static HashMap<String, SubLocale> getSubLocaleMap() {return subLocaleMap;}
	public void setSubLocaleMap(HashMap<String, SubLocale> map) {subLocaleMap = map;}
	 
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
	   
	@OneToOne
	private Locale locale;
	public Locale getLocale() {return this.locale;}
	public void setLocale(Locale locale) {this.locale = locale;}
	
	public SubLocale() {super();} 
	
	private SubLocale(Country localeCountry, State localeState, County localeCounty, City localeCity, Locale locale, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		super(name, PlaceType.TYPE.SUB_LOCALE, abbrev, aliases);
		if (name != null && name.length() > 0)
			subLocaleMap.put(name.toLowerCase(), this);
		if (abbrev != null && abbrev.length() > 0)
			subLocaleMap.put(abbrev.toLowerCase(), this);
		if (aliases != null)
			for(PlaceAlias alias:aliases)
				subLocaleMap.put(alias.getName().toLowerCase(), this);
		this.country = localeCountry;
		this.state = localeState;
		this.county = localeCounty;
		this.city = localeCity;
		this.locale = locale;
	}
	
	public static SubLocale getSubLocale(String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		SubLocale subLocale;
		if (name != null)
		{
			subLocale = subLocaleMap.get(name.toLowerCase());
			if (subLocale != null)
				return subLocale;
			}
		if (abbrev != null)
		{
			subLocale = subLocaleMap.get(abbrev.toLowerCase());
			if (subLocale != null)
				return subLocale;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				subLocale = subLocaleMap.get(alias.getAlias().toLowerCase());
				if (subLocale != null)
					return subLocale;
			}
		return null;
	}
	
	public static SubLocale getSubLocale(Country localeCountry, State localeState, County localeCounty, City localeCity, Locale locale, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		SubLocale subLocale = getSubLocale(name, abbrev, aliases);
		if (subLocale != null)
			return subLocale;
		return new SubLocale(localeCountry, localeState, localeCounty, localeCity, locale, name, abbrev, aliases);
	}
}
