package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.UserRoles;
import com.java.domain.Users;
import com.java.repository.UserRolesRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.UserRolesService;

@Service
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    private UserRolesRepository UserRolesRepository;

    @Override
    public UserRoles save(UserRoles obj) {
        return UserRolesRepository.save(obj);
    }

    @Override
    public UserRoles update(UserRoles obj) {
        return UserRolesRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        UserRolesRepository.deleteById(id);
    }

    @Override
    public List<UserRoles> getAll() {
        return UserRolesRepository.findAll();
    }

    @Override
    public Optional<UserRoles> getById(UUID id) {
        return UserRolesRepository.findById(id);
    }
    
    @Override
    public Optional<UserRoles> getByName(String roleName) {
        return UserRolesRepository.findByRole(roleName);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : UserRoles.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

