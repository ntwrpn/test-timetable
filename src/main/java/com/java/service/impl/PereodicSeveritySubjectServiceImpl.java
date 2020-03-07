package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.PereodicSeveritySubject;
import com.java.repository.PereodicSeveritySubjectRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.PereodicSeveritySubjectService;

@Service
public class PereodicSeveritySubjectServiceImpl implements PereodicSeveritySubjectService {

    @Override
    public PereodicSeveritySubject save(PereodicSeveritySubject obj) {
        return PereodicSeveritySubjectRepository.save(obj);
    }

    @Override
    public PereodicSeveritySubject update(PereodicSeveritySubject obj) {
        return PereodicSeveritySubjectRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        PereodicSeveritySubjectRepository.deleteById(id);
    }

    @Autowired
    private PereodicSeveritySubjectRepository PereodicSeveritySubjectRepository;

    @Override
    public List<PereodicSeveritySubject> getAll() {
        return PereodicSeveritySubjectRepository.findAll();
    }

    @Override
    public Optional<PereodicSeveritySubject> getById(UUID id) {
        return PereodicSeveritySubjectRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : PereodicSeveritySubject.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

