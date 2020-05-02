package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Occupation;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface OccupationService {

    Occupation save(Occupation Occupation);

    Occupation update(Occupation Occupation);

    void delete(UUID id);

    List<Occupation> getAll();

    Optional<Occupation> getById(UUID userId);
    
    public JsonSchema getFields();

    List<Occupation> findBySymbol(String symbol);

    List<Occupation> findByValue(String value);
}