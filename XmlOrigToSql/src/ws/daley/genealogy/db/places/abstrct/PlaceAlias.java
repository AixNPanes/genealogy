package ws.daley.genealogy.db.places.abstrct;

import java.util.List;
import java.util.TreeMap;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import ws.daley.genealogy.XmlOrigToSQL;
import ws.daley.genealogy.db.places.City;
import ws.daley.genealogy.db.places.CompositePlace;
import ws.daley.genealogy.db.places.Country;
import ws.daley.genealogy.db.places.County;
import ws.daley.genealogy.db.places.Locale;
import ws.daley.genealogy.db.places.Place;
import ws.daley.genealogy.db.places.State;
import ws.daley.genealogy.db.places.alias.CityAlias;
import ws.daley.genealogy.db.places.alias.CountryAlias;
import ws.daley.genealogy.db.places.alias.CountyAlias;
import ws.daley.genealogy.db.places.alias.LocaleAlias;
import ws.daley.genealogy.db.places.alias.StateAlias;

public abstract class PlaceAlias extends PlaceName
{
	public abstract PlaceStructure getPlaceStructure();
	public abstract void setPlaceStructure(PlaceStructure place);
	
	private static TreeMap<String, PlaceStructure> placeMap = new TreeMap<String, PlaceStructure>();
	public static TreeMap<String, PlaceStructure> getPlaceMap(){return placeMap;}
	public static void setPlaceMap(TreeMap<String, PlaceStructure> newPlaceMap) {placeMap = newPlaceMap;}
	public static void addPlaceMap(PlaceStructure placeStructure)
	{
		if (placeMap.get(placeStructure.getElement()) != null)
//			System.out.println("PlaceStructure map has element '" + placeStructure.element + "'");
			throw new RuntimeException("PlaceStructure map has element '" + placeStructure.getElement() + "'");
		placeMap.put(placeStructure.getElement(), placeStructure);
	}
	
    protected Place place;
    public Place getPlace() {return this.place;}
    public void setPlace(Place place) {this.place = place;}

	private PlaceType.TYPE type;
    public PlaceType.TYPE getType() {return this.type;}
    public void setType(PlaceType.TYPE type) {this.type = type;}

    public PlaceAlias() {}
	
	public PlaceAlias(PlaceType.TYPE type, String name, PlaceStructure place)
	{
		super(name);
		this.setType(type);
		this.setPlaceStructure(place);
		
		if (place == null)
			throw new RuntimeException("Place cannot be null");
	}
	
    public PlaceAlias(PlaceType.TYPE type, String name, String[] element)
	{
		super(name);
		this.setType(type);
		
		switch(type)
		{
			case LOCALE:
				List<LocaleAlias> localeAliases = null;
				List<Locale> locales = null;
				getItems("Locale", name, element, localeAliases, locales);
				break;
			case CITY:
				List<CityAlias> cityAliases = null;
				List<City> cities = null;
				getItems("City", name, element, cityAliases, cities);
				break;
			case COUNTY:
				List<CountyAlias> countyAliases = null;
				List<County> counties = null;
				getItems("County", name, element, countyAliases, counties);
				break;
			case STATE:
				List<StateAlias> stateAliases = null;
				List<State> states = null;
				getItems("XlsState", name, element, stateAliases, states);
				break;
			case COUNTRY:
				List<CountryAlias> countryAliases = null;
				List<Country> countries = null;
				getItems("XlsCountry", name, element, countryAliases, countries);
				break;
			case NONE:
				break;
		}
	}
	
	@SuppressWarnings("unchecked")
    private void getItems(String gettype, String name, String[] element, List<? extends PlaceAlias> aliases, List<? extends PlaceStructure> items)
	{
		try{aliases = XmlOrigToSQL.getEntityManager().createNamedQuery("get" + gettype + "Alias").setParameter("name", name).getResultList();}
		catch(PersistenceException e){}
		if (aliases != null && aliases.size() != 0)
			this.setPlaceStructure(aliases.get(0).getPlaceStructure());
		else
		{
			items = null;
			try{items = XmlOrigToSQL.getEntityManager().createNamedQuery("find" + gettype).setParameter("name", element[0]).getResultList();}
			catch(PersistenceException e){}
			if (items != null && items.size() != 0)
				this.setPlaceStructure(items.get(0));
		}
	}
	
    @SuppressWarnings("unchecked")
    public static PlaceStructure getPlace(String element)
	{
		String placeAliasSearch = "'" + CompositePlace.quote(element.toLowerCase()) + "'";
		Query q = XmlOrigToSQL.getEntityManager().createNamedQuery("select * from XlsState s " +
				"where " + placeAliasSearch);
		List<PlaceAlias> placeAliases = q.getResultList();
		if (placeAliases == null || placeAliases.size() == 0)
			return null;
		return placeAliases.get(0).getPlaceStructure();
	}
}
