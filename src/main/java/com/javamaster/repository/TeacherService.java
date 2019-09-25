

package com.javamaster.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.javamaster.domain.Teacher;
public class TeacherService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Teacher add(Teacher obj){
        em.getTransaction().begin();
        Teacher objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Teacher get(int id){
        return em.find(Teacher.class, id);
    }

    public void update(Teacher obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Teacher> getAll(){
        TypedQuery<Teacher> namedQuery = em.createNamedQuery("Teacher.getAll", Teacher.class);
        return namedQuery.getResultList();
    }
    
    public List<Teacher> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Teacher.getById", Teacher.class).setParameter("id", id);
        List<Teacher> result=namedQuery.getResultList();   
        return result;
    }
    
}


