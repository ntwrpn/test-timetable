package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @Override
    public OccupationCounterCourse save(OccupationCounterCourse obj) {
        return OccupationCounterCourseRepository.save(obj);
    }

    @Override
    public OccupationCounterCourse update(OccupationCounterCourse obj) {
        return OccupationCounterCourseRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        OccupationCounterCourseRepository.deleteById(id);
    }

    @Autowired
    private OccupationCounterCourseRepository OccupationCounterCourseRepository;

    @Override
    public List<OccupationCounterCourse> getAll() {
        return OccupationCounterCourseRepository.findAll();
    }

    @Override
    public Optional<OccupationCounterCourse> getById(UUID id) {
        return OccupationCounterCourseRepository.findById(id);
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

