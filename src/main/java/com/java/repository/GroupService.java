

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Groups;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Groups add(Groups obj){
        em.getTransaction().begin();
        Groups objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Groups get(int id){
        return em.find(Groups.class, id);
    }

    public void update(Groups obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Groups> getAll(){
        TypedQuery<Groups> namedQuery = em.createNamedQuery("Groups.getAll", Groups.class);
        return namedQuery.getResultList();
    }
    
    public List<Groups> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Groups.getById", Groups.class).setParameter("id", id);
        List<Groups> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Groups.class.getDeclaredFields()) {
            if (field.getName()!="flow" && field.getName()!="subgroup"){
                obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
            }
        }
        return obj;
    }
}


