

package com.java.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import com.java.domain.StudyPlan;
import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class StudyPlanService {
    public EntityManager em = EntityWorker.GetEntityWorker();

    public StudyPlan add(StudyPlan obj){
        em.getTransaction().begin();
        StudyPlan objFromDB = em.merge(obj);
        em.getTransaction().commit();
        return objFromDB;
    }

    public void delete(int id){
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public StudyPlan get(int id){
        return em.find(StudyPlan.class, id);
    }

    public void update(StudyPlan obj){
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public List<StudyPlan> getAll(){
        TypedQuery<StudyPlan> namedQuery = em.createNamedQuery("StudyPlan.getAll", StudyPlan.class);
        return namedQuery.getResultList();
    }
    
    public List<StudyPlan> getById(int id){
        TypedQuery namedQuery = em.createNamedQuery("StudyPlan.getById", StudyPlan.class).setParameter("id", id);
        List<StudyPlan> result=namedQuery.getResultList();   
        return result;
    }
    
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : StudyPlan.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}


