package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.PereodicSeverity;
import com.java.repository.PereodicSeverityRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.PereodicSeverityService;

@Service
public class PereodicSeverityServiceImpl implements PereodicSeverityService {

    @Autowired
    private PereodicSeverityRepository pereodicSeverityRepository;

    @Override
    public PereodicSeverity save(PereodicSeverity obj) {
        return pereodicSeverityRepository.save(obj);
    }

    @Override
    public PereodicSeverity update(PereodicSeverity obj) {
        return pereodicSeverityRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        pereodicSeverityRepository.deleteById(id);
    }

    @Override
    public List<PereodicSeverity> getAll() {
        return pereodicSeverityRepository.findAll();
    }

    @Override
    public Optional<PereodicSeverity> getById(UUID id) {
        return pereodicSeverityRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : PereodicSeverity.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

