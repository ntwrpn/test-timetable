package com.java.service.impl;

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

    @Autowired
    private LearningSeverityListRepository learningSeverityListRepository;

    @Override
    public LearningSeverityList save(LearningSeverityList obj) {
        return learningSeverityListRepository.save(obj);
    }

    @Override
    public LearningSeverityList update(LearningSeverityList obj) {
        return learningSeverityListRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        learningSeverityListRepository.deleteById(id);
    }

    @Override
    public List<LearningSeverityList> getAll() {
        return learningSeverityListRepository.findAll();
    }

    @Override
    public Optional<LearningSeverityList> getById(UUID id) {
        return learningSeverityListRepository.findById(id);
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

