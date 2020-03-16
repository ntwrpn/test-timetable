package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Deanery;
import org.json.simple.JSONObject;

public interface DeaneryService {

    Deanery save(Deanery deanery);

    Deanery update(Deanery deanery);

    void delete(UUID id);

    List<Deanery> getAll();

    Optional<Deanery> getById(UUID userId);
    
    public JSONObject getFields();
}