

package com.javamaster.repository;

import com.javamaster.domain.LearningSeverityList;
import com.javamaster.repository.EntityWorker;
import java.util.ArrayList;
import java.util.Iterator; 
import java.util.List;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
public class LearningSeverityListService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    
    public LearningSeverityList add(LearningSeverityList obj){
        em.getTransaction().begin();
        LearningSeverityList objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public LearningSeverityList get(int id){
        LearningSeverityList return_el = em.find(LearningSeverityList.class, id);
        return return_el;
    }

    public void update(LearningSeverityList obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<LearningSeverityList> getAll(){
        TypedQuery<LearningSeverityList> namedQuery = em.createNamedQuery("LearningSeverityList.getAll", LearningSeverityList.class);
        List<LearningSeverityList> ret_list = namedQuery.getResultList();
        return ret_list;
    }

    public List<LearningSeverityList> getLastId(){
        TypedQuery namedQuery = em.createNamedQuery("LearningSeverityList.getLastId", LearningSeverityList.class);
        List<LearningSeverityList> result=namedQuery.getResultList();  
        return result;
    }
    
    public List<LearningSeverityList> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("LearningSeverityList.getById", LearningSeverityList.class).setParameter("id", id);
        List<LearningSeverityList> result=namedQuery.getResultList();   
        return result;
    }
    

    public List<LearningSeverityList> getAllInArray(List<Integer> id){
        
        List<LearningSeverityList> Bigresult = new ArrayList<LearningSeverityList>(); 
        for(int i=0; i < id.size(); i++) {
            TypedQuery namedQuery = em.createNamedQuery("LearningSeverityList.getById", LearningSeverityList.class).setParameter("id", id.get(i));
            List<LearningSeverityList> result=namedQuery.getResultList();  
            Bigresult.addAll(result);
         }
        return Bigresult;
    }
}


