package ws.daley.genealogy.db.places;

import ws.daley.genealogy.db.places.abstrct.PlaceId;

public class URLType extends PlaceId
{
    private String name = "";
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public URLType() {}
	
	public URLType(String name)
	{
		this.setName(name);
	}
}
