

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Severity;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SeverityService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Severity add(Severity obj){
        em.getTransaction().begin();
        Severity objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Severity get(int id){
        return em.find(Severity.class, id);
    }

    public void update(Severity obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Severity> getAll(){
        TypedQuery<Severity> namedQuery = em.createNamedQuery("Severity.getAll", Severity.class);
        return namedQuery.getResultList();
    }
    
    public List<Severity> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Severity.getById", Severity.class).setParameter("id", id);
        List<Severity> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Severity.class.getDeclaredFields()) {
            if (field.getName()!="flow"){
                obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
            }
        }
        return obj;
    }
}


