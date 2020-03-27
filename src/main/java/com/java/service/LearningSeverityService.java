package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.LearningSeverity;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface LearningSeverityService {

    LearningSeverity save(LearningSeverity LearningSeverity);

    LearningSeverity update(LearningSeverity LearningSeverity);

    void delete(UUID id);

    List<LearningSeverity> getAll();

    Optional<LearningSeverity> getById(UUID userId);
    
    public JsonSchema getFields();
}