package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Role;
import com.java.repository.RoleRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Role save(Role obj) {
        return RoleRepository.save(obj);
    }

    @Override
    public Role update(Role obj) {
        return RoleRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        RoleRepository.delete(id);
    }

    @Autowired
    private RoleRepository RoleRepository;

    @Override
    public List<Role> getAll(){
        return RoleRepository.findAll();
    }

    @Override
    public Optional<Role> getById(UUID id) {
        return RoleRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Role.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

