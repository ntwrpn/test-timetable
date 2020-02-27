

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Turn;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TurnService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Turn add(Turn obj){
        em.getTransaction().begin();
        Turn objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Turn get(int id){
        return em.find(Turn.class, id);
    }

    public void update(Turn obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Turn> getAll(){
        TypedQuery<Turn> namedQuery = em.createNamedQuery("Turn.getAll", Turn.class);
        return namedQuery.getResultList();
    }
    
    public List<Turn> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Turn.getById", Turn.class).setParameter("id", id);
        List<Turn> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Turn.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


