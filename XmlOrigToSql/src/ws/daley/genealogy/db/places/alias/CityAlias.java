package ws.daley.genealogy.db.places.alias;

import ws.daley.genealogy.db.places.City;
import ws.daley.genealogy.db.places.abstrct.PlaceAlias;
import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;

public class CityAlias extends PlaceAlias
{
    private City place;
    @Override
    public City getPlaceStructure() {return this.place;}
    @Override
    public void setPlaceStructure(PlaceStructure place) {this.place = (City)place;}

    public CityAlias() {}
	
    public CityAlias(String name, City city)
    {
    	super(PlaceType.TYPE.CITY, name, city);
    }
	
	public CityAlias(City city) {this(city.getName(), city);}

    public static void load() {}
}
