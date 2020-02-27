

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Schedule;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Schedule add(Schedule obj){
        em.getTransaction().begin();
        Schedule objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Schedule get(int id){
        return em.find(Schedule.class, id);
    }

    public void update(Schedule obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Schedule> getAll(){
        TypedQuery<Schedule> namedQuery = em.createNamedQuery("Schedule.getAll", Schedule.class);
        return namedQuery.getResultList();
    }
    
    public List<Schedule> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Schedule.getById", Schedule.class).setParameter("id", id);
        List<Schedule> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Schedule.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


