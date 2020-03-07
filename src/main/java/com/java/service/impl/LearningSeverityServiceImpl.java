package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.LearningSeverity;
import com.java.repository.LearningSeverityRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.LearningSeverityService;

@Service
public class LearningSeverityServiceImpl implements LearningSeverityService {

    @Override
    public LearningSeverity save(LearningSeverity obj) {
        return LearningSeverityRepository.save(obj);
    }

    @Override
    public LearningSeverity update(LearningSeverity obj) {
        return LearningSeverityRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        LearningSeverityRepository.delete(id);
    }

    @Autowired
    private LearningSeverityRepository LearningSeverityRepository;

    @Override
    public List<LearningSeverity> getAll(){
        return LearningSeverityRepository.findAll();
    }

    @Override
    public Optional<LearningSeverity> getById(UUID id) {
        return LearningSeverityRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : LearningSeverity.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

