package com.java.service;

import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Users;
import com.java.payload.ResetPasswordRequest;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;

public interface UsersService {

    Users save(Users Users);

    Users update(Users Users);

    void delete(UUID id);

    List<Users> getAll();

    Optional<Users> getById(UUID userId);

    
    Optional<Users> getByName(String login);
    List<Users> getByEnabled(boolean state);
    
    List<Users> getClear();

    Optional<Users> getByTeacherId(UUID userId);

    Optional<Users> getByEmployeeId(UUID userId);


    void blockUser(UUID id);
    void unBlockUser(UUID id);

    public JsonSchema getFields();
    
    String createTokenForUser(Users user);
    
    String validatePasswordResetToken(UUID id, String token);
    
    ResponseEntity<?> updatePassword(ResetPasswordRequest resetPasswordRequest);
}