package ws.daley.genealogy.db.places;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ws.daley.genealogy.XmlOrigToSQL;
import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.util.Util;

public class CompositePlace
{
	private String[] element;

	private int elementIndex = 0;
	
	private static Long key = 0l;
	private Long placeKey = -1l;
	public Long getPlaceKey() {return this.placeKey;}
	public void setPlaceKey(Long placeKey) {this.placeKey = placeKey;}

	private Country country = null;
    public Country getCountry() {return this.country;}
    public void setCountry(Country country) {this.country = country;}

	private State state = null;
    public State getState() {return this.state;}
    public void setState(State state) {this.state = state;}

	private County county = null;
    public County getCounty() {return this.county;}
    public void setCounty(County county) {this.county = county;}

	private City city = null;
    public void setCity(City city) {this.city = city;}
    public City getCity() {return this.city;}

	private Locale locale = null;
    public Locale getLocale() {return this.locale;}
    public void setLocale(Locale locale) {this.locale = locale;}

	private String url1;
    public String getUrl1() {return this.url1;}
    public void setUrl1(String url1) {this.url1 = url1;}

	private String url2;
    public String getUrl2() {return this.url2;}
    public void setUrl2(String url2) {this.url2 = url2;}

	private String url3;
    public String getUrl3() {return this.url3;}
    public void setUrl3(String url3) {this.url3 = url3;}
	
	public static String quote(String s)
	{
		return s.replaceAll("'", "''");
	}
	
	public static String stringify(String[] el, int start)
	{
		String ret = "";
		for(int i = start; i < el.length; i++)
			ret += ((ret.length() == 0)?"":", ") + el[i];
		return ret;
	}
	
	public static String stringify(String[] el)
	{
		return stringify(el, 0);
	}
	
	public CompositePlace() {}
	
	public CompositePlace(Place place)
	{
		this.element = place.getElement();
		setPlaceKey(++key);
		
		String[] names = this.element;
		while(names.length > 0)
		{
			if ("Ashland".equals(names[0]))
			{
				int x = 0;
			}
			PlaceStructure newPlace = PlaceStructure.getPlace(names);
			String className = newPlace.getClass().getSimpleName();
			if ("XlsCountry".equals(className))
				this.country = (Country)newPlace;
			else if ("XlsState".equals(className))
				this.state = (State)newPlace;
			else if ("XlsCountry".equals(className))
				this.county = (County)newPlace;
			else if ("City".equals(className))
				this.city = (City)newPlace;
			else if ("Locale".equals(className))
				this.locale = (Locale)newPlace;
			names = Util.subElement(names, 1, names.length);
		}
	}

    /**
     * @return the element
     */
    public String[] getElement() {return this.element;}
    
    private String getTypes()
    {
		List<String> types = new ArrayList<String>();
		if (this.country != null && this.country.getName() != null)
			types.add("XlsCountry");
		if (this.state != null && this.state.getName() != null)
			types.add("XlsState");
		if (this.county != null && this.county.getName() != null)
			types.add("County");
		if (this.city != null && this.city.getName() != null)
			types.add("City");
		if (this.locale != null && this.locale.getName() != null)
			types.add("Locale");
		String ret = "";
		for(String type:types)
			ret += (ret.length()>0?"And":"") + type;
		return ret;
    }
    
    private Object query(Query query)
    {
    	Query q = query;
		if (this.country != null && this.country.getName() != null)
			q = q.setParameter("countryId", this.country.getId().toString());
		if (this.state != null && this.state.getName() != null)
			q = q.setParameter("stateId", this.state.getId().toString());
		if (this.county != null && this.county.getName() != null)
			q = q.setParameter("CountyId", this.county.getId().toString());
		if (this.city != null && this.city.getName() != null)
			q = q.setParameter("cityId", this.city.getId().toString());
		if (this.locale != null && this.locale.getName() != null)
			q = q.setParameter("localeId", this.locale.getId().toString());
		Object o = null;
    	try{o = q.getSingleResult();}
    	catch(Throwable t) {}
		return o;
    }

    private Country setCountry()
	{
    	String namedQuery = "findCountry" + getTypes(); 
    	Country newCountry = (Country)query(XmlOrigToSQL.getEntityManager().createNamedQuery(namedQuery).
			setParameter("name", this.element[this.elementIndex])); 
		if (newCountry != null)
			++this.elementIndex;
		return newCountry;
	}
	
	private State setState()
	{
    	String namedQuery = "findState" + getTypes(); 
    	State newState = (State)query(XmlOrigToSQL.getEntityManager().createNamedQuery(namedQuery).
			setParameter("name", this.element[this.elementIndex])); 
		if (newState != null)
			++this.elementIndex;
		return newState;
	}
	
	private County setCounty()
	{
    	String namedQuery = "findCounty" + getTypes(); 
    	County newCounty = (County)query(XmlOrigToSQL.getEntityManager().createNamedQuery(namedQuery).
			setParameter("name", this.element[this.elementIndex])); 
		if (newCounty != null)
			++this.elementIndex;
		return newCounty;
	}
	
	private City setCity()
	{
    	String namedQuery = "findCity" + getTypes(); 
    	City newCity = (City)query(XmlOrigToSQL.getEntityManager().createNamedQuery(namedQuery).
			setParameter("name", this.element[this.elementIndex])); 
		if (newCity != null)
			++this.elementIndex;
		return newCity;
	}
	
	private Locale setLocale()
	{
    	String namedQuery = "findLocale" + getTypes(); 
    	Locale newLocale = (Locale)query(XmlOrigToSQL.getEntityManager().createNamedQuery(namedQuery).
			setParameter("name", this.element[this.elementIndex])); 
		if (newLocale != null)
			++this.elementIndex;
		return newLocale;
	}
}
