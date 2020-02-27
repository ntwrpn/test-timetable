

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.SeveritySubject;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SeveritySubjectService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public SeveritySubject add(SeveritySubject obj){
        em.getTransaction().begin();
        SeveritySubject objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public SeveritySubject get(int id){
        return em.find(SeveritySubject.class, id);
    }

    public void update(SeveritySubject obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<SeveritySubject> getAll(){
        TypedQuery<SeveritySubject> namedQuery = em.createNamedQuery("SeveritySubject.getAll", SeveritySubject.class);
        return namedQuery.getResultList();
    }
    
    public List<SeveritySubject> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("SeveritySubject.getById", SeveritySubject.class).setParameter("id", id);
        List<SeveritySubject> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : SeveritySubject.class.getDeclaredFields()) {
            if (field.getName()!="flow"){
                obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
            }
        }
        return obj;
    }
}


