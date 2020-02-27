

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Semester;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Semester add(Semester obj){
        em.getTransaction().begin();
        Semester objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Semester get(int id){
        return em.find(Semester.class, id);
    }

    public void update(Semester obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Semester> getAll(){
        TypedQuery<Semester> namedQuery = em.createNamedQuery("Semester.getAll", Semester.class);
        return namedQuery.getResultList();
    }
    
    public List<Semester> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Semester.getById", Semester.class).setParameter("id", id);
        List<Semester> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Semester.class.getDeclaredFields()) {
            if (field.getName()!="flow"){
                obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
            }
        }
        return obj;
    }
}


