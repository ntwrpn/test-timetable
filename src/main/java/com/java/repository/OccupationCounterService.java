

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.OccupationCounter;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class OccupationCounterService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public OccupationCounter add(OccupationCounter obj){
        em.getTransaction().begin();
        OccupationCounter objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public OccupationCounter get(int id){
        return em.find(OccupationCounter.class, id);
    }

    public void update(OccupationCounter obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<OccupationCounter> getAll(){
        TypedQuery<OccupationCounter> namedQuery = em.createNamedQuery("OccupationCounter.getAll", OccupationCounter.class);
        return namedQuery.getResultList();
    }
    
    public List<OccupationCounter> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("OccupationCounter.getById", OccupationCounter.class).setParameter("id", id);
        List<OccupationCounter> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : OccupationCounter.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


