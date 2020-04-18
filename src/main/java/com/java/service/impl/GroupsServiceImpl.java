package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Groups;
import com.java.repository.FlowRepository;
import com.java.repository.GroupsRepository;

import java.lang.reflect.Field;

import com.java.repository.SpecialityRepository;
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

    @Autowired
    private FlowRepository flowRepository;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public Groups save(Groups obj, UUID flowId) {
        if(flowId != null){
            obj.setFlow(flowRepository.findById(flowId).get());
        }
        return groupsRepository.save(obj);
    }

    @Override
    public Groups update(Groups obj) {
        return groupsRepository.save(obj);
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
        return groupsRepository.findByFlowLecternDeaneryId(uuid);
    }
    
    @Override
    public List<Groups> findByFlowId(UUID uuid) {
        return groupsRepository.findByFlowId(uuid);
    }

    @Override
    public List<Groups> findByName(String name) {
        return groupsRepository.findByName(name);
    }
}

