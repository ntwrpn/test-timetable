package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Subgroup;
import com.java.repository.SubgroupRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.SubgroupService;

@Service
public class SubgroupServiceImpl implements SubgroupService {

    @Autowired
    private SubgroupRepository subgroupRepository;

    @Override
    public Subgroup save(Subgroup obj) {
        return subgroupRepository.save(obj);
    }

    @Override
    public Subgroup update(Subgroup obj) {
        return subgroupRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        subgroupRepository.deleteById(id);
    }


    @Override
    public List<Subgroup> getAll() {
        return subgroupRepository.findAll();
    }

    @Override
    public Optional<Subgroup> getById(UUID id) {
        return subgroupRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Subgroup.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

