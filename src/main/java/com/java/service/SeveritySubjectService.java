package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.SeveritySubject;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface SeveritySubjectService {

    SeveritySubject save(SeveritySubject SeveritySubject);

    SeveritySubject update(SeveritySubject SeveritySubject);

    void delete(UUID id);

    List<SeveritySubject> getAll();

    Optional<SeveritySubject> getById(UUID userId);
    
    public JsonSchema getFields();
}