package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Classroom;
import com.java.repository.ClassroomRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Override
    public Classroom save(Classroom obj) {
        return ClassroomRepository.save(obj);
    }

    @Override
    public Classroom update(Classroom obj) {
        return ClassroomRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        ClassroomRepository.deleteById(id);
    }

    @Autowired
    private ClassroomRepository ClassroomRepository;

    @Override
    public List<Classroom> getAll(){
        return ClassroomRepository.findAll();
    }

    @Override
    public Optional<Classroom> getById(UUID id) {
        return ClassroomRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Classroom.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

