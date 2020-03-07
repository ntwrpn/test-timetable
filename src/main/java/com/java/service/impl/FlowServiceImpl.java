package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Flow;
import com.java.repository.FlowRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.FlowService;

@Service
public class FlowServiceImpl implements FlowService {

    @Override
    public Flow save(Flow obj) {
        return FlowRepository.save(obj);
    }

    @Override
    public Flow update(Flow obj) {
        return FlowRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        FlowRepository.deleteById(id);
    }

    @Autowired
    private FlowRepository FlowRepository;

    @Override
    public List<Flow> getAll(){
        return FlowRepository.findAll();
    }

    @Override
    public Optional<Flow> getById(UUID id) {
        return FlowRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Flow.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

