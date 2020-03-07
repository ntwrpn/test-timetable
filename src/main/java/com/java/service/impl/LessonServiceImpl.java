package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Lesson;
import com.java.repository.LessonRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService {

    @Override
    public Lesson save(Lesson obj) {
        return LessonRepository.save(obj);
    }

    @Override
    public Lesson update(Lesson obj) {
        return LessonRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        LessonRepository.delete(id);
    }

    @Autowired
    private LessonRepository LessonRepository;

    @Override
    public List<Lesson> getAll(){
        return LessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> getById(UUID id) {
        return LessonRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Lesson.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

