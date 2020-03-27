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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.ClassroomTypeService;

@Service
public class ClassroomTypeServiceImpl implements ClassroomTypeService {

    @Autowired
    private ClassroomTypeRepository classroomTypeRepository;

    @Override
    public ClassroomType save(ClassroomType obj) {
        return classroomTypeRepository.save(obj);
    }

    @Override
    public ClassroomType update(ClassroomType obj) {
        return classroomTypeRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        classroomTypeRepository.deleteById(id);
    }


    @Override
    public List<ClassroomType> getAll() {
        return classroomTypeRepository.findAll();
    }

    @Override
    public Optional<ClassroomType> getById(UUID id) {
        return classroomTypeRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(ClassroomType.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

