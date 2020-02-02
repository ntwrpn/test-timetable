

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.Course;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public Course add(Course obj){
        em.getTransaction().begin();
        Course objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Course get(int id){
        return em.find(Course.class, id);
    }

    public void update(Course obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<Course> getAll(){
        TypedQuery<Course> namedQuery = em.createNamedQuery("Course.getAll", Course.class);
        return namedQuery.getResultList();
    }
    
    public List<Course> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("Course.getById", Course.class).setParameter("id", id);
        List<Course> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Course.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


