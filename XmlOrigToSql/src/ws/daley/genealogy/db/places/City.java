package ws.daley.genealogy.db.places;

import java.util.List;

import javax.persistence.EntityManager;

import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;

public class City extends PlaceStructure
{
	private County parentPlace;
	@Override
    public County getParentPlaceStructure() {return this.parentPlace;}
	@Override
    public void setParentPlaceStructure(PlaceStructure parentPlace) {this.parentPlace = (County)parentPlace;}

	private Country country;
    public Country getCountry() {return this.country;}
    public void setCountry(Country country) {this.country = country;}
	
	private State state;
    public State getState() {return this.state;}
    public void setState(State state) {this.state = state;}

	private County county;
    public County getCounty() {return this.county;}
    public void setCounty(County county) {this.county = county;}

	public City() {}
	
	public City(String[] name, String abbrev, County county, List<PlaceURL> urls)
	{
		super(TYPE.CITY, name, abbrev, county, urls);
		this.setCounty(this.getParentPlaceStructure());
		if (this.getCounty() != null)
			this.setState(this.getCounty().getParentPlaceStructure());
		if (this.getState() != null)
			this.setCountry(this.getState().getParentPlaceStructure());
	}
	
	public City(String[] name, String abbrev)
	{
		this(name, abbrev, null, null);
	}
    
    public static void persist(@SuppressWarnings("unused") EntityManager em) {}
	
    public static City getPlace(String element) {return (City)getPlace(PlaceType.TYPE.CITY, new String[]{element});}
}
