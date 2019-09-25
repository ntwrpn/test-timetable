

package com.javamaster.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.javamaster.domain.Subgroup;
public class SubgroupService {
    public EntityManager em = EntityWorker.GetEntityWorker();


    public Subgroup add(Subgroup obj){
        em.getTransaction().begin();
        Subgroup objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Subgroup get(int id){
        return em.find(Subgroup.class, id);
    }

    public void update(Subgroup obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Subgroup> getAll(){
        TypedQuery<Subgroup> namedQuery = em.createNamedQuery("Subgroup.getAll", Subgroup.class);
        return namedQuery.getResultList();
    }
    
    public List<Subgroup> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Subgroup.getById", Subgroup.class).setParameter("id", id);
        List<Subgroup> result=namedQuery.getResultList();   
        return result;
    }
    
}


