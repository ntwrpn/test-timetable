package com.java.service.impl;

//import com.java.domain.PasswordResetToken;
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
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Users.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
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

