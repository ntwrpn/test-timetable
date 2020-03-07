package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @Override
    public LecternType save(LecternType obj) {
        return LecternTypeRepository.save(obj);
    }

    @Override
    public LecternType update(LecternType obj) {
        return LecternTypeRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        LecternTypeRepository.delete(id);
    }

    @Autowired
    private LecternTypeRepository LecternTypeRepository;

    @Override
    public List<LecternType> getAll(){
        return LecternTypeRepository.findAll();
    }

    @Override
    public Optional<LecternType> getById(UUID id) {
        return LecternTypeRepository.findById(id);
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

