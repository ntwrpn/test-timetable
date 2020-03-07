package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Semester;
import com.java.repository.SemesterRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SemesterService;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Override
    public Semester save(Semester obj) {
        return SemesterRepository.save(obj);
    }

    @Override
    public Semester update(Semester obj) {
        return SemesterRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        SemesterRepository.delete(id);
    }

    @Autowired
    private SemesterRepository SemesterRepository;

    @Override
    public List<Semester> getAll(){
        return SemesterRepository.findAll();
    }

    @Override
    public Optional<Semester> getById(UUID id) {
        return SemesterRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Semester.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

