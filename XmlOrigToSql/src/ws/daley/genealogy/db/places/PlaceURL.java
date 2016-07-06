package ws.daley.genealogy.db.places;

import java.util.List;

import javax.persistence.Query;

import ws.daley.genealogy.XmlOrigToSQL;
import ws.daley.genealogy.db.places.abstrct.PlaceId;

public class PlaceURL extends PlaceId
{
    private String url;
    public String getURL() {return this.url;}
    public void setURL(String url) {this.url = url;}
    
	public PlaceURL() {}
	
	public PlaceURL(String url)
	{
		this();
		this.url = url;
	}
	
    @SuppressWarnings("unchecked")
    public static PlaceURL getPlace(String element)
	{
		String placeURLSearch = "'" + CompositePlace.quote(element.toLowerCase()) + "'";
		Query q = XmlOrigToSQL.getEntityManager().createNamedQuery("select * from XlsState s " +
				"where " + placeURLSearch);
		List<PlaceURL> placeURLs = q.getResultList();
		if (placeURLs == null)
			return null;
		return placeURLs.get(0);
	}
}
