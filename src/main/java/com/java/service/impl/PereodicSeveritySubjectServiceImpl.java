package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.PereodicSeveritySubject;
import com.java.repository.PereodicSeveritySubjectRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.PereodicSeveritySubjectService;

@Service
public class PereodicSeveritySubjectServiceImpl implements PereodicSeveritySubjectService {

    @Autowired
    private PereodicSeveritySubjectRepository pereodicSeveritySubjectRepository;

    @Override
    public PereodicSeveritySubject save(PereodicSeveritySubject obj) {
        return pereodicSeveritySubjectRepository.save(obj);
    }

    @Override
    public PereodicSeveritySubject update(PereodicSeveritySubject obj) {
        return pereodicSeveritySubjectRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        pereodicSeveritySubjectRepository.deleteById(id);
    }


    @Override
    public List<PereodicSeveritySubject> getAll() {
        return pereodicSeveritySubjectRepository.findAll();
    }

    @Override
    public Optional<PereodicSeveritySubject> getById(UUID id) {
        return pereodicSeveritySubjectRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(PereodicSeveritySubject.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

