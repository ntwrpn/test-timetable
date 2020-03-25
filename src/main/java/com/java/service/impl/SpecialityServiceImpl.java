package com.java.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Speciality;
import com.java.domain.StudyPlan;
import com.java.repository.LecternRepository;
import com.java.repository.SpecialityRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SpecialityService;
import java.io.IOException;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private LecternRepository lecternRepository;

    @Override
    public Speciality save(Speciality obj) {
        return specialityRepository.save(obj);
    }

    @Override
    public Speciality save(Speciality obj, UUID lecternId) {
        obj.setLectern(lecternRepository.findById(lecternId).get());
        return specialityRepository.save(obj);
    }

    @Override
    public Speciality update(Speciality obj) {
        Optional<Speciality> speciality = specialityRepository.findById(obj.getId());
        if(speciality.isPresent()){
            speciality.get().setId(obj.getId());
            speciality.get().setDescription(obj.getDescription());
            speciality.get().setName(obj.getName());
            return specialityRepository.save(speciality.get());
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        specialityRepository.deleteById(id);
    }

    @Override
    public List<Speciality> getAll() {
        return specialityRepository.findAll();
    }

    @Override
    public Optional<Speciality> getById(UUID id) {
        return specialityRepository.findById(id);
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
}

