

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Lectern;
import java.io.StringWriter;
import java.lang.reflect.Field;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;

@Service
public class LecternService {
    public EntityWorker wrk = new EntityWorker();
    public EntityManager em = wrk.GetEntityWorker();
    public Lectern add(Lectern obj){
        em.getTransaction().begin();
        Lectern objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Lectern get(int id){
        return em.find(Lectern.class, id);
    }

    public void update(Lectern obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Lectern> getAll(){
        TypedQuery<Lectern> namedQuery = em.createNamedQuery("Lectern.getAll", Lectern.class);
        return namedQuery.getResultList();
    }
    
    public List<Lectern> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Lectern.getById", Lectern.class).setParameter("id", id);
        List<Lectern> result=namedQuery.getResultList();   
        return result;
    }
    

    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Lectern.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
    
}


