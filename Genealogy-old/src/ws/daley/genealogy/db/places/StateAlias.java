package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class StateAlias extends PlaceAlias implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, StateAlias> stateAliasMap = new HashMap<String, StateAlias>();
	public static HashMap<String, StateAlias> getStateAliasMap() {return stateAliasMap;} 
	public static void setStateAliasMap(HashMap<String, StateAlias> map) {stateAliasMap = map;} 
	
	@OneToOne
	private Country country;
	public Country getCountry() {return this.country;}
	public void setCountry(Country country) {this.country = country;}
	
	public StateAlias() {super();}
	
	public StateAlias(String name, String alias, Country country)
	{
		super(name, PlaceType.TYPE.STATE, alias);
		if (name != null && name.length() > 0)
			stateAliasMap.put(name.toLowerCase(), this);
		if (alias != null && alias.length() > 0)
			stateAliasMap.put(alias.toLowerCase(), this);
		this.country = country;
	}
	
	public static StateAlias getStateAlias(String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		StateAlias stateAlias;
		if (name != null && name.length() > 0)
		{
			stateAlias = stateAliasMap.get(name.toLowerCase());
			if (stateAlias != null)
				return stateAlias;
		}
		if (abbrev != null && abbrev.length() > 0)
		{
			stateAlias = stateAliasMap.get(abbrev.toLowerCase());
			if (stateAlias != null)
				return stateAlias;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				stateAlias = stateAliasMap.get(alias.getAlias().toLowerCase());
				if (stateAlias != null)
					return stateAlias;
			}
		return null;
	}
}
