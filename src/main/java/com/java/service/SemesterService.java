package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Semester;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface SemesterService {

    Semester save(Semester Semester);

    Semester update(Semester Semester);

    void delete(UUID id);

    List<Semester> getAll();

    Optional<Semester> getById(UUID userId);
    
    public JsonSchema getFields();
}