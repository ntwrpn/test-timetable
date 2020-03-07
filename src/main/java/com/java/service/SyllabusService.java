package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Syllabus;
import org.json.simple.JSONObject;

public interface SyllabusService {

    Syllabus save(Syllabus Syllabus);

    Syllabus update(Syllabus Syllabus);

    void delete(UUID id);

    List<Syllabus> getAll();

    Optional<Syllabus> getById(UUID userId);
    
    public JSONObject getFields();
}