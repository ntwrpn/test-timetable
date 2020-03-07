package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.OccupationCounter;
import org.json.simple.JSONObject;

public interface OccupationCounterService {

    OccupationCounter save(OccupationCounter OccupationCounter);

    OccupationCounter update(OccupationCounter OccupationCounter);

    void delete(UUID id);

    List<OccupationCounter> getAll();

    Optional<OccupationCounter> getById(UUID userId);
    
    public JSONObject getFields();
}