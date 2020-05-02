package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.java.domain.Occupation;
import com.java.repository.OccupationRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.OccupationService;

@Service
public class OccupationServiceImpl implements OccupationService {

    @Autowired
    private OccupationRepository occupationRepository;

    @Override
    public Occupation save(Occupation obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Occupation>> violations = validator.validate(obj);
        if(violations.size()!=0){
            throw  new ConstraintViolationException(violations);
        }
        return occupationRepository.save(obj);
    }

    @Override
    public Occupation update(Occupation obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Occupation>> violations = validator.validate(obj);
        if(violations.size()!=0){
            throw  new ConstraintViolationException(violations);
        }
        return occupationRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        occupationRepository.deleteById(id);
    }


    @Override
    public List<Occupation> getAll() {
        return occupationRepository.findAll();
    }

    @Override
    public Optional<Occupation> getById(UUID id) {
        return occupationRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Occupation.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }

    @Override
    public List<Occupation> findBySymbol(String symbol) {
        return occupationRepository.findBySymbol(symbol);
    }

    @Override
    public List<Occupation> findByValue(String value) {
        return occupationRepository.findByValue(value);
    }
}

