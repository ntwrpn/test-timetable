

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Role;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    public EntityManager em = EntityWorker.GetEntityWorker();


    public Role add(Role obj){
        em.getTransaction().begin();
        Role objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Role get(int id){
        return em.find(Role.class, id);
    }

    public void update(Role obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Role> getAll(){
        TypedQuery<Role> namedQuery = em.createNamedQuery("Role.getAll", Role.class);
        return namedQuery.getResultList();
    }
    
    public List<Role> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Role.getById", Role.class).setParameter("id", id);
        List<Role> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Role.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


