package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Faculty;
import com.java.repository.FacultyRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Override
    public Faculty save(Faculty obj) {
        return FacultyRepository.save(obj);
    }

    @Override
    public Faculty update(Faculty obj) {
        return FacultyRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        FacultyRepository.delete(id);
    }

    @Autowired
    private FacultyRepository FacultyRepository;

    @Override
    public List<Faculty> getAll(){
        return FacultyRepository.findAll();
    }

    @Override
    public Optional<Faculty> getById(UUID id) {
        return FacultyRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Faculty.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

