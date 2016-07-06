package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class SubSubLocale extends PlaceStructure implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	
	
	@Transient
	private static HashMap<String, SubSubLocale> subSubLocaleMap = new HashMap<String, SubSubLocale>();
	public static HashMap<String, SubSubLocale> getSubSubLocaleMap() {return subSubLocaleMap;}
	public void setSubSubLocaleMap(HashMap<String, SubSubLocale> map) {subSubLocaleMap = map;}
	 
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
	   
	@OneToOne
	private SubLocale subLocale;
	public SubLocale getSubLocale() {return this.subLocale;}
	public void setSubLocale(SubLocale subLocale) {this.subLocale = subLocale;}
	
	public SubSubLocale() {super();} 
	
	private SubSubLocale(Country localeCountry, State localeState, County localeCounty, City localeCity, Locale locale, SubLocale subLocale, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		super(name, PlaceType.TYPE.SUB_LOCALE, abbrev, aliases);
		if (name != null && name.length() > 0)
			subSubLocaleMap.put(name.toLowerCase(), this);
		if (abbrev != null && abbrev.length() > 0)
			subSubLocaleMap.put(abbrev.toLowerCase(), this);
		if (aliases != null)
			for(PlaceAlias alias:aliases)
				subSubLocaleMap.put(alias.getName().toLowerCase(), this);
		this.country = localeCountry;
		this.state = localeState;
		this.county = localeCounty;
		this.city = localeCity;
		this.locale = locale;
		this.subLocale = subLocale;
	}
	
	public static SubSubLocale getSubSubLocale(String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		SubSubLocale subSubLocale;
		if (name != null)
		{
			subSubLocale = subSubLocaleMap.get(name.toLowerCase());
			if (subSubLocale != null)
				return subSubLocale;
			}
		if (abbrev != null)
		{
			subSubLocale = subSubLocaleMap.get(abbrev.toLowerCase());
			if (subSubLocale != null)
				return subSubLocale;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				subSubLocale = subSubLocaleMap.get(alias.getAlias().toLowerCase());
				if (subSubLocale != null)
					return subSubLocale;
			}
		return null;
	}
	
	public static SubSubLocale getSubSubLocale(Country localeCountry, State localeState, County localeCounty, City localeCity, Locale locale, SubLocale subLocale, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		SubSubLocale subSubLocale = getSubSubLocale(name, abbrev, aliases);
		if (subSubLocale != null)
			return subSubLocale;
		return new SubSubLocale(localeCountry, localeState, localeCounty, localeCity, locale, subLocale, name, abbrev, aliases);
	}
}
