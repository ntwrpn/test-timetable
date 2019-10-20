

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.CanTeach;
import org.springframework.stereotype.Service;

@Service
public class CanTeachService {
    public EntityManager em = EntityWorker.GetEntityWorker();
    public CanTeach add(CanTeach obj){
        em.getTransaction().begin();
        CanTeach objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public CanTeach get(int id){
        return em.find(CanTeach.class, id);
    }

    public void update(CanTeach obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<CanTeach> getAll(){
        TypedQuery<CanTeach> namedQuery = em.createNamedQuery("CanTeach.getAll", CanTeach.class);
        return namedQuery.getResultList();
    }
    
    public List<CanTeach> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("CanTeach.getById", CanTeach.class).setParameter("id", id);
        List<CanTeach> result=namedQuery.getResultList();   
        return result;
    }
    
}


