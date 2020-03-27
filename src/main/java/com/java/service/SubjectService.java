package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Subject;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface SubjectService {

    Subject save(Subject Subject);

    Subject update(Subject Subject);

    void delete(UUID id);

    List<Subject> getAll();

    Optional<Subject> getById(UUID userId);
    
    public JsonSchema getFields();
}