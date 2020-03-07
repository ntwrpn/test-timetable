package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Severity;
import com.java.repository.SeverityRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SeverityService;

@Service
public class SeverityServiceImpl implements SeverityService {

    @Autowired
    private SeverityRepository severityRepository;

    @Override
    public Severity save(Severity obj) {
        return severityRepository.save(obj);
    }

    @Override
    public Severity update(Severity obj) {
        return severityRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        severityRepository.deleteById(id);
    }

    @Override
    public List<Severity> getAll() {
        return severityRepository.findAll();
    }

    @Override
    public Optional<Severity> getById(UUID id) {
        return severityRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Severity.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

