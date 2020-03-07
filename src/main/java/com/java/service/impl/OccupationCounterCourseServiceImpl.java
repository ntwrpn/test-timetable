package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.OccupationCounterCourse;
import com.java.repository.OccupationCounterCourseRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.OccupationCounterCourseService;

@Service
public class OccupationCounterCourseServiceImpl implements OccupationCounterCourseService {

    @Autowired
    private OccupationCounterCourseRepository occupationCounterCourseRepository;

    @Override
    public OccupationCounterCourse save(OccupationCounterCourse obj) {
        return occupationCounterCourseRepository.save(obj);
    }

    @Override
    public OccupationCounterCourse update(OccupationCounterCourse obj) {
        return occupationCounterCourseRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        occupationCounterCourseRepository.deleteById(id);
    }

    @Override
    public List<OccupationCounterCourse> getAll() {
        return occupationCounterCourseRepository.findAll();
    }

    @Override
    public Optional<OccupationCounterCourse> getById(UUID id) {
        return occupationCounterCourseRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : OccupationCounterCourse.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

