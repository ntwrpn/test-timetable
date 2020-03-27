package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.NeededLesson;
import com.java.repository.NeededLessonRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.NeededLessonService;

@Service
public class NeededLessonServiceImpl implements NeededLessonService {

    @Autowired
    private NeededLessonRepository neededLessonRepository;

    @Override
    public NeededLesson save(NeededLesson obj) {
        return neededLessonRepository.save(obj);
    }

    @Override
    public NeededLesson update(NeededLesson obj) {
        return neededLessonRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        neededLessonRepository.deleteById(id);
    }


    @Override
    public List<NeededLesson> getAll() {
        return neededLessonRepository.findAll();
    }

    @Override
    public Optional<NeededLesson> getById(UUID id) {
        return neededLessonRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(NeededLesson.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

