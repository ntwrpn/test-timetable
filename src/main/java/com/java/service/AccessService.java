package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Access;
import org.json.simple.JSONObject;

public interface AccessService {

    Access save(Access Access);

    Access update(Access Access);

    void delete(UUID id);

    List<Access> getAll();

    Optional<Access> getById(UUID userId);
    
    List<Access> getByUserRoles(String role);
    
    public JSONObject getFields();
}