package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.LearningSeverityList;
import com.java.repository.LearningSeverityListRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.LearningSeverityListService;

@Service
public class LearningSeverityListServiceImpl implements LearningSeverityListService {

    @Override
    public LearningSeverityList save(LearningSeverityList obj) {
        return LearningSeverityListRepository.save(obj);
    }

    @Override
    public LearningSeverityList update(LearningSeverityList obj) {
        return LearningSeverityListRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        LearningSeverityListRepository.delete(id);
    }

    @Autowired
    private LearningSeverityListRepository LearningSeverityListRepository;

    @Override
    public List<LearningSeverityList> getAll(){
        return LearningSeverityListRepository.findAll();
    }

    @Override
    public Optional<LearningSeverityList> getById(UUID id) {
        return LearningSeverityListRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : LearningSeverityList.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

