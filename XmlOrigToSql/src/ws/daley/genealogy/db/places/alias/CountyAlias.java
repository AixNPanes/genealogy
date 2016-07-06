package ws.daley.genealogy.db.places.alias;

import javax.persistence.EntityManager;

import ws.daley.genealogy.XmlOrigToSQL;
import ws.daley.genealogy.db.places.County;
import ws.daley.genealogy.db.places.abstrct.PlaceAlias;
import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;

public class CountyAlias extends PlaceAlias
{
    private County place;
    @Override
    public County getPlaceStructure() {return this.place;}
    @Override
    public void setPlaceStructure(PlaceStructure place) {this.place = (County)place;}
	
	public CountyAlias() {}
	
	public CountyAlias(String name, County county) {super(PlaceType.TYPE.COUNTY, name, county);}
	
	public CountyAlias(String name, String[] element) {super(PlaceType.TYPE.COUNTY, name, element);}
	
    public static void load()
	{
		EntityManager em = XmlOrigToSQL.getEntityManager();
		for(CountyAlias countyAlias: new CountyAlias[]{
				new CountyAlias("Mohave, Arizona, United States", new String[]{"Mohave County", "Arizona", "United States"}),
				new CountyAlias("Pope, Arkansas, United States", new String[]{"Pope County", "Arkansas", "United States"}),
				new CountyAlias("Pope, Arkansas, USA", new String[]{"Pope County", "Arkansas", "United States"}),
				new CountyAlias("Aameda, California, United States", new String[]{"Alameda County", "California", "United States"}),
				new CountyAlias("Calaveras, California, United States", new String[]{"Calaveras County", "California", "United States"}),
				new CountyAlias("Calavaras County, California, United States", new String[]{"Calaveras County", "California", "United States"}),
				new CountyAlias("Kern, California, United States", new String[]{"Kern County", "California", "United States"}),
				new CountyAlias("Talbot, Georgia, United States", new String[]{"Talbot County", "Georgia", "United States"}),
				new CountyAlias("Jo Davies County, Illinois, United States", new String[]{"Jo Daviess County", "Illinois", "United States"}),
				new CountyAlias("La Porte County, Illinois, United States", new String[]{"LaPorte County", "Indiana", "United States"}),
				new CountyAlias("Fayette, Iowa, United States", new String[]{"Fayette County", "Iowa", "United States"}),
				new CountyAlias("St Louis County, Missouri, United States", new String[]{"Saint Louis County", "Missouri", "United States"}),
				new CountyAlias("Kings, New York XlsState, United States", new String[]{"Kings County", "New York XlsState", "United States"}),
				new CountyAlias("Queens, New York XlsState, United States", new String[]{"Queens County", "New York XlsState", "United States"}),
				new CountyAlias("Guernsey, Ohio, United States", new String[]{"Guernsey County", "Ohio", "United States"}),
				new CountyAlias("McIntosh, Oklahoma, United States", new String[]{"McIntosh County", "Oklahoma", "United States"}),
				new CountyAlias("Mcintosh, Oklahoma, United States", new String[]{"McIntosh County", "Oklahoma", "United States"}),
				new CountyAlias("Muskogee, Oklahoma, United States", new String[]{"Muskogee County", "Oklahoma", "United States"}),
				new CountyAlias("Tulsa, Oklahoma, United States", new String[]{"Tulsa County", "Oklahoma", "United States"}),
				new CountyAlias("Henry, Tennessee, United States", new String[]{"Henry County", "Tennessee", "United States"}),
				new CountyAlias("Lavaca, Texas, United States", new String[]{"Lavaca County", "Texas", "United States"}),
				new CountyAlias("Nueces County, Texas, United States", new String[]{"Nueces", "Texas", "United States"}),
				new CountyAlias("Suffolk, Virginia, United States", new String[]{"Nansemond County", "Virginia", "United States"}),
				new CountyAlias("Devon, England, Great Britain", new String[]{"Devonshire", "England", "Great Britain"}),
				new CountyAlias("Tyrone, Northern Ireland, Great Britain", new String[]{"Tyrone County", "Northern Ireland", "Great Britain"}),
				new CountyAlias("Yorkshire, England, Great Britain", new String[]{"York", "England", "Great Britain"}),
				new CountyAlias("Roscommon, Ireland, Great Britain", new String[]{"County Roscommon", "Ireland", "Great Britain"}),
				new CountyAlias("Central Plains, Manitoba, Canada", new String[]{"Central Plains Region", "Manitoba", "Canada"}),
				new CountyAlias("Pembina, Manitoba, Canada", new String[]{"Pembina Valley", "Manitoba", "Canada"}),
				new CountyAlias("Selkirk, Manitoba, Canada", new String[]{"Selkirk Area", "Manitoba", "Canada"}),
				new CountyAlias("St. Anne, Manitoba, Canada", new String[]{"Ste. Anne", "Manitoba", "Canada"}),
				new CountyAlias("St. Rose, Manitoba, Canada", new String[]{"Ste. Rose", "Manitoba", "Canada"}),
				new CountyAlias("Thompson, Manitoba, Canada", new String[]{"Thompson/North Central", "Manitoba", "Canada"}),
				new CountyAlias("St. John, New Brunswick, Canada", new String[]{"Saint John", "New Brunswick", "Canada"}),
				new CountyAlias("Fredericton, New Brunswick, Canada", new String[]{"Fredericton Junction", "New Brunswick", "Canada"}),
				new CountyAlias("Abitibi County, Quebec, Canada", new String[]{"Abitibi Territory", "Quebec", "Canada"})
		})
			em.persist(countyAlias);
	}
}
