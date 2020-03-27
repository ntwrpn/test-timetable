package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Syllabus;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface SyllabusService {

    Syllabus save(Syllabus Syllabus);

    Syllabus update(Syllabus Syllabus);

    void delete(UUID id);

    List<Syllabus> getAll();

    Optional<Syllabus> getById(UUID userId);
    
    public JsonSchema getFields();
}