package ws.daley.genealogy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.jpa.PersistenceProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import ws.daley.genealogy.db.Gedcom55;
import ws.daley.genealogy.db.places.Fixup;
import ws.daley.genealogy.db.places.Place;
import ws.daley.genealogy.db.xls.XlsPlace;
import ws.daley.genealogy.util.Util;

public class XmlOrigToSQL
{
//	private static Properties entityManagerProperties = new Properties();;
	private static EntityManagerFactory entityManagerFactory;
    public static EntityManagerFactory getEntityManagerFactory() {return entityManagerFactory;}
    
    private static EntityManager entityManager;
    public static EntityManager getEntityManager()
    {
    	if (entityManager == null)
    		entityManager = entityManagerFactory.createEntityManager();
    	return entityManager;
    }
    
    private static EntityTransaction entityTransaction;
    public static EntityTransaction getEntityTranaction() {return entityTransaction;}
    
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Throwable
	{
		
//		entityManagerFactory = Persistence.createEntityManagerFactory("GenealogyOrigxml", "persistence.xml");
//		entityManagerFactory = (new PersistenceProvider()).createEntityManagerFactory("GenealogyOrigxml", entityManagerProperties);
		HashMap<String, Object> props = new HashMap<String, Object>();
		props.put(PersistenceUnitProperties.CLASSLOADER, PersistenceProvider.class.getClassLoader());
		entityManagerFactory = Persistence.createEntityManagerFactory("Genealogy", props);
		entityTransaction = getEntityManager().getTransaction();
		
		Fixup.load();
		
		XlsPlace.buildXlsDBFromSpreadsheet("places.xls");
		XlsPlace.addPlaceLists();
		int x = 0;
		
//		PlacesXls placesXls = new PlacesXls("places.xls");
//		TreeMap<String, Place> placeMap = Place.getPlaceMap();

		File inFile = Util.getInFile("daley.ged55.orig.xml");
		String buffer = Util.getString(inFile);
	    ByteArrayInputStream is = new ByteArrayInputStream(buffer.getBytes());
	    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	    Node documentNode = document.getChildNodes().item(0);
	    Gedcom55 gedcom55 = new Gedcom55(documentNode);

		entityTransaction.begin();
	    entityManager.persist(gedcom55);
		entityTransaction.commit();
	}
	
//	private static String formatDuration(long time)
//	{
//		long secs = time/1000;
//		long mins = secs / 60;
//		long hrs = mins / 60;
//		long dys = hrs / 24;
//		long millis = time - (secs * 1000);
//		long seconds = secs - (mins * 60);
//		long minutes = mins - (hrs * 60);
//		long hours = hrs - (dys * 24);
////		long days = dys;
//		return ""+hours+":"+minutes+":"+seconds+"."+millis;
//	}
	
//	private static void dropTables()
//	{
//		Query tables = entityManager.createQuery("select table_name from information_schema.tables where table_schema='genealogy_origxml';");
//		List list = tables.getResultList();
//		int xx= 0;
//	}
}
