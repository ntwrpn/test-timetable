package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.ClassroomType;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface ClassroomTypeService {

    ClassroomType save(ClassroomType ClassroomType);

    ClassroomType update(ClassroomType ClassroomType);

    void delete(UUID id);

    List<ClassroomType> getAll();

    Optional<ClassroomType> getById(UUID userId);
    
    public JsonSchema getFields();
}