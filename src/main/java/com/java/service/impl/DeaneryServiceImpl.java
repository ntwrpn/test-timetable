package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Deanery;
import com.java.repository.DeaneryRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.DeaneryService;

@Service
public class DeaneryServiceImpl implements DeaneryService {

    @Autowired
    private DeaneryRepository deaneryRepository;

    @Override
    public Deanery save(Deanery obj) {
        return deaneryRepository.save(obj);
    }

    @Override
    public Deanery update(Deanery obj) {
        return deaneryRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        deaneryRepository.deleteById(id);
    }

    @Override
    public List<Deanery> getAll() {
        return deaneryRepository.findAll();
    }

    @Override
    public Optional<Deanery> getById(UUID id) {
        return deaneryRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Deanery.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

