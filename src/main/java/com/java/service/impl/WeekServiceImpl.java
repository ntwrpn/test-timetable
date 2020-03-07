package com.java.service.impl;

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

    @Autowired
    private WeekRepository weekRepository;

    @Override
    public Week save(Week obj) {
        return weekRepository.save(obj);
    }

    @Override
    public Week update(Week obj) {
        return weekRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        weekRepository.deleteById(id);
    }

    @Override
    public List<Week> getAll() {
        return weekRepository.findAll();
    }

    @Override
    public Optional<Week> getById(UUID id) {
        return weekRepository.findById(id);
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

