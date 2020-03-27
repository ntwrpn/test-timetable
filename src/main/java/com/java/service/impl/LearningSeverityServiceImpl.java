package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.LearningSeverity;
import com.java.repository.LearningSeverityRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.LearningSeverityService;

@Service
public class LearningSeverityServiceImpl implements LearningSeverityService {

    @Autowired
    private LearningSeverityRepository learningSeverityRepository;

    @Override
    public LearningSeverity save(LearningSeverity obj) {
        return learningSeverityRepository.save(obj);
    }

    @Override
    public LearningSeverity update(LearningSeverity obj) {
        return learningSeverityRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        learningSeverityRepository.deleteById(id);
    }


    @Override
    public List<LearningSeverity> getAll() {
        return learningSeverityRepository.findAll();
    }

    @Override
    public Optional<LearningSeverity> getById(UUID id) {
        return learningSeverityRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(LearningSeverity.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

