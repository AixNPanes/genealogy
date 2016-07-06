package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class CountyAlias extends PlaceAlias implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, CountyAlias> countyAliasMap = new HashMap<String, CountyAlias>();
	public static HashMap<String, CountyAlias> getCountyAliasMap() {return countyAliasMap;} 
	public static void setCountyAliasMap(HashMap<String, CountyAlias> map) {countyAliasMap = map;} 
	
	@OneToOne
	private Country country;
	public Country getCountry() {return this.country;}
	public void setCountry(Country country) {this.country = country;}
	
	@OneToOne
	private State state;
	public State getState() {return this.state;}
	public void setState(State state) {this.state = state;}
	
	public CountyAlias() {super();}
	
	public CountyAlias(String name, String alias, Country country, State state)
	{
		super(name, PlaceType.TYPE.COUNTY, alias);
		if (name != null && name.length() > 0)
			countyAliasMap.put(name.toLowerCase(), this);
		if (alias != null && alias.length() > 0)
			countyAliasMap.put(alias.toLowerCase(), this);
		this.country = country;
		this.state = state;
	}
	
	public static CountyAlias getCountyAlias(String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		CountyAlias countyAlias;
		if (name != null && name.length() > 0)
		{
			countyAlias = countyAliasMap.get(name.toLowerCase());
			if (countyAlias != null)
				return countyAlias;
		}
		if (abbrev != null && abbrev.length() > 0)
		{
			countyAlias = countyAliasMap.get(abbrev.toLowerCase());
			if (countyAlias != null)
				return countyAlias;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				countyAlias = countyAliasMap.get(alias.getAlias().toLowerCase());
				if (countyAlias != null)
					return countyAlias;
			}
		return null;
	}
}
