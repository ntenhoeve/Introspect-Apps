package nth.innoforce.domain.project;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nth.innoforce.dataaccess.InnoforceDataAccess;
import nth.innoforce.domain.resource.Resource;
import nth.introspect.dataaccess.hibernate.persistenceunit.PersistenceUnit;


public class ProjectDataAccess extends InnoforceDataAccess<Project> {

	public Project getGibletHarvesterProject() {
		PersistenceUnit persistanceUnit = getPersistenceUnit();
		String queryString = persistanceUnit.createSelectQuery(Project.class, "where e.number=602104");
		EntityManager entityManager = persistanceUnit.getEntityManager();
		Query query = entityManager.createQuery(queryString);
		return (Project) query.getSingleResult();
	}

	public List<Project> findProjectsOfResource(Resource resource) {
		PersistenceUnit persistenceUnit = getPersistenceUnit();
		String queryString = persistenceUnit.createSelectQuery(Project.class, "where e.deleted=false and ( e.projectManager.id=" + resource.getId() + " or e.projectLeader.id=" + resource.getId() + ")");
		EntityManager entityManager = persistenceUnit.getEntityManager();
		Query query = entityManager.createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<Project> resultList = query.getResultList();
		return resultList;
	}

}
