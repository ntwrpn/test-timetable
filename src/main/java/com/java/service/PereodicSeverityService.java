package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.PereodicSeverity;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface PereodicSeverityService {

    PereodicSeverity save(PereodicSeverity PereodicSeverity);

    PereodicSeverity update(PereodicSeverity PereodicSeverity);

    void delete(UUID id);

    List<PereodicSeverity> getAll();

    Optional<PereodicSeverity> getById(UUID userId);
    
    public JsonSchema getFields();
}