

package com.javamaster.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.javamaster.domain.Syllabus;
public class SyllabusService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Syllabus add(Syllabus obj){
        em.getTransaction().begin();
        Syllabus objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Syllabus get(int id){
        return em.find(Syllabus.class, id);
    }

    public void update(Syllabus obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Syllabus> getAll(){
        TypedQuery<Syllabus> namedQuery = em.createNamedQuery("Syllabus.getAll", Syllabus.class);
        return namedQuery.getResultList();
    }
    
    public List<Syllabus> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Syllabus.getById", Syllabus.class).setParameter("id", id);
        List<Syllabus> result=namedQuery.getResultList();   
        return result;
    }
    
}


