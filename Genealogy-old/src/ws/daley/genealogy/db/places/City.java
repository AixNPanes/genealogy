package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class City extends PlaceStructure implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	
	
	@Transient
	private static HashMap<String, City> cityMap = new HashMap<String, City>();
	public static HashMap<String, City> getCityMap() {return cityMap;}
	public void setCityMap(HashMap<String, City> map) {cityMap = map;}

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
	
	public City() {super();} 
	
	private City(Country cityCountry, State cityState, County cityCounty, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		super(name, PlaceType.TYPE.CITY, abbrev, aliases);
		if (name != null && name.length() > 0)
			cityMap.put(name.toLowerCase(), this);
		if (abbrev != null && abbrev.length() > 0)
			cityMap.put(abbrev.toLowerCase(), this);
		if (aliases != null)
			for(PlaceAlias alias:aliases)
				cityMap.put(alias.getName().toLowerCase(), this);
		this.country = cityCountry;
		this.state = cityState;
		this.county = cityCounty;
	}
	
	public static City getCity(String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		City country;
		if (name != null)
		{
			country = cityMap.get(name.toLowerCase());
			if (country != null)
				return country;
			}
		if (abbrev != null)
		{
			country = cityMap.get(abbrev.toLowerCase());
			if (country != null)
				return country;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				country = cityMap.get(alias.getAlias().toLowerCase());
				if (country != null)
					return country;
			}
		return null;
	}
	
	public static City getCity(Country country, State state, County county, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		City city = getCity(name, abbrev, aliases);
		if (city != null)
			return city;
		return new City(country, state, county, name, abbrev, aliases);
	}
}
