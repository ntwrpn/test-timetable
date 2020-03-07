package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.StudyPlanStatus;
import com.java.repository.StudyPlanStatusRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.StudyPlanStatusService;

@Service
public class StudyPlanStatusServiceImpl implements StudyPlanStatusService {

    @Override
    public StudyPlanStatus save(StudyPlanStatus obj) {
        return StudyPlanStatusRepository.save(obj);
    }

    @Override
    public StudyPlanStatus update(StudyPlanStatus obj) {
        return StudyPlanStatusRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        StudyPlanStatusRepository.delete(id);
    }

    @Autowired
    private StudyPlanStatusRepository StudyPlanStatusRepository;

    @Override
    public List<StudyPlanStatus> getAll(){
        return StudyPlanStatusRepository.findAll();
    }

    @Override
    public Optional<StudyPlanStatus> getById(UUID id) {
        return StudyPlanStatusRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : StudyPlanStatus.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

