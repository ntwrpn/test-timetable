package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Speciality;
import org.json.simple.JSONObject;

public interface SpecialityService {

    Speciality save(Speciality Speciality);

    Speciality save(Speciality Speciality, UUID lecternId);

    Speciality update(Speciality Speciality);

    void delete(UUID id);

    List<Speciality> getAll();

    Optional<Speciality> getById(UUID userId);
    
    public JSONObject getFields();
}