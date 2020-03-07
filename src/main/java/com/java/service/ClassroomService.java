package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Classroom;
import org.json.simple.JSONObject;

public interface ClassroomService {

    Classroom save(Classroom Classroom);

    Classroom update(Classroom Classroom);

    void delete(UUID id);

    List<Classroom> getAll();

    Optional<Classroom> getById(UUID userId);
    
    public JSONObject getFields();
}