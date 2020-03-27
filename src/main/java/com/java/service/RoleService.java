package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Role;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface RoleService {

    Role save(Role Role);

    Role update(Role Role);

    void delete(UUID id);

    List<Role> getAll();

    Optional<Role> getById(UUID userId);
    
    public JsonSchema getFields();
}