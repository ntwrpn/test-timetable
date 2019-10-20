

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Lesson;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Lesson add(Lesson obj){
        em.getTransaction().begin();
        Lesson objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Lesson get(int id){
        return em.find(Lesson.class, id);
    }

    public void update(Lesson obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Lesson> getAll(){
        TypedQuery<Lesson> namedQuery = em.createNamedQuery("Lesson.getAll", Lesson.class);
        return namedQuery.getResultList();
    }
    
    public List<Lesson> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Lesson.getById", Lesson.class).setParameter("id", id);
        List<Lesson> result=namedQuery.getResultList();   
        return result;
    }
    
}


