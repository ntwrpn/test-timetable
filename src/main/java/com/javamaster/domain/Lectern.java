
package com.javamaster.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Lectern")

@NamedQueries({
@NamedQuery(name = "Lectern.getAll", query = "SELECT c from Lectern c"),
@NamedQuery(name = "Lectern.getById", query = "SELECT c from Lectern c where c.id=:id")
}) 


public class Lectern {

    
    @Id
    @Column(name = "idlectern")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "faculty_id")
    private int faculty_id;

    @Column(name = "faculty_idfaculty")
    private int faculty_idfaculty;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "lectern_name")
    private String lectern_name;

    @Column(name = "lectern_type_id")
    private int lectern_type_id;


    public Lectern() {
    }

    
    public int getFaculty_id() {
        return faculty_id;
    }
    
    public void setFaculty_id(int faculty_id) {
        this.faculty_id=faculty_id;
    }

    public int getFaculty_idfaculty() {
        return faculty_idfaculty;
    }
    
    public void setFaculty_idfaculty(int faculty_idfaculty) {
        this.faculty_idfaculty=faculty_idfaculty;
    }

    public String getFullname() {
        return fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname=fullname;
    }

    public String getLectern_name() {
        return lectern_name;
    }
    
    public void setLectern_name(String lectern_name) {
        this.lectern_name=lectern_name;
    }

    public int getLectern_type_id() {
        return lectern_type_id;
    }
    
    public void setLectern_type_id(int lectern_type_id) {
        this.lectern_type_id=lectern_type_id;
    }

}

