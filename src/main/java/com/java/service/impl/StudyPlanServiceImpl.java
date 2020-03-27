package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.StudyPlan;
import com.java.repository.StudyPlanRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.StudyPlanService;

@Service
public class StudyPlanServiceImpl implements StudyPlanService {

    @Autowired
    private StudyPlanRepository studyPlanRepository;

    @Override
    public StudyPlan save(StudyPlan obj) {
        return studyPlanRepository.save(obj);
    }

    @Override
    public StudyPlan update(StudyPlan obj) {
        return studyPlanRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        studyPlanRepository.deleteById(id);
    }


    @Override
    public List<StudyPlan> getAll() {
        return studyPlanRepository.findAll();
    }

    @Override
    public Optional<StudyPlan> getById(UUID id) {
        return studyPlanRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(StudyPlan.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
	
    @Override
    public List<StudyPlan> findStudyplansByLecternId(UUID id){
            return studyPlanRepository.findStudyplansByLecternId(id);
    }
}

