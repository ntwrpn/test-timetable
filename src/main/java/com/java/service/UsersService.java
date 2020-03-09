package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Users;
import org.json.simple.JSONObject;

public interface UsersService {

    Users save(Users Users);

    Users update(Users Users);

    void delete(UUID id);

    List<Users> getAll();

    Optional<Users> getById(UUID userId);

    
    Optional<Users> getByName(String login);
    List<Users> getByEnabled(boolean state);


    void blockUser(UUID id);
    void unBlockUser(UUID id);

    public JSONObject getFields();
}