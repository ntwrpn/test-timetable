package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Timetable;
import com.java.repository.TimetableRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.customProperties.HyperSchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.TimetableService;

@Service
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    @Override
    public Timetable save(Timetable obj) {
        return timetableRepository.save(obj);
    }

    @Override
    public Timetable update(Timetable obj) {
        return timetableRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        timetableRepository.deleteById(id);
    }


    @Override
    public List<Timetable> getAll() {
        return timetableRepository.findAll();
    }

    @Override
    public Optional<Timetable> getById(UUID id) {
        return timetableRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        HyperSchemaFactoryWrapper  visitor = new HyperSchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Timetable.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
}

