package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Corps;
import org.json.simple.JSONObject;

public interface CorpsService {

    Corps save(Corps Corps);

    Corps update(Corps Corps);

    void delete(UUID id);

    List<Corps> getAll();

    Optional<Corps> getById(UUID userId);
    
    public JSONObject getFields();
}