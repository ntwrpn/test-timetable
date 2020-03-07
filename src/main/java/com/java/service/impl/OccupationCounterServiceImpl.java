package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.OccupationCounter;
import com.java.repository.OccupationCounterRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.OccupationCounterService;

@Service
public class OccupationCounterServiceImpl implements OccupationCounterService {

    @Autowired
    private OccupationCounterRepository occupationCounterRepository;

    @Override
    public OccupationCounter save(OccupationCounter obj) {
        return occupationCounterRepository.save(obj);
    }

    @Override
    public OccupationCounter update(OccupationCounter obj) {
        return occupationCounterRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        occupationCounterRepository.deleteById(id);
    }

    @Override
    public List<OccupationCounter> getAll() {
        return occupationCounterRepository.findAll();
    }

    @Override
    public Optional<OccupationCounter> getById(UUID id) {
        return occupationCounterRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : OccupationCounter.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

