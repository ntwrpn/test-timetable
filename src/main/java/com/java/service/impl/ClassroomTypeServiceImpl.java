package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.ClassroomType;
import com.java.repository.ClassroomTypeRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.ClassroomTypeService;

@Service
public class ClassroomTypeServiceImpl implements ClassroomTypeService {

    @Override
    public ClassroomType save(ClassroomType obj) {
        return ClassroomTypeRepository.save(obj);
    }

    @Override
    public ClassroomType update(ClassroomType obj) {
        return ClassroomTypeRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        ClassroomTypeRepository.delete(id);
    }

    @Autowired
    private ClassroomTypeRepository ClassroomTypeRepository;

    @Override
    public List<ClassroomType> getAll(){
        return ClassroomTypeRepository.findAll();
    }

    @Override
    public Optional<ClassroomType> getById(UUID id) {
        return ClassroomTypeRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : ClassroomType.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

