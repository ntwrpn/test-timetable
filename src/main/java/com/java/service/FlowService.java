package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Flow;
import org.json.simple.JSONObject;

public interface FlowService {

    Flow save(Flow Flow);

    Flow update(Flow Flow);

    void delete(UUID id);

    List<Flow> getAll();

    Optional<Flow> getById(UUID userId);
    
    public JSONObject getFields();
}