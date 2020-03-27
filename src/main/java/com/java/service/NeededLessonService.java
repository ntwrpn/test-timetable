package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.NeededLesson;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface NeededLessonService {

    NeededLesson save(NeededLesson NeededLesson);

    NeededLesson update(NeededLesson NeededLesson);

    void delete(UUID id);

    List<NeededLesson> getAll();

    Optional<NeededLesson> getById(UUID userId);
    
    public JsonSchema getFields();
}