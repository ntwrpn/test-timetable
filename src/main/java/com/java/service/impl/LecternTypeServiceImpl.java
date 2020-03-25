package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.LecternType;
import com.java.repository.LecternTypeRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.LecternTypeService;

@Service
public class LecternTypeServiceImpl implements LecternTypeService {

    @Autowired
    private LecternTypeRepository lecternTypeRepository;

    @Override
    public LecternType save(LecternType obj) {
        return lecternTypeRepository.save(obj);
    }

    @Override
    public LecternType update(LecternType obj) {
        return lecternTypeRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        lecternTypeRepository.deleteById(id);
    }


    @Override
    public List<LecternType> getAll() {
        return lecternTypeRepository.findAll();
    }

    @Override
    public Optional<LecternType> getById(UUID id) {
        return lecternTypeRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(LecternType.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

