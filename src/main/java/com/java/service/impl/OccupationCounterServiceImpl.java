package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @Override
    public OccupationCounter save(OccupationCounter obj) {
        return OccupationCounterRepository.save(obj);
    }

    @Override
    public OccupationCounter update(OccupationCounter obj) {
        return OccupationCounterRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        OccupationCounterRepository.delete(id);
    }

    @Autowired
    private OccupationCounterRepository OccupationCounterRepository;

    @Override
    public List<OccupationCounter> getAll(){
        return OccupationCounterRepository.findAll();
    }

    @Override
    public Optional<OccupationCounter> getById(UUID id) {
        return OccupationCounterRepository.findById(id);
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

