package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Subgroup;
import org.json.simple.JSONObject;

public interface SubgroupService {

    Subgroup save(Subgroup Subgroup);

    Subgroup update(Subgroup Subgroup);

    void delete(UUID id);

    List<Subgroup> getAll();

    Optional<Subgroup> getById(UUID userId);
    
    public JSONObject getFields();
}