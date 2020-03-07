package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.TypeOfLesson;
import org.json.simple.JSONObject;

public interface TypeOfLessonService {

    TypeOfLesson save(TypeOfLesson TypeOfLesson);

    TypeOfLesson update(TypeOfLesson TypeOfLesson);

    void delete(UUID id);

    List<TypeOfLesson> getAll();

    Optional<TypeOfLesson> getById(UUID userId);
    
    public JSONObject getFields();
}