package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.CanTeach;
import org.json.simple.JSONObject;

public interface CanTeachService {

    CanTeach save(CanTeach CanTeach);

    CanTeach update(CanTeach CanTeach);

    void delete(UUID id);

    List<CanTeach> getAll();

    Optional<CanTeach> getById(UUID userId);
    
    public JSONObject getFields();
}