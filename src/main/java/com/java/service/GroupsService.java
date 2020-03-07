package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Groups;
import org.json.simple.JSONObject;

public interface GroupsService {

    Groups save(Groups Groups);

    Groups update(Groups Groups);

    void delete(UUID id);

    List<Groups> getAll();

    Optional<Groups> getById(UUID userId);
    
    public JSONObject getFields();
}