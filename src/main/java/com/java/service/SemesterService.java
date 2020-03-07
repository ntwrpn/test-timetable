package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Semester;
import org.json.simple.JSONObject;

public interface SemesterService {

    Semester save(Semester Semester);

    Semester update(Semester Semester);

    void delete(UUID id);

    List<Semester> getAll();

    Optional<Semester> getById(UUID userId);
    
    public JSONObject getFields();
}