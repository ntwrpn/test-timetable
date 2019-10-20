

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Users;
public class UsersService {
    public EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public Users add(Users obj){
        em.getTransaction().begin();
        Users objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Users get(int id){
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
    
    public List<Users> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Users.getById", Users.class).setParameter("id", id);
        List<Users> result=namedQuery.getResultList();   
        return result;
    }
    
}


