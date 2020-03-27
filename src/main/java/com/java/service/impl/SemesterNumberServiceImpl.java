package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.SemesterNumber;
import com.java.repository.SemesterNumberRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.SemesterNumberService;

@Service
public class SemesterNumberServiceImpl implements SemesterNumberService {

    @Autowired
    private SemesterNumberRepository semesterNumberRepository;

    @Override
    public SemesterNumber save(SemesterNumber obj) {
        return semesterNumberRepository.save(obj);
    }

    @Override
    public SemesterNumber update(SemesterNumber obj) {
        return semesterNumberRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        semesterNumberRepository.deleteById(id);
    }


    @Override
    public List<SemesterNumber> getAll() {
        return semesterNumberRepository.findAll();
    }

    @Override
    public Optional<SemesterNumber> getById(UUID id) {
        return semesterNumberRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(SemesterNumber.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

