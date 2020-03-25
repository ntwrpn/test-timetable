package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.OccupationCounter;
import com.java.repository.OccupationCounterRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.OccupationCounterService;

@Service
public class OccupationCounterServiceImpl implements OccupationCounterService {

    @Autowired
    private OccupationCounterRepository occupationCounterRepository;

    @Override
    public OccupationCounter save(OccupationCounter obj) {
        return occupationCounterRepository.save(obj);
    }

    @Override
    public OccupationCounter update(OccupationCounter obj) {
        return occupationCounterRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        occupationCounterRepository.deleteById(id);
    }


    @Override
    public List<OccupationCounter> getAll() {
        return occupationCounterRepository.findAll();
    }

    @Override
    public Optional<OccupationCounter> getById(UUID id) {
        return occupationCounterRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(OccupationCounter.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

