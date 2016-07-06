package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class County extends PlaceStructure implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	
	
	@Transient
	private static HashMap<String, County> countyMap = new HashMap<String, County>();
	public static HashMap<String, County> getCountyMap() {return countyMap;}
	public void setCountyMap(HashMap<String, County> map) {countyMap = map;}

	@OneToOne
	private Country country;
	public Country getCountry() {return this.country;}
	public void setCountry(Country country) {this.country = country;}

	@OneToOne
	private State state;
	public State getState() {return this.state;}
	public void setState(State state) {this.state = state;}

	public County() {super();}
	
	private County (Country countyCountry, State countyState, String countyName, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		super(countyName, PlaceType.TYPE.STATE, abbrev, aliases);
		if (countyName != null && countyName.length() > 0)
			countyMap.put(countyName.toLowerCase(), this);
		if (abbrev != null && abbrev.length() > 0)
			countyMap.put(abbrev.toLowerCase(), this);
		if (aliases != null)
			for(PlaceAlias alias:aliases)
				countyMap.put(alias.getName().toLowerCase(), this);
		this.country = countyCountry;
		this.state = countyState;
	}
	
	public static County getCounty(String countyName, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		County county;
		if (countyName != null)
		{
			county = countyMap.get(countyName.toLowerCase());
			if (county != null)
				return county;
			}
		if (abbrev != null)
		{
			county = countyMap.get(abbrev.toLowerCase());
			if (county != null)
				return county;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				county = countyMap.get(alias.getAlias().toLowerCase());
				if (county != null)
					return county;
			}
		return null;
	}
	
	public static County getCounty(Country country, State state, String countyName, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		County county = getCounty(countyName, abbrev, aliases);
		if (county != null)
			return county;
		return new County(country, state, countyName, abbrev, aliases);
	}
}
