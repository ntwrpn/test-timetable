package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Subject;
import com.java.repository.SubjectRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Override
    public Subject save(Subject obj) {
        return SubjectRepository.save(obj);
    }

    @Override
    public Subject update(Subject obj) {
        return SubjectRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        SubjectRepository.delete(id);
    }

    @Autowired
    private SubjectRepository SubjectRepository;

    @Override
    public List<Subject> getAll(){
        return SubjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getById(UUID id) {
        return SubjectRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Subject.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

