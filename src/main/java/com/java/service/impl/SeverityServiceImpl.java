package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Severity;
import com.java.repository.SeverityRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.SeverityService;

@Service
public class SeverityServiceImpl implements SeverityService {

    @Autowired
    private SeverityRepository severityRepository;

    @Override
    public Severity save(Severity obj) {
        return severityRepository.save(obj);
    }

    @Override
    public Severity update(Severity obj) {
        return severityRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        severityRepository.deleteById(id);
    }


    @Override
    public List<Severity> getAll() {
        return severityRepository.findAll();
    }

    @Override
    public Optional<Severity> getById(UUID id) {
        return severityRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Severity.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

