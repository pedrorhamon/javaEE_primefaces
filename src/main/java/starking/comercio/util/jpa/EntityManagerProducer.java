package starking.comercio.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author pedroRhamon
 */

@ApplicationScoped
public class EntityManagerProducer {
	
	private EntityManagerFactory factory;

	
	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("ComercioPU");
	}
	
	@Produces @RequestScoped
	public EntityManager createEntityManager() {
		return this.factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
}
