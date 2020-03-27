package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Corps;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface CorpsService {

    Corps save(Corps Corps);

    Corps update(Corps Corps);

    void delete(UUID id);

    List<Corps> getAll();

    Optional<Corps> getById(UUID userId);
    
    public JsonSchema getFields();
}