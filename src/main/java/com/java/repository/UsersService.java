

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Users;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
public class UsersService {
    public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public Users add(Users obj){
        em.getTransaction().begin();
        Users objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(Integer id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Users get(Integer id){
        return em.find(Users.class, id);
    }

    public void update(Users obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Users> getAll(){
        TypedQuery<Users> namedQuery = em.createNamedQuery("Users.getAll", Users.class);
        return namedQuery.getResultList();
    }
    
    public Users getById(Integer id){
        TypedQuery namedQuery = em.createNamedQuery("Users.getById", Users.class).setParameter("id", id);
        List<Users> result=namedQuery.getResultList(); 
        return result.get(0);
    }

    public List<Users> getByEnabled(Boolean enabled){
        TypedQuery namedQuery = em.createNamedQuery("Users.getByEnabled", Users.class).setParameter("enabled", enabled);
        List<Users> result=namedQuery.getResultList();   
        return result;
    }
    
    public Users getByName(String username){
        TypedQuery namedQuery = em.createNamedQuery("Users.getByName", Users.class).setParameter("username", username);
        List<Users> result=namedQuery.getResultList();   
        return result.get(0);
    }

    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Users.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
    
}


