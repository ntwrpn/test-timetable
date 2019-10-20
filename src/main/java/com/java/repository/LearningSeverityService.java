

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.LearningSeverity;
import org.springframework.stereotype.Service;

@Service
public class LearningSeverityService {
    public EntityManager em = EntityWorker.GetEntityWorker();


    
    public LearningSeverity add(LearningSeverity obj){
        em.getTransaction().begin();
        LearningSeverity objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public LearningSeverity get(int id){
        return em.find(LearningSeverity.class, id);
    }

    public void update(LearningSeverity obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<LearningSeverity> getAll(){
        TypedQuery<LearningSeverity> namedQuery = em.createNamedQuery("LearningSeverity.getAll", LearningSeverity.class);
        return namedQuery.getResultList();
    }
    
    public List<LearningSeverity> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("LearningSeverity.getById", LearningSeverity.class).setParameter("id", id);
        List<LearningSeverity> result=namedQuery.getResultList();  
        return result;
    }
    
}


