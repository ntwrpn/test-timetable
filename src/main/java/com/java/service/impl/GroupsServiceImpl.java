package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.java.domain.Groups;
import com.java.repository.GroupsRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.GroupsService;

@Service
public class GroupsServiceImpl implements GroupsService {

    @Autowired
    private GroupsRepository groupsRepository;

    @Override
    public Groups save(Groups obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Groups>> violations = validator.validate(obj);
        if (violations.size() != 0) {
            throw new ConstraintViolationException(violations);
        }
        return groupsRepository.save(obj);
    }

    @Override
    public Groups update(Groups obj) {
        Optional<Groups> groups = groupsRepository.findById(obj.getId());
        if(groups.isPresent()){
            obj.setFlow(groups.get().getFlow());
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Groups>> violations = validator.validate(obj);
            if(violations.size()!=0){
                throw  new ConstraintViolationException(violations);
            }
            return groupsRepository.save(obj);
        }
        return null;
    }


    @Override
    public void delete(UUID id) {
        groupsRepository.deleteById(id);
    }


    @Override
    public List<Groups> getAll() {
        return groupsRepository.findAll();
    }

    @Override
    public Optional<Groups> getById(UUID id) {
        return groupsRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Groups.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }

    @Override
    public List<Groups> findByFlowLecternDeaneryId(UUID uuid) {
        return groupsRepository.findBySpecialityLecternDeaneryId(uuid);
    }

    @Override
    public List<Groups> findByName(String name) {
        return groupsRepository.findByName(name);
    }

    @Override
    public List<Groups> findByFlowAndSpecialityLecternDeaneryId(UUID flowId, UUID uuid) {
        return groupsRepository.findByFlowIdAndSpecialityLecternDeaneryId(flowId, uuid);
    }

    @Override
    public Groups setNullFlow(Groups obj) {
        obj.setFlow(null);
        return groupsRepository.save(obj);
    }
}

