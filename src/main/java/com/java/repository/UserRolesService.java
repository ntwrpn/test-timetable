

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.UserRoles;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;

public class UserRolesService {
    public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public UserRoles add(UserRoles obj){
        em.getTransaction().begin();
        UserRoles objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public UserRoles get(int id){
        return em.find(UserRoles.class, id);
    }

    public void update(UserRoles obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<UserRoles> getAll(){
        TypedQuery<UserRoles> namedQuery = em.createNamedQuery("UserRoles.getAll", UserRoles.class);
        return namedQuery.getResultList();
    }
    
    public List<UserRoles> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("UserRoles.getById", UserRoles.class).setParameter("user_role_id", id);
        List<UserRoles> result=namedQuery.getResultList();  
        return result;
    }

    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : UserRoles.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
    
}


