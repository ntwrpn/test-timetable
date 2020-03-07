package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Turn;
import org.json.simple.JSONObject;

public interface TurnService {

    Turn save(Turn Turn);

    Turn update(Turn Turn);

    void delete(UUID id);

    List<Turn> getAll();

    Optional<Turn> getById(UUID userId);
    
    public JSONObject getFields();
}