package com.java.service;

import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
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

    List<Users> getByLecternName(String lectern);

    List<Users> getByDeaneryName(String deanery);
    
    List<Users> getByLectern(UUID id);

    List<Users> getByDeanery(UUID id);

    void blockUser(UUID id);
    void unBlockUser(UUID id);

    public JsonSchema getFields();
    
    /*void createPasswordResetTokenForUser(Users user, String token);
    
    String validatePasswordResetToken(UUID id, String token);*/
}