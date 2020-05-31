package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.java.domain.Flow;
import com.java.domain.Groups;
import com.java.repository.DeaneryRepository;
import com.java.repository.FlowRepository;

import java.lang.reflect.Field;

import com.java.repository.LecternRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.FlowService;

@Service
public class FlowServiceImpl implements FlowService {

    @Autowired
    private FlowRepository flowRepository;

    @Autowired
    private DeaneryRepository deaneryRepository;
    
    @Override
    public Flow save(Flow obj, UUID uuid) {
        if(uuid != null){
            obj.setDeanery(deaneryRepository.findById(uuid).get());
        }
        return saveOrUpdateFlow(obj);
    }

    @Override
    public Flow update(Flow obj) {
        Optional<Flow> flow = flowRepository.findById(obj.getId());
        if(flow.isPresent()){
            obj.setDeanery(flow.get().getDeanery());
            for(Groups groups:obj.getGroups()){
                groups.setFlow(obj);
            }
            return saveOrUpdateFlow(obj);
        }
        return null;
    }

    private Flow saveOrUpdateFlow(Flow obj){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Flow>> violations = validator.validate(obj);
        if(violations.size()!=0){
            throw  new ConstraintViolationException(violations);
        }
        return flowRepository.save(obj);
    }
    
    @Override
    public void delete(UUID id) {
        flowRepository.deleteById(id);
    }


    @Override
    public List<Flow> getAll() {
        return flowRepository.findAll();
    }

    @Override
    public Optional<Flow> getById(UUID id) {
        return flowRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Flow.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }

    @Override
    public List<Flow> findByDeaneryId(UUID uuid) {
        return flowRepository.findAllByDeaneryId(uuid);
    }

    @Override
    public List<Flow> findByLecternId(UUID uuid) {
        return flowRepository.findFlowsByLecternId(uuid);
    }

    @Override
    public List<Flow> findByName(String name) {
        return flowRepository.findByName(name);
    }
}

