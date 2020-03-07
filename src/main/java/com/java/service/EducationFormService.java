package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.EducationForm;
import org.json.simple.JSONObject;

public interface EducationFormService {

    EducationForm save(EducationForm EducationForm);

    EducationForm update(EducationForm EducationForm);

    void delete(UUID id);

    List<EducationForm> getAll();

    Optional<EducationForm> getById(UUID userId);
    
    public JSONObject getFields();
}