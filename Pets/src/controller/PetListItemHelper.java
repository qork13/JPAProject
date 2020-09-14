package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PetList;

public class PetListItemHelper {
	
	static EntityManagerFactory emfactory =	Persistence.createEntityManagerFactory("Pets");

	public void insertPet(PetList li) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();

		
	}
	
	public List<PetList> showAllPets(){
		EntityManager em = emfactory.createEntityManager();
		List<PetList> allPets = em.createQuery("Select p from PetList p").getResultList();
		
		return allPets;
	}
	
	public void deletePet(PetList toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetList> typedQuery = em.createQuery("Select li from PetList li where"
				+ " li.type = :selectedType and li.name = :selectedName and li.owner = :selectedOwner", PetList.class);
		
		typedQuery.setParameter("selectedType", toDelete.getType());
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		
		typedQuery.setMaxResults(1);
		
		PetList result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public PetList searchForPetById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PetList found = em.find(PetList.class, idToEdit);
		em.close();
		return	found;
	}

	public void updatePetItem(PetList toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}

	public List<PetList> searchForPetByType(String petType) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetList> typedQuery	= em.createQuery("select li from PetList li where"
				+ " li.type = :selectedType", PetList.class);
		typedQuery.setParameter("selectedType", petType);
		List<PetList>	foundPets = typedQuery.getResultList();
		em.close();
		return	foundPets;
		
	}

	public List<PetList> searchForPetByName(String petName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetList>	typedQuery	=	em.createQuery("select	li	from PetList li where "
				+ " li.name	= :selectedName", PetList.class);
		typedQuery.setParameter("selectedName",	petName);
		List<PetList>	foundPets = typedQuery.getResultList();
		em.close();
		return	foundPets;
	}
	
	public List<PetList> searchForPetByOwner(String petOwner) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetList>	typedQuery	=	em.createQuery("select	li	from PetList li where "
				+ " li.owner = :selectedOwner", PetList.class);
		typedQuery.setParameter("selectedOwner", petOwner);
		List<PetList>	foundPets = typedQuery.getResultList();
		em.close();
		return	foundPets;
	}
	
	public void cleanUp() {
		emfactory.close();
	}

}
