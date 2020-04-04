package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Year;
import com.java.repository.YearRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.YearService;

@Service
public class YearServiceImpl implements YearService {

    @Autowired
    private YearRepository yearRepository;

    @Override
    public Year save(Year obj) {
        return yearRepository.save(obj);
    }

    @Override
    public Year update(Year obj) {
        return yearRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        yearRepository.deleteById(id);
    }


    @Override
    public List<Year> getAll() {
        return yearRepository.findAll();
    }

    @Override
    public Optional<Year> getById(UUID id) {
        return yearRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Year.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

