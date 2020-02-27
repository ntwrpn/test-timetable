

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.WeekCount;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class WeekCountService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public WeekCount add(WeekCount obj){
        em.getTransaction().begin();
        WeekCount objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public WeekCount get(int id){
        return em.find(WeekCount.class, id);
    }

    public void update(WeekCount obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<WeekCount> getAll(){
        TypedQuery<WeekCount> namedQuery = em.createNamedQuery("WeekCount.getAll", WeekCount.class);
        return namedQuery.getResultList();
    }
    
    public List<WeekCount> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("WeekCount.getById", WeekCount.class).setParameter("id", id);
        List<WeekCount> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : WeekCount.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


