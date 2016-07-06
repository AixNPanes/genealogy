package ws.daley.genealogy.db.places.abstrct;


public abstract class PlaceId
{
	private static Long idNo = new Long(0);
	
    private Long id = ++idNo;
    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

	public PlaceId() {}
}
