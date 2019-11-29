

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Flow;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class FlowService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Flow add(Flow obj){
        em.getTransaction().begin();
        Flow objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Flow get(int id){
        return em.find(Flow.class, id);
    }

    public void update(Flow obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Flow> getAll(){
        TypedQuery<Flow> namedQuery = em.createNamedQuery("Flow.getAll", Flow.class);
        return namedQuery.getResultList();
    }
    
    public List<Flow> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Flow.getById", Flow.class).setParameter("id", id);
        List<Flow> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Flow.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


