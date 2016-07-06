package ws.daley.genealogy.db.places;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;
import ws.daley.genealogy.util.Util;

public class County extends PlaceStructure
{
	private static PlaceStructure[] places()
	{
		ArrayList<PlaceStructure> list = new ArrayList<PlaceStructure>();
		list = Util.arrayList(list, CountyUS.us());
		list = Util.arrayList(list, CountyCanada.canada());
		list = Util.arrayList(list, CountyMexico.mexico());
		list = Util.arrayList(list, CountyFrance.france());
		list = Util.arrayList(list, CountyUK.uk());
		PlaceStructure[] placeArray = new PlaceStructure[list.size()];
		for(int i = 0; i < list.size(); i++)
			placeArray[i] = list.get(i);
		return placeArray;
	}
	
	private State parentPlace;
	@Override
    public State getParentPlaceStructure() {return this.parentPlace;}
	@Override
    public void setParentPlaceStructure(PlaceStructure parentPlace) {this.parentPlace = (State)parentPlace;}
	
	private Country country;
    public Country getCountry() {return this.country;}
    public void setCountry(Country country) {this.country = country;}

    private State state;
    public State getState() {return this.state;}
    public void setState(State state) {this.state = state;}

	public County() {}
	
	public County(String[] name, String abbrev)
	{
		this(name, abbrev, null, null);
	}
	
	public County(String[] name, String abbrev, State state, List<PlaceURL> urls)
	{
		super(TYPE.COUNTY, name, abbrev, state, urls);
		this.setState(this.getParentPlaceStructure());
		if (this.getState() == null)
			this.setState((State)PlaceStructure.getPlace(Util.subElement(name, 1, name.length)));
		if (this.getState() != null)
			this.setCountry(this.getState().getParentPlaceStructure());
	}
    
    public static void persist(EntityManager em) {PlaceStructure.persist(em, places());}
	
    public static County getPlace(String element) {return (County)getPlace(PlaceType.TYPE.COUNTY, new String[]{element});}
}
