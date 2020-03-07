package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Teacher;
import com.java.repository.TeacherRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Override
    public Teacher save(Teacher obj) {
        return TeacherRepository.save(obj);
    }

    @Override
    public Teacher update(Teacher obj) {
        return TeacherRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        TeacherRepository.delete(id);
    }

    @Autowired
    private TeacherRepository TeacherRepository;

    @Override
    public List<Teacher> getAll(){
        return TeacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getById(UUID id) {
        return TeacherRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Teacher.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

