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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public Classroom save(Classroom obj) {
        return classroomRepository.save(obj);
    }

    @Override
    public Classroom update(Classroom obj) {
        return classroomRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        classroomRepository.deleteById(id);
    }


    @Override
    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    @Override
    public Optional<Classroom> getById(UUID id) {
        return classroomRepository.findById(id);
    }
    
    @Override
    public List<Classroom> getByCorps(UUID id) {
        return classroomRepository.findByCorps(id);
    }
    
    @Override
    public List<Classroom> getByLectern(UUID id) {
        return classroomRepository.findByLectern(id);
    }


    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Classroom.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

