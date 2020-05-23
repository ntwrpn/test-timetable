package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Teacher;
import com.java.repository.LecternRepository;
import com.java.repository.TeacherRepository;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

import java.io.IOException;

import com.java.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private LecternRepository lecternRepository;

    @Override
    public Teacher save(Teacher obj) {
        return teacherRepository.save(obj);
    }

    @Override
    public Teacher save(Teacher obj, UUID lecternId) {
        obj.setLectern(this.lecternRepository.findById(lecternId).get());
        return teacherRepository.save(obj);
    }


    @Override
    public Teacher update(Teacher obj) {
        Optional<Teacher> teacher = this.teacherRepository.findById(obj.getId());
        if (teacher.isPresent()) {
            teacher.get().setName(obj.getName());
            teacher.get().setSurname(obj.getSurname());
            teacher.get().setPatronymic(obj.getPatronymic());
            teacher.get().setAcademicDegree(obj.getAcademicDegree());
            teacher.get().setAcademicDegreeAbbreviation(obj.getAcademicDegreeAbbreviation());
            teacher.get().setStaffType(obj.getStaffType());
            teacher.get().setPosition(obj.getPosition());
            teacher.get().setAcademicRank(obj.getAcademicRank());
            teacher.get().setAdditionalInfo(obj.getAdditionalInfo());
            teacher.get().setRate(obj.getRate());
            if (obj.getLectern() != null) {
                teacher.get().setLectern(obj.getLectern());
            }
            return teacherRepository.save(teacher.get());
        }
        return teacherRepository.save(teacher.get());
    }

    @Override
    public void delete(UUID id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getById(UUID id) {
        return teacherRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try {
            mapper.acceptJsonFormatVisitor(Teacher.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx) {
            return null;
        }
    }

    @Override
    public List<Teacher> findByLectern(UUID uuid) {
        return teacherRepository.findByLecternId(uuid);
    }
}
