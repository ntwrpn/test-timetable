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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Faculty save(Faculty obj) {
        return facultyRepository.save(obj);
    }

    @Override
    public Faculty update(Faculty obj) {
        return facultyRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        facultyRepository.deleteById(id);
    }


    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Optional<Faculty> getById(UUID id) {
        return facultyRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Faculty.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

