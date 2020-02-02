

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Week;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class WeekService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Week add(Week obj){
        em.getTransaction().begin();
        Week objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Week get(int id){
        return em.find(Week.class, id);
    }

    public void update(Week obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Week> getAll(){
        TypedQuery<Week> namedQuery = em.createNamedQuery("Week.getAll", Week.class);
        return namedQuery.getResultList();
    }
    
    public List<Week> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Week.getById", Week.class).setParameter("id", id);
        List<Week> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Week.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


