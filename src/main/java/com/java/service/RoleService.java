package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Role;
import org.json.simple.JSONObject;

public interface RoleService {

    Role save(Role Role);

    Role update(Role Role);

    void delete(UUID id);

    List<Role> getAll();

    Optional<Role> getById(UUID userId);
    
    public JSONObject getFields();
}