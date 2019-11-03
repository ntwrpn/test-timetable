

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Classroom;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Classroom add(Classroom obj){
        em.getTransaction().begin();
        Classroom objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Classroom get(int id){
        return em.find(Classroom.class, id);
    }

    public void update(Classroom obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Classroom> getAll(){
        TypedQuery<Classroom> namedQuery = em.createNamedQuery("Classroom.getAll", Classroom.class);
        return namedQuery.getResultList();
    }
    
    public List<Classroom> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Classroom.getById", Classroom.class).setParameter("id", id);
        List<Classroom> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Classroom.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


