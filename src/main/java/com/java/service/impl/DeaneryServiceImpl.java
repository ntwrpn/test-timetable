package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Deanery;
import com.java.repository.DeaneryRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.DeaneryService;

@Service
public class DeaneryServiceImpl implements DeaneryService {

    @Autowired
    private DeaneryRepository deaneryRepository;

    @Override
    public Deanery save(Deanery obj) {
        return deaneryRepository.save(obj);
    }

    @Override
    public Deanery update(Deanery obj) {
        return deaneryRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        deaneryRepository.deleteById(id);
    }


    @Override
    public List<Deanery> getAll() {
        return deaneryRepository.findAll();
    }

    @Override
    public Optional<Deanery> getById(UUID id) {
        return deaneryRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Deanery.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

