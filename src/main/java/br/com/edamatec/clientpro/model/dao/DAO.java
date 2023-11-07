package br.com.edamatec.clientpro.model.dao;

import br.com.edamatec.clientpro.model.entities.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DAO<E> {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private Class<E> _class;
    private final static String PERSISTENCEUNITNAME = "client_pro";
    
    static {
            try {
                    emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
            } catch (Exception e) {
                    throw e;
            }
    }

    public DAO() {
            this(null);
    }

    public DAO(Class<E> _class) {
            this._class= _class;
            em = emf.createEntityManager();
    }
    
    private DAO<E> openTransaction() {
            em.getTransaction().begin();
            return this;
    }

    private DAO<E> closeTransaction() {
            em.getTransaction().commit();
            return this;
    }

    private DAO<E> include(E entity) {
            em.persist(entity);
            return this;
    }
    
    private DAO<E> remove(E entity){
            em.remove(entity);
            return this;
    }
    
    private DAO<E> edit(E entity){
        em.detach(entity);
        em.merge(entity);
        return this;
    }
    
    public DAO<E> editAtomic(E entity){
        return this.openTransaction().edit(entity).closeTransaction();
    }
    
    public DAO<E> removeAtomic(E entity){
        return this.openTransaction().remove(entity).closeTransaction();
    }
    
    public DAO<E> includeAtomic(E entity) {
            return this.openTransaction().include(entity).closeTransaction();
    }
    
    public List<E> getAll(){
        String jpql = "SELECT e FROM " + _class.getName() + " e";
        TypedQuery<E> query = em.createQuery(jpql, _class);
        return query.getResultList();
    }
    
   public E getById(Object id){
        return em.find(_class, id);
   }

   public List<Client> searchByFilters(String name, String cpf, String telephone, String email){
       CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
       CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
       Root<Client> root = criteriaQuery.from(Client.class);
       
       List<Predicate> predicates = new ArrayList<>();
       
        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        if (cpf != null && !cpf.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("cpf"), cpf));
        }
        if (telephone != null && !telephone.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("telephone"), telephone));
        }
        if (email != null && !email.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("email"), email));
        }
        
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        
        TypedQuery<Client> query = em.createQuery(criteriaQuery);
        return query.getResultList();
   }
}
