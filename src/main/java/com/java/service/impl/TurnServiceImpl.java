package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Turn;
import com.java.repository.TurnRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.TurnService;

@Service
public class TurnServiceImpl implements TurnService {

    @Autowired
    private TurnRepository turnRepository;

    @Override
    public Turn save(Turn obj) {
        return turnRepository.save(obj);
    }

    @Override
    public Turn update(Turn obj) {
        return turnRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        turnRepository.deleteById(id);
    }


    @Override
    public List<Turn> getAll() {
        return turnRepository.findAll();
    }

    @Override
    public Optional<Turn> getById(UUID id) {
        return turnRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Turn.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

