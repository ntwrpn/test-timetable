

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.TypeOfLesson;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TypeOfLessonService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public TypeOfLesson add(TypeOfLesson obj){
        em.getTransaction().begin();
        TypeOfLesson objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public TypeOfLesson get(int id){
        return em.find(TypeOfLesson.class, id);
    }

    public void update(TypeOfLesson obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<TypeOfLesson> getAll(){
        TypedQuery<TypeOfLesson> namedQuery = em.createNamedQuery("TypeOfLesson.getAll", TypeOfLesson.class);
        return namedQuery.getResultList();
    }
    
    public List<TypeOfLesson> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("TypeOfLesson.getById", TypeOfLesson.class).setParameter("id", id);
        List<TypeOfLesson> result=namedQuery.getResultList();   
        return result;
    }
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : TypeOfLesson.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


