package ws.daley.genealogy;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.jpa.osgi.PersistenceProvider;

public class Test
{
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

    public static void main(String[] args)
	{
		HashMap<String, Object> props = new HashMap<String, Object>();
		props.put(PersistenceUnitProperties.CLASSLOADER, PersistenceProvider.class.getClassLoader());
		entityManagerFactory = Persistence.createEntityManagerFactory("GenealogyTest", props);
		entityTransaction = getEntityManager().getTransaction();
		Query q = entityManager.createNamedQuery("findCountyFromCountryAndStateAndCounty");
		q = q.setParameter("county", "New West Minster");
		q = q.setParameter("STATE", "British Columbia");
		q = q.setParameter("COUNTRY", "Canada");
		List l = q.getResultList();
		int i = 0;
	}

}
