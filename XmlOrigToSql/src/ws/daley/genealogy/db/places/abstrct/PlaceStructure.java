package ws.daley.genealogy.db.places.abstrct;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import ws.daley.genealogy.XmlOrigToSQL;
import ws.daley.genealogy.db.places.City;
import ws.daley.genealogy.db.places.Country;
import ws.daley.genealogy.db.places.County;
import ws.daley.genealogy.db.places.Locale;
import ws.daley.genealogy.db.places.PlaceURL;
import ws.daley.genealogy.db.places.State;
import ws.daley.genealogy.db.places.alias.CityAlias;
import ws.daley.genealogy.db.places.alias.CountryAlias;
import ws.daley.genealogy.db.places.alias.CountyAlias;
import ws.daley.genealogy.db.places.alias.LocaleAlias;
import ws.daley.genealogy.db.places.alias.StateAlias;
import ws.daley.genealogy.util.Util;

public abstract class PlaceStructure extends PlaceType
{
	public abstract PlaceStructure getParentPlaceStructure();
	public abstract void setParentPlaceStructure(PlaceStructure place);
	
	private static TreeMap<String, PlaceStructure> placeMap = new TreeMap<String, PlaceStructure>();
	public static TreeMap<String, PlaceStructure> getPlaceMap(){return placeMap;}
	public static void setPlaceMap(TreeMap<String, PlaceStructure> newPlaceMap) {placeMap = newPlaceMap;}
	public static void addPlaceMap(PlaceStructure placeStructure)
	{
		if (placeMap.get(placeStructure.element) != null)
//			System.out.println("PlaceStructure map has element '" + placeStructure.element + "'");
			throw new RuntimeException("PlaceStructure map has element '" + placeStructure.element + "'");
		placeMap.put(placeStructure.element, placeStructure);
	}
	
    private String abbrev = "";
    public String getAbbrev() {return this.abbrev;}
    public void setAbbrev(String abbrev) {this.abbrev = abbrev;}

	private String element = "";
    public String getElement() {return this.element;}
    public void setElement(String element) {this.element = element;}

	private List<PlaceURL> urls = new ArrayList<PlaceURL>();
	public List<PlaceURL> getUrls() {return this.urls;}
    public void setUrls(List<PlaceURL> urls) {this.urls = urls;}
    public void addUrl(PlaceURL url) {this.urls.add(url);}
	
	public PlaceStructure() {}
	
	public PlaceStructure(TYPE type, String[] name, String abbrev, PlaceStructure parentPlace, List<PlaceURL> urls)
	{
		super(type, name[name.length-(type==TYPE.COUNTRY?1:(type==TYPE.STATE?2:(type==TYPE.COUNTY?3:(type==TYPE.CITY?4:5))))]);
		this.abbrev = abbrev;
		String[] elementName = Util.trim(name);
		this.element = Util.join(elementName, ", ");
		if (type != TYPE.COUNTRY && parentPlace == null)
		{
			String[] parent = Util.subElement(elementName, 1, elementName.length);
			this.setParentPlaceStructure(PlaceStructure.getPlace(PlaceType.getParentType(type), parent));
		}
		else
			this.setParentPlaceStructure(parentPlace);
		if (urls == null)
			this.urls = new ArrayList<PlaceURL>();
		else
			this.urls = urls;
		addPlaceMap(this);
	}
	
	public void setRecord(PlaceStructure place)
	{
		if (place != null)
		{
			this.setName(place.getName());
			this.setType(place.getType());
			this.element = place.getElement();
			this.setId(place.getId());
			this.abbrev = place.getAbbrev();
		}
	}
    
    public static EntityManager getEntityManager() {return XmlOrigToSQL.getEntityManager();}
    
    public static PlaceStructure getPlace(String[] element)
	{
    	String el = Util.join(element, ", ");
    	PlaceStructure place = placeMap.get(el);
    	if (place != null)
    		return place;
    	switch(element.length)
    	{
    		case 1:
    			place = queryPlaces(PlaceType.TYPE.COUNTRY,
    					new TYPE[][]{{PlaceType.TYPE.COUNTRY}},
    					element);
    			if (place != null)
    				return place;
    			place = queryPlaces(PlaceType.TYPE.STATE,
    					new TYPE[][]{{PlaceType.TYPE.STATE}},
    					element);
    			if (place != null)
    				return place;
    			place = queryPlaces(PlaceType.TYPE.COUNTY,
    					new TYPE[][]{{PlaceType.TYPE.COUNTY}},
    					element);
    			if (place != null)
    				return place;
    			place = queryPlaces(PlaceType.TYPE.CITY,
    					new TYPE[][]{{PlaceType.TYPE.CITY}},
    					element);
    			if (place != null)
    				return place;
    			place = queryPlaces(PlaceType.TYPE.LOCALE,
    					new TYPE[][]{{PlaceType.TYPE.LOCALE}},
    					element);
    			if (place != null)
    				return place;
    			return null;
    		case 2:
    			place = queryPlaces(PlaceType.TYPE.STATE,
    					new TYPE[][]{{PlaceType.TYPE.STATE, PlaceType.TYPE.COUNTRY}},
    					element);
    			if (place != null)
    				return place;
    			place = queryPlaces(PlaceType.TYPE.COUNTY,
    					new TYPE[][]{{PlaceType.TYPE.COUNTY, PlaceType.TYPE.COUNTRY},
    						{PlaceType.TYPE.COUNTY, PlaceType.TYPE.STATE}},
    					element);
    			if (place != null)
    				return place;
    			place = queryPlaces(PlaceType.TYPE.CITY,
    					new TYPE[][]{{PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTRY},
    						{PlaceType.TYPE.CITY, PlaceType.TYPE.STATE},
    						{PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTY}},
    					element);
    			if (place != null)
    				return place;
    			break;
    		case 3:
    			place = queryPlaces(PlaceType.TYPE.COUNTY,
    					new TYPE[][]{{PlaceType.TYPE.COUNTY, PlaceType.TYPE.STATE, PlaceType.TYPE.COUNTRY, }},
    					element);
    			if (place != null)
    				return place;
    			place = queryPlaces(PlaceType.TYPE.CITY,
    					new TYPE[][]{{PlaceType.TYPE.CITY, PlaceType.TYPE.STATE, PlaceType.TYPE.COUNTRY},
    						{PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTY, PlaceType.TYPE.COUNTRY}, 
    						{PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTY, PlaceType.TYPE.STATE}},
    					element);
    			if (place != null)
    				return place;
    			place = queryPlaces(PlaceType.TYPE.LOCALE,
    					new TYPE[][]{{PlaceType.TYPE.LOCALE, PlaceType.TYPE.STATE, PlaceType.TYPE.COUNTRY},
    						{PlaceType.TYPE.LOCALE, PlaceType.TYPE.COUNTY, PlaceType.TYPE.COUNTRY},
    						{PlaceType.TYPE.LOCALE, PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTRY},
    						{PlaceType.TYPE.LOCALE, PlaceType.TYPE.COUNTY, PlaceType.TYPE.STATE},
    						{PlaceType.TYPE.LOCALE, PlaceType.TYPE.CITY, PlaceType.TYPE.STATE},
    						{PlaceType.TYPE.LOCALE, PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTY}},
    					element);
    			if (place != null)
    				return place;
    			break;
    		case 4:
    			place = queryPlaces(PlaceType.TYPE.CITY,
    					new TYPE[][]{{PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTY, PlaceType.TYPE.STATE, PlaceType.TYPE.COUNTRY}},
    					element);
    			if (place != null)
    				return place;
    			place = queryPlaces(PlaceType.TYPE.LOCALE,
    					new TYPE[][]{
    						{PlaceType.TYPE.LOCALE, PlaceType.TYPE.COUNTY, PlaceType.TYPE.STATE, PlaceType.TYPE.COUNTRY},
    						{PlaceType.TYPE.LOCALE, PlaceType.TYPE.CITY, PlaceType.TYPE.STATE, PlaceType.TYPE.COUNTRY},
    						{PlaceType.TYPE.LOCALE, PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTY, PlaceType.TYPE.COUNTRY},
    						{PlaceType.TYPE.LOCALE, PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTY, PlaceType.TYPE.STATE}},
    					element);
    			if (place != null)
    				return place;
    			break;
    		case 5:
    			place = queryPlaces(PlaceType.TYPE.LOCALE,
    					new TYPE[][]{{PlaceType.TYPE.LOCALE, PlaceType.TYPE.CITY, PlaceType.TYPE.COUNTY, PlaceType.TYPE.STATE, PlaceType.TYPE.COUNTRY}},
    					element);
    			if (place != null)
    				return place;
    			break;
    	}
    	return place;
	}
    
    private static PlaceStructure queryPlaces(PlaceType.TYPE type, PlaceType.TYPE[][] types, String[] element)
    {
    	PlaceStructure place = null;
    	for(TYPE[] queryTypes:types)
		{
			place = getPlace(type, queryTypes, element);
			if (place != null)
				return place;
		}
    	return null;
    }
    
    private static String getQueryName(TYPE[] types)
	{
    	String name = "";
    	for(TYPE type:types)
    	{
    		if (name.length() > 0)
    			name += "And";
    		name += PlaceType.getName(type);
    	}
    	return name;
	}
    
    private static Query getQualifiedQuery(Query query, TYPE[] types, String[] element)
    {
    	Query q = query;
    	for(int i = 0; i < types.length; i++)
    		q = q.setParameter(PlaceType.getName(types[i]).toLowerCase(), element[i]);
    	return q;
    }
    
    @SuppressWarnings("unchecked")
    private static PlaceStructure getPlaceAlias(PlaceType.TYPE type, String[] element)
    {
       	List<PlaceAlias> aliases = null;
       	String typeName = PlaceType.getName(type);
       	String queryName = "get"+typeName+"Alias";
       	String el = Util.join(element, ", ");
       	try{aliases = getEntityManager().createNamedQuery(queryName).setParameter("name", el).getResultList();    	}
       	catch(PersistenceException e) {}
       	catch(Throwable t)
       	{
       		int i = 0;
       	}
       	if (aliases != null && aliases.size()>0)
       		return aliases.get(0).getPlaceStructure();
    	return null;
    }
    
    @SuppressWarnings("unchecked")
    private static PlaceStructure findPlace(PlaceType.TYPE type, TYPE[] types, String[] element)
    {
		List<PlaceStructure> places = null;
       	String queryName = "find"+PlaceType.getName(type)+"From"+getQueryName(types);
    	try{Query q = getQualifiedQuery(getEntityManager().createNamedQuery(queryName), types, element);
    		places = q.getResultList();
    	}
    	catch(IllegalArgumentException iae)
    	{
    		System.out.println(iae.getMessage());
    		int i = 0;
    	}
    	catch(Throwable t)
    	{
    		int i = 0;
    	}
		if (places != null && places.size() > 0)
			return places.get(0);
		return null;
    }
    
    private static PlaceStructure getPlace(PlaceType.TYPE type, TYPE[] types, String[] element)
    {
    	PlaceStructure place = getPlaceAlias(type, element);
    	if (place != null)
    		return place;
    	return findPlace(type, types, element);
    }
    
    @SuppressWarnings("unchecked")
    public static PlaceStructure getPlace(PlaceType.TYPE type, String[] element)
	{
    	try{
	    	List<PlaceAlias> aliases = null;
	    	String typeName = PlaceType.getName(type);
	    	String queryName = "get"+typeName+"Alias";
	    	String findName = "find"+typeName;
	    	int placeOrdinal = PlaceType.getOrdinal(type);
	    	int elementNumber = element.length - placeOrdinal;
	    	String el = element[elementNumber];
	    	String nameString = Util.join(element, ", ");
	    	try{aliases = getEntityManager().createNamedQuery(queryName).setParameter("name", el).getResultList();}
	    	catch(ArrayIndexOutOfBoundsException aiobe)
	    	{
	    		throw aiobe;
	    	}
	    	catch(Throwable e) {
	    		int i = 0;
	    	}
			if (aliases != null && aliases.size() > 0)
				return aliases.get(0).getPlaceStructure();
			List<PlaceStructure> places = null;
//	    	try{places = getEntityManager().createNamedQuery("find"+queryName).setParameter("name", element[element.length - PlaceType.getOrdinal(type)]).getResultList();}
	    	try{places = getEntityManager().createNamedQuery(findName).setParameter("name", nameString).getResultList();}
	    	catch(ArrayIndexOutOfBoundsException aiobe) {throw aiobe;}
	    	catch(Throwable e) {
	    		int i = 0;
	    	}
			if (places != null && places.size() > 0)
				return places.get(0);
			return null;
    	}
    	catch(ArrayIndexOutOfBoundsException aiobe) {throw aiobe;}
    	catch(Throwable e)
    	{
    		return null;
    	}
	}
    
    @SuppressWarnings("unchecked")
    public static void persistAliases(EntityManager em, String queryName)
    {
    	List<PlaceStructure> placeList = null;
    	try{placeList = getEntityManager().createNamedQuery("get"+queryName).getResultList();}
    	catch(PersistenceException e)
    	{
    		e.printStackTrace();
    	}
    	
//		XmlOrigToSQL.getEntityManager().getTransaction().begin();
    	if (placeList != null && placeList.size() > 0)
	    	for(PlaceStructure place:placeList)
	    	{
	    		switch(place.getType())
	    		{
	    			case COUNTRY:
	    				if (place.getName() != null && place.getName().length() != 0)
	    					em.persist(new CountryAlias(place.getName(), (Country)place));
	    				if (place.getAbbrev() != null && place.getAbbrev().length() != 0)
	    					em.persist(new CountryAlias(place.getAbbrev(), (Country)place));
	    				if (place.getElement() != null && place.getElement().length() != 0)
	    					em.persist(new CountryAlias(place.getElement(), (Country)place));
	    				break;
	    			case STATE:
	    				if (place.getName() != null && place.getName().length() != 0)
	    					em.persist(new StateAlias(place.getName(), (State)place));
	    				if (place.getAbbrev() != null && place.getAbbrev().length() != 0)
	    					em.persist(new StateAlias(place.getAbbrev(), (State)place));
	    				if (place.getElement() != null && place.getElement().length() != 0)
	    					em.persist(new StateAlias(place.getElement(), (State)place));
	    				break;
	    			case COUNTY:
	    				if (place.getName() != null && place.getName().length() != 0)
	    					em.persist(new CountyAlias(place.getName(), (County)place));
	    				if (place.getAbbrev() != null && place.getAbbrev().length() != 0)
	    					em.persist(new CountyAlias(place.getAbbrev(), (County)place));
	    				if (place.getElement() != null && place.getElement().length() != 0)
	    					em.persist(new CountyAlias(place.getElement(), (County)place));
	    				break;
	    			case CITY:
	    				if (place.getName() != null && place.getName().length() != 0)
	    					em.persist(new CityAlias(place.getName(), (City)place));
	    				if (place.getAbbrev() != null && place.getAbbrev().length() != 0)
	    					em.persist(new CityAlias(place.getAbbrev(), (City)place));
	    				if (place.getElement() != null && place.getElement().length() != 0)
	    					em.persist(new CityAlias(place.getElement(), (City)place));
	    				break;
	    			case LOCALE:
	    				if (place.getName() != null && place.getName().length() != 0)
	    					em.persist(new LocaleAlias(place.getName(), (Locale)place));
	    				if (place.getAbbrev() != null && place.getAbbrev().length() != 0)
	    					em.persist(new LocaleAlias(place.getAbbrev(), (Locale)place));
	    				if (place.getElement() != null && place.getElement().length() != 0)
	    					em.persist(new LocaleAlias(place.getElement(), (Locale)place));
	    				break;
	    		}
	    	}
//		XmlOrigToSQL.getEntityManager().getTransaction().commit();
    }
    
    public static void persist(EntityManager em, PlaceStructure[] places)
    {
    	for(PlaceStructure place:places)
    		em.persist(place);
    }
}
