package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Week;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface WeekService {

    Week save(Week Week);

    Week update(Week Week);

    void delete(UUID id);

    List<Week> getAll();

    Optional<Week> getById(UUID userId);
    
    public JsonSchema getFields();
}