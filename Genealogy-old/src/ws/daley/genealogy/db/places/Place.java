package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ws.daley.genealogy.util.Util;

@Entity
public class Place extends PlaceType implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;

	@Transient
	private static TreeMap<String, Place> placeMap = new TreeMap<String, Place>();
    public static TreeMap<String, Place> getPlaceMap() {return placeMap;}
    public static void setPlaceMap(TreeMap<String, Place> placeMap) {Place.placeMap = placeMap;}
	
    @Transient
    private String parentName;
    public String getParentName() {return this.parentName;}
    public void setParentName(String parentName) {this.parentName = parentName;}
    
    @Transient
    private Place parent;
    public Place getParent() {return this.parent;}
    public void setParent(Place parent) {this.parent = parent;}

    @Transient
	private String[] element;
    public String[] getElement() {return this.element;}
    public void setElement(String[] element) {this.element = element;}
	
    @Transient
	private String[] parentElement;
    public String[] getParentElement() {return this.parentElement;}
    public void setParentElement(String[] parentElement) {this.parentElement = parentElement;}
    
    @Transient
    private static HashMap<Integer, String> titles = new HashMap<Integer, String>();
    public static HashMap<Integer, String> getTitles() {return titles;}
    public static void setTitles(HashMap<Integer, String> titles) {Place.titles = titles;}
	
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
	   
    @OneToOne
	private SubSubLocale subSubLocale;
	public SubSubLocale getSubSubLocale() {return this.subSubLocale;}
	public void setSubSubLocale(SubSubLocale subSubLocale) {this.subSubLocale = subSubLocale;}
	
	public Place() {super();} 
	
	private Place(String[] nameList)
	{
		super();
		this.element = trim(nameList);
		this.parentElement = shorten(this.element);
		this.setName(Util.join(this.element, ", "));
		this.parentName = Util.join(this.parentElement, ", ");
		if (this.parentName.length() > 0)
		{
			this.parent = placeMap.get(this.parentName);
			if (this.parent == null)
				this.parent = Place.fetchPlace(this.parentElement);
		}
		switch(this.element.length)
		{
			case 7:
				this.setType(PlaceType.TYPE.SUB_LOCALE);
				this.setSubSubLocale(SubSubLocale.getSubSubLocale(this.parent.getCountry(), this.parent.getState(), this.parent.getCounty(), this.parent.getCity(), this.parent.getLocale(), this.parent.getSubLocale(), this.element[4], null, null));
				this.setSubLocale(this.subSubLocale.getSubLocale());
				this.setLocale(this.subSubLocale.getLocale());
				this.setCity(this.subSubLocale.getCity());
				this.setCounty(this.subSubLocale.getCounty());
				this.setState(this.subSubLocale.getState());
				this.setCountry(this.subSubLocale.getCountry());
				break;
			case 6:
				this.setType(PlaceType.TYPE.SUB_LOCALE);
				this.setSubLocale(SubLocale.getSubLocale(this.parent.getCountry(), this.parent.getState(), this.parent.getCounty(), this.parent.getCity(), this.parent.getLocale(), this.element[4], null, null));
				this.setLocale(this.subLocale.getLocale());
				this.setCity(this.subLocale.getCity());
				this.setCounty(this.subLocale.getCounty());
				this.setState(this.subLocale.getState());
				this.setCountry(this.subLocale.getCountry());
				break;
			case 5:
				this.setType(PlaceType.TYPE.LOCALE);
				this.setLocale(Locale.getLocale(this.parent.getCountry(), this.parent.getState(), this.parent.getCounty(), this.parent.getCity(), this.element[4], null, null));
				this.setCity(this.locale.getCity());
				this.setCounty(this.locale.getCounty());
				this.setState(this.locale.getState());
				this.setCountry(this.locale.getCountry());
				break;
			case 4:
				this.setType(PlaceType.TYPE.CITY);
				this.setCity(City.getCity(this.parent.getCountry(), this.parent.getState(), this.parent.getCounty(), this.element[3], null, null));
				this.setCounty(this.city.getCounty());
				this.setState(this.city.getState());
				this.setCountry(this.city.getCountry());
				break;
			case 3:
				this.setType(PlaceType.TYPE.COUNTY);
				this.setCounty(County.getCounty(this.parent.getCountry(), this.parent.getState(), this.element[2], null, null));
				this.setState(this.county.getState());
				this.setCountry(this.county.getCountry());
				break;
			case 2:
				this.setType(PlaceType.TYPE.STATE);
				this.setState(State.getState(this.parent.getCountry(), this.element[1], null, null));
				this.setCountry(this.state.getCountry());
				break;
			case 1:
				this.setType(PlaceType.TYPE.COUNTRY);
				this.setCountry(Country.getCountry(this, this.element[0], null, null));
				break;
		}
		for(String name:fetchAllNames())
		{
			placeMap.put(name.toLowerCase(), this);
		}
		if (this.country == null)
			throw new RuntimeException("x");
	}
	
	private ArrayList<String> fetchAllNames()
	{
		String[] els = this.element;
		String[] als = this.element;
		String alias;
		for(int i = 0; i < els.length; i++)
		{
			switch(i)
			{
				case 0:
					CountryAlias countryAlias = CountryAlias.getCountryAlias(els[0], null, null);
					if (countryAlias != null)
					{
						alias = countryAlias.getAlias();
						if (alias != null && alias.length() > 0)
							als[0] = alias;
					}
					break;
				case 1:
					StateAlias stateAlias = StateAlias.getStateAlias(els[1], null, null);
					if (stateAlias != null)
					{
						alias = stateAlias.getAlias();
						if (alias != null && alias.length() > 0)
							als[1] = alias;
					}
					break;
				case 2:
					CountyAlias countyAlias = CountyAlias.getCountyAlias(els[2], null, null);
					if (countyAlias != null)
					{
						alias = countyAlias.getAlias();
						if (alias != null && alias.length() > 0)
							als[2] = alias;
					}
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
			}
		}
		ArrayList<String> eList = new ArrayList<String>();
		for(String el:els)
			eList.add(el);
		ArrayList<String> aList = new ArrayList<String>();
		for(String el:als)
			aList.add(el);
		ArrayList<String> ret = fetchAllNames(eList, aList);
		return ret;
	}
	
	private ArrayList<String> fetchAllNames(List<String> els, List<String> als)
	{
		ArrayList<String> ret = new ArrayList<String>();
		if (els.size() == 1)
		{
			ret.add(els.get(0));
			if (!als.get(0).equals(els.get(0)))
				ret.add(els.get(0));
			return ret;
		}
		List<String> el1 = els.subList(1, els.size());
		List<String> al1 = als.subList(1, als.size());
		ArrayList<String> temp = fetchAllNames(el1, al1);
		for(int i = 0; i < temp.size(); i++)
		{
			ret.add(els.get(0)+", "+temp.get(i));
			if (!als.get(0).equals(els.get(0)))
				ret.add(als.get(0)+", "+temp.get(i));
		}
		return ret;
	}
	
	public static Place fetchPlace(String[] nameList)
	{
		String name = Util.join(nameList, ", ");
		Place place = placeMap.get(name.toLowerCase());
		if (place != null)
			return place;
		place = new Place(nameList);
		return place;
	}
	   
	private PlaceStructure fetchPlace()
	{
		if (this.getSubLocale() != null)
			return this.getSubLocale();
		else if (this.getLocale() != null)
			return this.getLocale();
		else if (this.getCity() != null)
			return this.getCity();
		else if (this.getCounty() != null)
			return this.getCounty();
		if (this.getState() != null)
			return this.getState();
		if (this.getCountry() != null)
			return this.getCountry();
		return null;
	}
	
	public ArrayList<URL> fetchUrls()
	{
		PlaceStructure place = fetchPlace();
		if (place != null)
			return place.getUrls();
		return null;
	}
	
	public void addUrl(URL url)
	{
		PlaceStructure place = fetchPlace();
		if (place != null)
		{
			ArrayList<URL> urls = place.getUrls();
			if (urls == null)
				urls = new ArrayList<URL>();
			urls.add(url);
			place.setUrls(urls);
		}
	}
	
	private String[] shorten(String[] elements)
	{
		String[] ret = new String[elements.length-1];
		for(int i = 0; i < ret.length; i++)
			ret[i] = elements[i];
		return ret;
	}
	
	private String[] trim(String[] elements)
	{
		ArrayList<String> list = new ArrayList<String>();
		for(String e:elements)
			if (e != null && e.length() > 0)
				list.add(e.trim());
		String[] ret = new String[list.size()];
		ret = list.toArray(ret);
		return ret;
	}
}
