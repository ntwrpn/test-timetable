package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.UserRoles;
import org.json.simple.JSONObject;

public interface UserRolesService {

    UserRoles save(UserRoles UserRoles);

    UserRoles update(UserRoles UserRoles);

    void delete(UUID id);

    List<UserRoles> getAll();

    Optional<UserRoles> getById(UUID userId);
    
    Optional<UserRoles> getByName(String roleName);
    
    public JSONObject getFields();
}