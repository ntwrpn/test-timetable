package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Schedule;
import com.java.repository.ScheduleRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public Schedule save(Schedule obj) {
        return ScheduleRepository.save(obj);
    }

    @Override
    public Schedule update(Schedule obj) {
        return ScheduleRepository.save(obj);
    }
    
    @Override
    public void delete(UUID id) {
        ScheduleRepository.deleteById(id);
    }

    @Autowired
    private ScheduleRepository ScheduleRepository;

    @Override
    public List<Schedule> getAll(){
        return ScheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> getById(UUID id) {
        return ScheduleRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Schedule.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

