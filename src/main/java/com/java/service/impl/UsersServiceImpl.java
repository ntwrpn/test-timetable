package com.java.service.impl;

//import com.java.domain.PasswordResetToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.java.domain.TypeOfLesson;
import com.java.domain.UserToken;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Users;
import com.java.payload.ApiResponse;
import com.java.payload.ResetPasswordRequest;
import com.java.repository.TokenRepository;
//import com.java.repository.PasswordTokenRepository;
import com.java.repository.UsersRepository;
import com.java.service.AuthService;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.UsersService;
import java.io.IOException;
import java.util.Calendar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    AuthenticationManager authenticationManager;

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
        try {
            mapper.acceptJsonFormatVisitor(Users.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx) {
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
    public List<Users> getByEnabled(boolean state) {
        if (state) {
            return usersRepository.findByEnabledTrue();
        }
        return usersRepository.findByEnabledFalse();
    }

    @Override
    public List<Users> getByLecternName(String lectern) {
        return usersRepository.getByLectern(lectern);
    }

    @Override
    public List<Users> getByDeaneryName(String deanery) {
        return usersRepository.getByDeanery(deanery);
    }

    @Override
    public List<Users> getByLectern(UUID id) {
        return usersRepository.findByLectern(id);
    }

    @Override
    public List<Users> getByDeanery(UUID id) {
        return usersRepository.findByDeanery(id);
    }

    @Override
    public String createTokenForUser(Users user) {
        String token = UUID.randomUUID().toString();
        UserToken myToken = new UserToken(user, token);
        tokenRepository.save(myToken);
        return token;
    }

    @Override
    public String validatePasswordResetToken(UUID id, String token) {

        UserToken passUserToken = tokenRepository.findByToken(token).get();
        System.out.println(passUserToken.getUser().getId());
        System.out.println(id);
        if (!id.equals(passUserToken.getUser().getId())) {
            return "invalidToken";
        }
        Calendar cal = Calendar.getInstance();
        System.out.println(passUserToken.getExpiryDate()
                .getTime() - cal.getTime()
                        .getTime());
        if ((passUserToken.getExpiryDate()
                .getTime() - cal.getTime()
                        .getTime()) <= 0) {
            return "expired";
        }
        return null;
    }

    @Override
    public ResponseEntity<?> updatePassword(ResetPasswordRequest resetPasswordRequest) {
        String result = validatePasswordResetToken(resetPasswordRequest.getId(), resetPasswordRequest.getToken());
        System.out.println(result);
        if (result == null) {
            Users user = usersRepository.findById(resetPasswordRequest.getId()).get();
            user.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
            usersRepository.save(user);
            tokenRepository.delete(tokenRepository.findByToken(resetPasswordRequest.getToken()).get());
            return authService.authenticateUser(user.getUsername(), resetPasswordRequest.getPassword());
        }
        return new ResponseEntity(new ApiResponse(false, "Invalid token"),
                HttpStatus.BAD_REQUEST);
    }

}
