package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Week;
import com.java.repository.WeekRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.WeekService;

@Service
public class WeekServiceImpl implements WeekService {

    @Override
    public Week save(Week obj) {
        return WeekRepository.save(obj);
    }

    @Override
    public Week update(Week obj) {
        return WeekRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        WeekRepository.delete(id);
    }

    @Autowired
    private WeekRepository WeekRepository;

    @Override
    public List<Week> getAll(){
        return WeekRepository.findAll();
    }

    @Override
    public Optional<Week> getById(UUID id) {
        return WeekRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Week.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

