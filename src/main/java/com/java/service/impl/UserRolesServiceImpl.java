package com.java.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.java.domain.TypeOfLesson;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.UserRoles;
import com.java.repository.UserRolesRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.UserRolesService;
import java.io.IOException;

@Service
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public UserRoles save(UserRoles obj) {
        return userRolesRepository.save(obj);
    }

    @Override
    public UserRoles update(UserRoles obj) {
        return userRolesRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        userRolesRepository.deleteById(id);
    }

    @Override
    public List<UserRoles> getAll() {
        return userRolesRepository.findAll();
    }

    @Override
    public Optional<UserRoles> getById(UUID id) {
        return userRolesRepository.findById(id);
    }
    
    @Override
    public Optional<UserRoles> getByName(String roleName) {
        return userRolesRepository.findByRole(roleName);
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

