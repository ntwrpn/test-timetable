package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Access;
import com.java.repository.AccessRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.AccessService;

@Service
public class AccessServiceImpl implements AccessService {

    @Autowired
    private AccessRepository accessRepository;

    @Override
    public Access save(Access obj) {
        return accessRepository.save(obj);
    }

    @Override
    public Access update(Access obj) {
        return accessRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        accessRepository.deleteById(id);
    }


    @Override
    public List<Access> getAll() {
        return accessRepository.findAll();
    }

    @Override
    public Optional<Access> getById(UUID id) {
        return accessRepository.findById(id);
    }
    
    @Override
    public List<Access> getByUserRoles(String role) {
        return accessRepository.getByUserRoles(role);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Access.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

