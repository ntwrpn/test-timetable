package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Lectern;
import com.java.repository.LecternRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.LecternService;

@Service
public class LecternServiceImpl implements LecternService {

    @Override
    public Lectern save(Lectern obj) {
        return LecternRepository.save(obj);
    }

    @Override
    public Lectern update(Lectern obj) {
        return LecternRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        LecternRepository.delete(id);
    }

    @Autowired
    private LecternRepository LecternRepository;

    @Override
    public List<Lectern> getAll(){
        return LecternRepository.findAll();
    }

    @Override
    public Optional<Lectern> getById(UUID id) {
        return LecternRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Lectern.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

