package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Deanery;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface DeaneryService {

    Deanery save(Deanery Deanery);

    Deanery update(Deanery Deanery);

    void delete(UUID id);

    List<Deanery> getAll();

    Optional<Deanery> getById(UUID userId);
    
    public JsonSchema getFields();
}