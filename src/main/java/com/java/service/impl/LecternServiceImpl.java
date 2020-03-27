package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Lectern;
import com.java.repository.LecternRepository;
import com.java.repository.DeaneryRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.LecternService;

@Service
public class LecternServiceImpl implements LecternService {

    @Autowired
    private LecternRepository lecternRepository;
	
	@Autowired
    private DeaneryRepository deaneryRepository;
	
	
    @Override
    public Lectern save(Lectern obj, UUID id) {
		obj.setDeanery(deaneryRepository.findById(id).get());
        return lecternRepository.save(obj);
    }

    @Override
    public Lectern update(Lectern obj) {
        return lecternRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        lecternRepository.deleteById(id);
    }


    @Override
    public List<Lectern> getAll() {
        return lecternRepository.findAll();
    }

    @Override
    public Optional<Lectern> getById(UUID id) {
        return lecternRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Lectern.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
	
	@Override
	public List<Lectern> findLecternsByDeaneryId(UUID id){
		return lecternRepository.findLecternsByDeaneryId(id);
	};
}

