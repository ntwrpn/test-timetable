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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course save(Course obj) {
        return courseRepository.save(obj);
    }

    @Override
    public Course update(Course obj) {
        return courseRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        courseRepository.deleteById(id);
    }


    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getById(UUID id) {
        return courseRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Course.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

