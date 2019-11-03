

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Corps;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CorpsService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Corps add(Corps obj){
        em.getTransaction().begin();
        Corps objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Corps get(int id){
        return em.find(Corps.class, id);
    }

    public void update(Corps obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Corps> getAll(){
        TypedQuery<Corps> namedQuery = em.createNamedQuery("Corps.getAll", Corps.class);
        return namedQuery.getResultList();
    }
    
    public List<Corps> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Corps.getById", Corps.class).setParameter("id", id);
        List<Corps> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Corps.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


