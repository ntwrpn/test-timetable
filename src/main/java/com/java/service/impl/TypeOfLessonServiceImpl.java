package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.TypeOfLesson;
import com.java.repository.TypeOfLessonRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.TypeOfLessonService;

@Service
public class TypeOfLessonServiceImpl implements TypeOfLessonService {

    @Autowired
    private TypeOfLessonRepository typeOfLessonRepository;

    @Override
    public TypeOfLesson save(TypeOfLesson obj) {
        return typeOfLessonRepository.save(obj);
    }

    @Override
    public TypeOfLesson update(TypeOfLesson obj) {
        return typeOfLessonRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        typeOfLessonRepository.deleteById(id);
    }

    @Override
    public List<TypeOfLesson> getAll() {
        return typeOfLessonRepository.findAll();
    }

    @Override
    public Optional<TypeOfLesson> getById(UUID id) {
        return typeOfLessonRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : TypeOfLesson.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

