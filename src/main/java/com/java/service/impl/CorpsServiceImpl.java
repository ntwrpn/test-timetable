package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Corps;
import com.java.repository.CorpsRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.CorpsService;

@Service
public class CorpsServiceImpl implements CorpsService {

    @Override
    public Corps save(Corps obj) {
        return CorpsRepository.save(obj);
    }

    @Override
    public Corps update(Corps obj) {
        return CorpsRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        CorpsRepository.deleteById(id);
    }

    @Autowired
    private CorpsRepository CorpsRepository;

    @Override
    public List<Corps> getAll(){
        return CorpsRepository.findAll();
    }

    @Override
    public Optional<Corps> getById(UUID id) {
        return CorpsRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Corps.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

