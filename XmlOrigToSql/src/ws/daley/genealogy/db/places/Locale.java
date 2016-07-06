package ws.daley.genealogy.db.places;

import java.util.List;

import javax.persistence.EntityManager;

import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;

public class Locale extends PlaceStructure
{
	private City parentPlace;
	@Override
    public City getParentPlaceStructure() {return this.parentPlace;}
	@Override
    public void setParentPlaceStructure(PlaceStructure parentPlace) {this.parentPlace = (City)parentPlace;}

	private Country country;
    public Country getCountry() {return this.country;}
    public void setCountry(Country country) {this.country = country;}
	
	private State state;
    public State getState() {return this.state;}
    public void setState(State state) {this.state = state;}

	private County county;
    public County getCounty() {return this.county;}
    public void setCounty(County county) {this.county = county;}

	private City city;
    public City getCity() {return this.city;}
    public void setCity(City city) {this.city = city;}

	public Locale() {}
	
	public Locale(String[] name, String abbrev, County county, List<PlaceURL> urls)
	{
		super(TYPE.LOCALE, name, abbrev, county, urls);
		if (name[0].startsWith("161"))
		{
			int x = 0;
		}
		this.setCity(this.getParentPlaceStructure());
		if (this.getCity() != null)
			this.setCounty(this.getCity().getParentPlaceStructure());
		if (this.getCounty() != null)
			this.setState(this.getCounty().getParentPlaceStructure());
		if (this.getState() != null)
			this.setCountry(this.getState().getParentPlaceStructure());
	}
	
	public Locale(String[] name, String abbrev)
	{
		this(name, abbrev, null, null);
	}
    
    public static void persist(@SuppressWarnings("unused") EntityManager em) {}
	
    public static Locale getPlace(String element) {return (Locale)getPlace(PlaceType.TYPE.LOCALE, new String[]{element});}
}
