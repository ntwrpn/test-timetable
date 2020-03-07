package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.LecternType;
import org.json.simple.JSONObject;

public interface LecternTypeService {

    LecternType save(LecternType LecternType);

    LecternType update(LecternType LecternType);

    void delete(UUID id);

    List<LecternType> getAll();

    Optional<LecternType> getById(UUID userId);
    
    public JSONObject getFields();
}