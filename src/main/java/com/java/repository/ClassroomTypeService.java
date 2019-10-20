

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.ClassroomType;
import org.springframework.stereotype.Service;

@Service
public class ClassroomTypeService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public ClassroomType add(ClassroomType obj){
        em.getTransaction().begin();
        ClassroomType objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public ClassroomType get(int id){
        return em.find(ClassroomType.class, id);
    }

    public void update(ClassroomType obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<ClassroomType> getAll(){
        TypedQuery<ClassroomType> namedQuery = em.createNamedQuery("ClassroomType.getAll", ClassroomType.class);
        return namedQuery.getResultList();
    }
    
    public List<ClassroomType> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("ClassroomType.getById", ClassroomType.class).setParameter("id", id);
        List<ClassroomType> result=namedQuery.getResultList();   
        return result;
    }
    
}


