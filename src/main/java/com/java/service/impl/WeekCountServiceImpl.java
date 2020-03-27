package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.WeekCount;
import com.java.repository.WeekCountRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.WeekCountService;

@Service
public class WeekCountServiceImpl implements WeekCountService {

    @Autowired
    private WeekCountRepository weekCountRepository;

    @Override
    public WeekCount save(WeekCount obj) {
        return weekCountRepository.save(obj);
    }

    @Override
    public WeekCount update(WeekCount obj) {
        return weekCountRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        weekCountRepository.deleteById(id);
    }


    @Override
    public List<WeekCount> getAll() {
        return weekCountRepository.findAll();
    }

    @Override
    public Optional<WeekCount> getById(UUID id) {
        return weekCountRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(WeekCount.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

