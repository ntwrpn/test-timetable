package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.SemesterNumber;
import org.json.simple.JSONObject;

public interface SemesterNumberService {

    SemesterNumber save(SemesterNumber SemesterNumber);

    SemesterNumber update(SemesterNumber SemesterNumber);

    void delete(UUID id);

    List<SemesterNumber> getAll();

    Optional<SemesterNumber> getById(UUID userId);
    
    public JSONObject getFields();
}