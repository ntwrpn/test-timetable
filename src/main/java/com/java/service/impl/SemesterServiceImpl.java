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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.SemesterService;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Semester save(Semester obj) {
        return semesterRepository.save(obj);
    }

    @Override
    public Semester update(Semester obj) {
        return semesterRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        semesterRepository.deleteById(id);
    }


    @Override
    public List<Semester> getAll() {
        return semesterRepository.findAll();
    }

    @Override
    public Optional<Semester> getById(UUID id) {
        return semesterRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Semester.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

