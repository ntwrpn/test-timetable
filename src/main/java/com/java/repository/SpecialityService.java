

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Speciality;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SpecialityService {
    public EntityManager em = EntityWorker.GetEntityWorker();


    public Speciality add(Speciality obj){
        em.getTransaction().begin();
        Speciality objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Speciality get(int id){
        return em.find(Speciality.class, id);
    }

    public void update(Speciality obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Speciality> getAll(){
        TypedQuery<Speciality> namedQuery = em.createNamedQuery("Speciality.getAll", Speciality.class);
        return namedQuery.getResultList();
    }
    
    public List<Speciality> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Speciality.getById", Speciality.class).setParameter("id", id);
        List<Speciality> result=namedQuery.getResultList();   
        return result;
    }

    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Speciality.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


