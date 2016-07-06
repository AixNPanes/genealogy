package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class State extends PlaceStructure implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, State> stateMap = new HashMap<String, State>();
	public static HashMap<String, State> getStateMap() {return stateMap;}
	public void setStateMap(HashMap<String, State> map) {stateMap = map;}
	
	@OneToOne
	private Country country;
	public Country getCountry() {return this.country;}
	public void setCountry(Country country) {this.country = country;}
	
	public State() {super();} 
	
	private State(Country country, String name, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		super(name, PlaceType.TYPE.STATE, abbrev, aliases);
		if (name != null && name.length() > 0)
			stateMap.put(name.toLowerCase(), this);
		if (abbrev != null && abbrev.length() > 0)
			stateMap.put(abbrev.toLowerCase(), this);
		if (aliases != null)
			for(PlaceAlias alias:aliases)
				stateMap.put(alias.getName().toLowerCase(), this);
		this.country = country;
	}
	
	public static State getState(String stateName, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		State state;
		if (stateName != null)
		{
			state = stateMap.get(stateName.toLowerCase());
			if (state != null)
				return state;
			}
		if (abbrev != null)
		{
			state = stateMap.get(abbrev.toLowerCase());
			if (state != null)
				return state;
		}
		if (aliases != null)
			for(PlaceAlias alias:aliases)
			{
				state = stateMap.get(alias.getAlias().toLowerCase());
				if (state != null)
					return state;
			}
		return null;
	}
	
	public static State getState(Country country, String stateName, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		State state = getState(stateName, abbrev, aliases);
		if (state != null)
			return state;
		return new State(country, stateName, abbrev, aliases);
	}
}
