package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.TypeOfLesson;
import com.java.repository.TypeOfLessonRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.TypeOfLessonService;

@Service
public class TypeOfLessonServiceImpl implements TypeOfLessonService {

    @Autowired
    private TypeOfLessonRepository typeOfLessonRepository;

    @Override
    public TypeOfLesson save(TypeOfLesson obj) {
        return typeOfLessonRepository.save(obj);
    }

    @Override
    public TypeOfLesson update(TypeOfLesson obj) {
        return typeOfLessonRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        typeOfLessonRepository.deleteById(id);
    }


    @Override
    public List<TypeOfLesson> getAll() {
        return typeOfLessonRepository.findAll();
    }

    @Override
    public Optional<TypeOfLesson> getById(UUID id) {
        return typeOfLessonRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(TypeOfLesson.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

