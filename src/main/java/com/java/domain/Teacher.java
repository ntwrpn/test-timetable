
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Teacher")

@NamedQueries({
@NamedQuery(name = "Teacher.getAll", query = "SELECT c from Teacher c"),
@NamedQuery(name = "Teacher.getById", query = "SELECT c from Teacher c where c.id=:id")
}) 


public class Teacher {

    
    @Id
    @Column(name = "idteacher")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "lectern_id")
    private int lectern_id;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "surname")
    private String surname;


    public Teacher() {
    }

    
    public int getLectern_id() {
        return lectern_id;
    }
    
    public void setLectern_id(int lectern_id) {
        this.lectern_id=lectern_id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

    public String getPatronymic() {
        return patronymic;
    }
    
    public void setPatronymic(String patronymic) {
        this.patronymic=patronymic;
    }

    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname=surname;
    }

}

