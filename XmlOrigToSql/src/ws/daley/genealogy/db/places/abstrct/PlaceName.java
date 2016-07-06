package ws.daley.genealogy.db.places.abstrct;



public abstract class PlaceName extends PlaceId
{
    private String name = "";
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

	public PlaceName() {}
	
	public PlaceName(String name)
	{
		this.setName(name);
	}
}
