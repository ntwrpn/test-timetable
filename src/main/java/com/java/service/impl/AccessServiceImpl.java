package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Access;
import com.java.repository.AccessRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.AccessService;

@Service
public class AccessServiceImpl implements AccessService {

    @Override
    public Access save(Access obj) {
        return AccessRepository.save(obj);
    }

    @Override
    public Access update(Access obj) {
        return AccessRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        AccessRepository.deleteById(id);
    }

    @Autowired
    private AccessRepository AccessRepository;

    @Override
    public List<Access> getAll(){
        return AccessRepository.findAll();
    }

    @Override
    public Optional<Access> getById(UUID id) {
        return AccessRepository.findById(id);
    }
    
    
    @Override
    public List<Access> getByUserRoles(String role) {
        return AccessRepository.getByUserRoles(role);
    }
    
    
    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Access.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

