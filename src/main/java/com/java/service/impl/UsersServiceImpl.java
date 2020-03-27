package com.java.service.impl;

//import com.java.domain.PasswordResetToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.java.domain.TypeOfLesson;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Users;
//import com.java.repository.PasswordTokenRepository;
import com.java.repository.UsersRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.UsersService;
import java.io.IOException;
import java.util.Calendar;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
/*
    @Autowired
    private PasswordTokenRepository PasswordTokenRepository;
        */
    @Override
    public Users save(Users obj) {
        return usersRepository.save(obj);
    }

    @Override
    public Users update(Users obj) {
        return usersRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> getById(UUID id) {
        return usersRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Users.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }

    @Override
    public Optional<Users> getByName(String login) {
        return usersRepository.findByUsername(login);
    }

    @Override
    public void blockUser(UUID id) {
        usersRepository.blockUser(id);
    }

    @Override
    public void unBlockUser(UUID id) {
        usersRepository.unBlockUser(id);
    }
    
    @Override
    public List<Users> getByEnabled(boolean state){
        if (state){
            return usersRepository.findByEnabledTrue();
        }
        return usersRepository.findByEnabledFalse();
    }
    
    @Override
    public List<Users> getByLecternName(String lectern){
        return usersRepository.getByLectern(lectern);
    }
    
    @Override
    public List<Users> getByDeaneryName(String deanery){
        return usersRepository.getByDeanery(deanery);
    }
    
    @Override
    public List<Users> getByLectern(UUID id){
        return usersRepository.findByLectern(id);
    }
    
    @Override
    public List<Users> getByDeanery(UUID id){
        return usersRepository.findByDeanery(id);
    }
    
    
    /*
    @Override
    public void createPasswordResetTokenForUser(Users user, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setToken(token);
        myToken.setUser(user);
        PasswordTokenRepository.save(myToken);
    }
    @Override
    public String validatePasswordResetToken(UUID id, String token) {
    Optional<PasswordResetToken> passToken = PasswordTokenRepository.findByToken(token);
    if (passToken.isEmpty() || passToken.get().getId() != id) {
        return "invalidToken";
    }
 
    Calendar cal = Calendar.getInstance();
    if ((passToken.get().getExpiryDate()
        .getTime() - cal.getTime()
        .getTime()) <= 0) {
        return "expired";
    }
 
    return null;
}
  */
}

