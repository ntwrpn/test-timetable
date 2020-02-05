

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Occupation;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class OccupationService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Occupation add(Occupation obj){
        em.getTransaction().begin();
        Occupation objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Occupation get(int id){
        return em.find(Occupation.class, id);
    }

    public void update(Occupation obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Occupation> getAll(){
        TypedQuery<Occupation> namedQuery = em.createNamedQuery("Occupation.getAll", Occupation.class);
        return namedQuery.getResultList();
    }
    
    public List<Occupation> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Occupation.getById", Occupation.class).setParameter("id", id);
        List<Occupation> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Occupation.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


