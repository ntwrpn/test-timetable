package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.PereodicSeverity;
import org.json.simple.JSONObject;

public interface PereodicSeverityService {

    PereodicSeverity save(PereodicSeverity PereodicSeverity);

    PereodicSeverity update(PereodicSeverity PereodicSeverity);

    void delete(UUID id);

    List<PereodicSeverity> getAll();

    Optional<PereodicSeverity> getById(UUID userId);
    
    public JSONObject getFields();
}