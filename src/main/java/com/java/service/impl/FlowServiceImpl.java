package com.java.service.impl;

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

    @Autowired
    private FlowRepository flowRepository;

    @Override
    public Flow save(Flow obj) {
        return flowRepository.save(obj);
    }

    @Override
    public Flow update(Flow obj) {
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
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Flow.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}
