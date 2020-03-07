package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Severity;
import org.json.simple.JSONObject;

public interface SeverityService {

    Severity save(Severity Severity);

    Severity update(Severity Severity);

    void delete(UUID id);

    List<Severity> getAll();

    Optional<Severity> getById(UUID userId);
    
    public JSONObject getFields();
}