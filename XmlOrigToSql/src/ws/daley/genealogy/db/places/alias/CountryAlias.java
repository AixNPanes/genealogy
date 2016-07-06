package ws.daley.genealogy.db.places.alias;

import javax.persistence.EntityManager;

import ws.daley.genealogy.XmlOrigToSQL;
import ws.daley.genealogy.db.places.Country;
import ws.daley.genealogy.db.places.abstrct.PlaceAlias;
import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;

public class CountryAlias extends PlaceAlias
{
    private Country place;
    @Override
    public Country getPlaceStructure() {return this.place;}
    @Override
    public void setPlaceStructure(PlaceStructure place) {this.place = (Country)place;}

    public CountryAlias() {}
    
    public CountryAlias(String name, Country country)
    {
    	super(PlaceType.TYPE.COUNTRY, name, country);
    }
    
    public CountryAlias(String name, String[] element)
    {
    	super(PlaceType.TYPE.COUNTRY, name, element);
    }

    public static void load()
	{
		EntityManager em = XmlOrigToSQL.getEntityManager();
		for(CountryAlias countryAlias: new CountryAlias[]{
				new CountryAlias("USA", new String[]{"United States"}),
				new CountryAlias("Bavaria", new String[]{"Germany"}),
				new CountryAlias("England", new String[]{"United Kingdom"}),
				new CountryAlias("Great Britain", new String[]{"United Kingdom"}),
				new CountryAlias("Northern Ireland", new String[]{"United Kingdom"}),
				new CountryAlias("Holland", new String[]{"Netherlands"}),
				new CountryAlias("Russia", new String[]{"Russian Federation"}),
				new CountryAlias("Wales", new String[]{"United Kingdom"})
		})
			em.persist(countryAlias);
	}
}
