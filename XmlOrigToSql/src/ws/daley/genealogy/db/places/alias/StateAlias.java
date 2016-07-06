package ws.daley.genealogy.db.places.alias;

import javax.persistence.EntityManager;

import ws.daley.genealogy.XmlOrigToSQL;
import ws.daley.genealogy.db.places.State;
import ws.daley.genealogy.db.places.abstrct.PlaceAlias;
import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;

public class StateAlias extends PlaceAlias
{
    private State place;
    @Override
    public State getPlaceStructure() {return this.place;}
    @Override
    public void setPlaceStructure(PlaceStructure place) {this.place = (State)place;}
	
	public StateAlias() {}
	
	public StateAlias(String name, State state)
	{
		super(PlaceType.TYPE.STATE, name, state);
		if (name.trim().length() == 0)
		{
			int i = 0;
		}
	}
	
	public StateAlias(String name, String[] element)
	{
		super(PlaceType.TYPE.STATE, name, element);
		if (name.trim().length() == 0)
		{
			int i = 0;
		}
	}
	
    public static void load()
	{
		EntityManager em = XmlOrigToSQL.getEntityManager();
		for(StateAlias stateAlias: new StateAlias[]{
	    		new StateAlias("New York, United States", new String[]{"New York XlsState", "United States"}),
	    		new StateAlias("Alsace, France", new String[]{"Alsace-Lorraine", "France"}),
	    		new StateAlias("Bavaria, Germany", new String[]{"Bayern", "Germany"}),
	    		new StateAlias("Hesse, Germany", new String[]{"Hessen", "Germany"}),
	    		new StateAlias("Mecklenburg-Western Pomerania, Germany", new String[]{"Mecklenburg-Vorpommern", "Germany"}),
	    		new StateAlias("Lower Saxony, Germany", new String[]{"Niedersachsen", "Germany"}),
	    		new StateAlias("North Rhine-Westphalia, Germany", new String[]{"Nordrhein-Westfalen", "Germany"}),
	    		new StateAlias("Rhineland-Palatinate, Germany", new String[]{"Rheinland-Pfalz", "Germany"}),
	    		new StateAlias("Saxony, Germany", new String[]{"Sachsen", "Germany"}),
	    		new StateAlias("Saxony-Anhalt, Germany", new String[]{"Sachsen-Anhalt", "Germany"}),
	    		new StateAlias("Thüringen, Germany", new String[]{"Thuringia", "Germany"}),
	    		new StateAlias("Thuringen, Germany", new String[]{"Thuringia", "Germany"}),
	    		new StateAlias("Noord Brabant, Netherlands", new String[]{"North Brabant", "Netherlands"}),
	    		new StateAlias("Noord Holland, Netherlands", new String[]{"North Holland", "Netherlands"}),
	    		new StateAlias("Zealand, Netherlands", new String[]{"Zeeland", "Netherlands"}),
	    		new StateAlias("Zuid Holland, Netherlands", new String[]{"South Holland", "Netherlands"}),
	    		new StateAlias("Zuid-Holland, Netherlands", new String[]{"South Holland", "Netherlands"})
		})
			em.persist(stateAlias);
	}
}
