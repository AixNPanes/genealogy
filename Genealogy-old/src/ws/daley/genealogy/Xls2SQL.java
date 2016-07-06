package ws.daley.genealogy;

import java.io.IOException;

import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;
import org.apache.openjpa.persistence.OpenJPAPersistence;

import ws.daley.genealogy.db.PlacesXls;
import ws.daley.genealogy.db.places.PlaceLoader;

public class Xls2SQL
{
	private static OpenJPAEntityManagerFactory entityManagerFactory;
    public static OpenJPAEntityManagerFactory getEntityManagerFactory() {return entityManagerFactory;}
    
    private static OpenJPAEntityManager entityManager;
    public static OpenJPAEntityManager getEntityManager() {return entityManager;}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		String fileName = "places.xls";
		if (args.length == 1)
		{
			fileName = args[0];
		}
		else
		{
			syntax();
			throw new RuntimeException("Syntax error!");
		}
		entityManagerFactory = OpenJPAPersistence.createEntityManagerFactory("daley_genealogy", "persistence.xml");
		entityManager = entityManagerFactory.createEntityManager();
		generateSQL(entityManager, fileName);
	}

	private static void syntax()
	{
		System.out.println("Syntax -");
		System.out.println("\tXls2SQL [xls-file-name]");
		System.out.println("");
		System.out.println("\txls-file-name[places.xls]: the name of the .xls file containing the list of places and URLs. This file must be in pre-2007 format.");
		System.out.println("");
	}
	
	public static void generateSQL(OpenJPAEntityManager em, String places) throws IOException
	{
		em.getTransaction().begin();
		PlaceLoader.loadPlaces(em);
		PlacesXls placesXls = new PlacesXls(em, places);
		placesXls.insertLocations();
		em.getTransaction().commit();
	}
	
	
}
