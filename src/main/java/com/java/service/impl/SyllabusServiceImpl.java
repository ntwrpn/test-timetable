package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Syllabus;
import com.java.repository.SyllabusRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SyllabusService;

@Service
public class SyllabusServiceImpl implements SyllabusService {

    @Override
    public Syllabus save(Syllabus obj) {
        return SyllabusRepository.save(obj);
    }

    @Override
    public Syllabus update(Syllabus obj) {
        return SyllabusRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        SyllabusRepository.delete(id);
    }

    @Autowired
    private SyllabusRepository SyllabusRepository;

    @Override
    public List<Syllabus> getAll(){
        return SyllabusRepository.findAll();
    }

    @Override
    public Optional<Syllabus> getById(UUID id) {
        return SyllabusRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Syllabus.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

