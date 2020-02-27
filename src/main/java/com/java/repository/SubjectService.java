

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Subject;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Subject add(Subject obj){
        em.getTransaction().begin();
        Subject objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Subject get(int id){
        return em.find(Subject.class, id);
    }

    public void update(Subject obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Subject> getAll(){
        TypedQuery<Subject> namedQuery = em.createNamedQuery("Subject.getAll", Subject.class);
        return namedQuery.getResultList();
    }
    
    public List<Subject> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Subject.getById", Subject.class).setParameter("id", id);
        List<Subject> result=namedQuery.getResultList();   
        return result;
    }

    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Subject.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


