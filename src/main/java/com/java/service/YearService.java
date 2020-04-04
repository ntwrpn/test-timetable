package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Year;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface YearService {

    Year save(Year Year);

    Year update(Year Year);

    void delete(UUID id);

    List<Year> getAll();

    Optional<Year> getById(UUID userId);
    
    public JsonSchema getFields();
}