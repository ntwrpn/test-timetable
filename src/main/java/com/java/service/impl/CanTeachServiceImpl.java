package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.CanTeach;
import com.java.repository.CanTeachRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.CanTeachService;

@Service
public class CanTeachServiceImpl implements CanTeachService {

    @Autowired
    private CanTeachRepository canTeachRepository;

    @Override
    public CanTeach save(CanTeach obj) {
        return canTeachRepository.save(obj);
    }

    @Override
    public CanTeach update(CanTeach obj) {
        return canTeachRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        canTeachRepository.deleteById(id);
    }

    @Override
    public List<CanTeach> getAll() {
        return canTeachRepository.findAll();
    }

    @Override
    public Optional<CanTeach> getById(UUID id) {
        return canTeachRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : CanTeach.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

