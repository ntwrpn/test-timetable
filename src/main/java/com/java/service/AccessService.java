package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Access;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface AccessService {

    Access save(Access Access);

    Access update(Access Access);

    void delete(UUID id);

    List<Access> getAll();

    Optional<Access> getById(UUID userId);
    
    List<Access> getByUserRoles(String role);
    
    public JsonSchema getFields();
}