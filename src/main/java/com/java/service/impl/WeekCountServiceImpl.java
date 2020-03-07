package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.WeekCount;
import com.java.repository.WeekCountRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.WeekCountService;

@Service
public class WeekCountServiceImpl implements WeekCountService {

    @Override
    public WeekCount save(WeekCount obj) {
        return WeekCountRepository.save(obj);
    }

    @Override
    public WeekCount update(WeekCount obj) {
        return WeekCountRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        WeekCountRepository.delete(id);
    }

    @Autowired
    private WeekCountRepository WeekCountRepository;

    @Override
    public List<WeekCount> getAll(){
        return WeekCountRepository.findAll();
    }

    @Override
    public Optional<WeekCount> getById(UUID id) {
        return WeekCountRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : WeekCount.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

