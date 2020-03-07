package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Lesson;
import org.json.simple.JSONObject;

public interface LessonService {

    Lesson save(Lesson Lesson);

    Lesson update(Lesson Lesson);

    void delete(UUID id);

    List<Lesson> getAll();

    Optional<Lesson> getById(UUID userId);
    
    public JSONObject getFields();
}