

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.LecternType;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class LecternTypeService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public LecternType add(LecternType obj){
        em.getTransaction().begin();
        LecternType objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public LecternType get(int id){
        return em.find(LecternType.class, id);
    }

    public void update(LecternType obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<LecternType> getAll(){
        TypedQuery<LecternType> namedQuery = em.createNamedQuery("LecternType.getAll", LecternType.class);
        return namedQuery.getResultList();
    }
    
    public List<LecternType> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("LecternType.getById", LecternType.class).setParameter("id", id);
        List<LecternType> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : LecternType.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


