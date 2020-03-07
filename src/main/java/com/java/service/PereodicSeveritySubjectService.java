package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.PereodicSeveritySubject;
import org.json.simple.JSONObject;

public interface PereodicSeveritySubjectService {

    PereodicSeveritySubject save(PereodicSeveritySubject PereodicSeveritySubject);

    PereodicSeveritySubject update(PereodicSeveritySubject PereodicSeveritySubject);

    void delete(UUID id);

    List<PereodicSeveritySubject> getAll();

    Optional<PereodicSeveritySubject> getById(UUID userId);
    
    public JSONObject getFields();
}