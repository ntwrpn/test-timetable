package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.SemesterNumber;
import com.java.repository.SemesterNumberRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SemesterNumberService;

@Service
public class SemesterNumberServiceImpl implements SemesterNumberService {

    @Autowired
    private SemesterNumberRepository semesterNumberRepository;

    @Override
    public SemesterNumber save(SemesterNumber obj) {
        return semesterNumberRepository.save(obj);
    }

    @Override
    public SemesterNumber update(SemesterNumber obj) {
        return semesterNumberRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        semesterNumberRepository.deleteById(id);
    }

    @Override
    public List<SemesterNumber> getAll() {
        return semesterNumberRepository.findAll();
    }

    @Override
    public Optional<SemesterNumber> getById(UUID id) {
        return semesterNumberRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : SemesterNumber.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

