

package com.javamaster.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.javamaster.domain.NeededLesson;
public class NeededLessonService {
    public EntityManager em = EntityWorker.GetEntityWorker();


    public NeededLesson add(NeededLesson obj){
        em.getTransaction().begin();
        NeededLesson objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public NeededLesson get(int id){
        return em.find(NeededLesson.class, id);
    }

    public void update(NeededLesson obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<NeededLesson> getAll(){
        TypedQuery<NeededLesson> namedQuery = em.createNamedQuery("NeededLesson.getAll", NeededLesson.class);
        return namedQuery.getResultList();
    }
    
    public List<NeededLesson> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("NeededLesson.getById", NeededLesson.class).setParameter("id", id);
        List<NeededLesson> result=namedQuery.getResultList();   
        return result;
    }
    
}


