package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Occupation;
import org.json.simple.JSONObject;

public interface OccupationService {

    Occupation save(Occupation Occupation);

    Occupation update(Occupation Occupation);

    void delete(UUID id);

    List<Occupation> getAll();

    Optional<Occupation> getById(UUID userId);
    
    public JSONObject getFields();
}