package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Course;
import com.java.repository.CourseRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public Course save(Course obj) {
        return CourseRepository.save(obj);
    }

    @Override
    public Course update(Course obj) {
        return CourseRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        CourseRepository.deleteById(id);
    }

    @Autowired
    private CourseRepository CourseRepository;

    @Override
    public List<Course> getAll(){
        return CourseRepository.findAll();
    }

    @Override
    public Optional<Course> getById(UUID id) {
        return CourseRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Course.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

