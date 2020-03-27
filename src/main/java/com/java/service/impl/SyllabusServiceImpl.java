package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Syllabus;
import com.java.repository.SyllabusRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.SyllabusService;

@Service
public class SyllabusServiceImpl implements SyllabusService {

    @Autowired
    private SyllabusRepository syllabusRepository;

    @Override
    public Syllabus save(Syllabus obj) {
        return syllabusRepository.save(obj);
    }

    @Override
    public Syllabus update(Syllabus obj) {
        return syllabusRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        syllabusRepository.deleteById(id);
    }


    @Override
    public List<Syllabus> getAll() {
        return syllabusRepository.findAll();
    }

    @Override
    public Optional<Syllabus> getById(UUID id) {
        return syllabusRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Syllabus.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

