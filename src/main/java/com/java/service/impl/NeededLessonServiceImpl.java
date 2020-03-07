package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.NeededLesson;
import com.java.repository.NeededLessonRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.NeededLessonService;

@Service
public class NeededLessonServiceImpl implements NeededLessonService {

    @Autowired
    private NeededLessonRepository neededLessonRepository;

    @Override
    public NeededLesson save(NeededLesson obj) {
        return neededLessonRepository.save(obj);
    }

    @Override
    public NeededLesson update(NeededLesson obj) {
        return neededLessonRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        neededLessonRepository.deleteById(id);
    }

    @Override
    public List<NeededLesson> getAll() {
        return neededLessonRepository.findAll();
    }

    @Override
    public Optional<NeededLesson> getById(UUID id) {
        return neededLessonRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : NeededLesson.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

