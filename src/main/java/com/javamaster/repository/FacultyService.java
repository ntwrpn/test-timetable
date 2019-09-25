

package com.javamaster.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.javamaster.domain.Faculty;
public class FacultyService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Faculty add(Faculty obj){
        em.getTransaction().begin();
        Faculty objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Faculty get(int id){
        return em.find(Faculty.class, id);
    }

    public void update(Faculty obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Faculty> getAll(){
        TypedQuery<Faculty> namedQuery = em.createNamedQuery("Faculty.getAll", Faculty.class);
        return namedQuery.getResultList();
    }
    
    public List<Faculty> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Faculty.getById", Faculty.class).setParameter("id", id);
        List<Faculty> result=namedQuery.getResultList();   
        return result;
    }
    
}


