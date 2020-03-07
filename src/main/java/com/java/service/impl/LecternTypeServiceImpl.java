package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.LecternType;
import com.java.repository.LecternTypeRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.LecternTypeService;

@Service
public class LecternTypeServiceImpl implements LecternTypeService {

    @Autowired
    private LecternTypeRepository lecternTypeRepository;

    @Override
    public LecternType save(LecternType obj) {
        return lecternTypeRepository.save(obj);
    }

    @Override
    public LecternType update(LecternType obj) {
        return lecternTypeRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        lecternTypeRepository.deleteById(id);
    }

    @Override
    public List<LecternType> getAll() {
        return lecternTypeRepository.findAll();
    }

    @Override
    public Optional<LecternType> getById(UUID id) {
        return lecternTypeRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : LecternType.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

