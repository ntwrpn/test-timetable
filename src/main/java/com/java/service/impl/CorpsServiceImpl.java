package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Corps;
import com.java.repository.CorpsRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.CorpsService;

@Service
public class CorpsServiceImpl implements CorpsService {

    @Autowired
    private CorpsRepository corpsRepository;

    @Override
    public Corps save(Corps obj) {
        return corpsRepository.save(obj);
    }

    @Override
    public Corps update(Corps obj) {
        return corpsRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        corpsRepository.deleteById(id);
    }


    @Override
    public List<Corps> getAll() {
        return corpsRepository.findAll();
    }

    @Override
    public Optional<Corps> getById(UUID id) {
        return corpsRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Corps.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

