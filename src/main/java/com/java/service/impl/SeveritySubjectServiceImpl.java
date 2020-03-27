package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.SeveritySubject;
import com.java.repository.SeveritySubjectRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.SeveritySubjectService;

@Service
public class SeveritySubjectServiceImpl implements SeveritySubjectService {

    @Autowired
    private SeveritySubjectRepository severitySubjectRepository;

    @Override
    public SeveritySubject save(SeveritySubject obj) {
        return severitySubjectRepository.save(obj);
    }

    @Override
    public SeveritySubject update(SeveritySubject obj) {
        return severitySubjectRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        severitySubjectRepository.deleteById(id);
    }


    @Override
    public List<SeveritySubject> getAll() {
        return severitySubjectRepository.findAll();
    }

    @Override
    public Optional<SeveritySubject> getById(UUID id) {
        return severitySubjectRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(SeveritySubject.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

