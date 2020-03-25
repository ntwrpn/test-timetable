package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.LearningSeverityList;
import com.java.repository.LearningSeverityListRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.LearningSeverityListService;

@Service
public class LearningSeverityListServiceImpl implements LearningSeverityListService {

    @Autowired
    private LearningSeverityListRepository learningSeverityListRepository;

    @Override
    public LearningSeverityList save(LearningSeverityList obj) {
        return learningSeverityListRepository.save(obj);
    }

    @Override
    public LearningSeverityList update(LearningSeverityList obj) {
        return learningSeverityListRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        learningSeverityListRepository.deleteById(id);
    }


    @Override
    public List<LearningSeverityList> getAll() {
        return learningSeverityListRepository.findAll();
    }

    @Override
    public Optional<LearningSeverityList> getById(UUID id) {
        return learningSeverityListRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(LearningSeverityList.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

