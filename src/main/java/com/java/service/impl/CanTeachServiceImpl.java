package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.CanTeach;
import com.java.repository.CanTeachRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.CanTeachService;

@Service
public class CanTeachServiceImpl implements CanTeachService {

    @Autowired
    private CanTeachRepository canTeachRepository;

    @Override
    public CanTeach save(CanTeach obj) {
        return canTeachRepository.save(obj);
    }

    @Override
    public CanTeach update(CanTeach obj) {
        return canTeachRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        canTeachRepository.deleteById(id);
    }


    @Override
    public List<CanTeach> getAll() {
        return canTeachRepository.findAll();
    }

    @Override
    public Optional<CanTeach> getById(UUID id) {
        return canTeachRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(CanTeach.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

