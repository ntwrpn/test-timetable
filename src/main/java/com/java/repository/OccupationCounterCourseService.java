

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.OccupationCounterCourse;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class OccupationCounterCourseService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public OccupationCounterCourse add(OccupationCounterCourse obj){
        em.getTransaction().begin();
        OccupationCounterCourse objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public OccupationCounterCourse get(int id){
        return em.find(OccupationCounterCourse.class, id);
    }

    public void update(OccupationCounterCourse obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<OccupationCounterCourse> getAll(){
        TypedQuery<OccupationCounterCourse> namedQuery = em.createNamedQuery("OccupationCounterCourse.getAll", OccupationCounterCourse.class);
        return namedQuery.getResultList();
    }
    
    public List<OccupationCounterCourse> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("OccupationCounterCourse.getById", OccupationCounterCourse.class).setParameter("id", id);
        List<OccupationCounterCourse> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : OccupationCounterCourse.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


