package ws.daley.genealogy;

import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;
import org.apache.openjpa.persistence.OpenJPAPersistence;

public class Gedcom2SQL
{
	private static OpenJPAEntityManagerFactory entityManagerFactory;
    public static OpenJPAEntityManagerFactory getEntityManagerFactory() {return entityManagerFactory;}
    
    private static OpenJPAEntityManager entityManager;
    public static OpenJPAEntityManager getEntityManager() {return entityManager;}

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable
	{
		String gedFileName = "daley.ged";
		String xlsFileName = "places.xls";
		String wildcardXsdFileName = "gedcom55.xsd";
		switch(args.length)
		{
			case 3:
				wildcardXsdFileName = args[2];
			case 2:
				xlsFileName = args[1];
			case 1:
				gedFileName = args[0];
				break;
			default:
				syntax();
				throw new RuntimeException("Syntax error!");
		}
		System.out.println("creating entity manager factory");
		entityManagerFactory = OpenJPAPersistence.createEntityManagerFactory("daley_genealogy", "persistence.xml");
		System.out.println("creating entity manager");
		entityManager = entityManagerFactory.createEntityManager();
		System.out.println("generating places from XLS");
		Xls2SQL.generateSQL(entityManager, xlsFileName);
		System.out.println("generating people from gedcom");
		Ged2XML.generateSQL(entityManager, gedFileName, wildcardXsdFileName);
	}

	private static void syntax()
	{
		System.out.println("Syntax -");
		System.out.println("\tXls2SQL [gedcom-file-name] [xls-file-name]");
		System.out.println("");
		System.out.println("\txls-file-name[places.xls]: the name of the .xls file containing the list of places and URLs. This file must be in pre-2007 format.");
		System.out.println("\txls-file-name[places.xls]: the name of the .xls file containing the list of places and URLs. This file must be in pre-2007 format.");
		System.out.println("");
	}

}
