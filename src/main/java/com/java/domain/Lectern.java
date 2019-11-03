
package com.java.domain;

import java.lang.reflect.Field;
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
    private int id=0;
    
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="faculty_idfaculty", referencedColumnName="idfaculty", nullable = true)
    private Faculty faculty_idfaculty;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "lectern_name")
    private String lectern_name;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="lectern_type_id", referencedColumnName="idlectern_type", nullable = true)
    private LecternType lectern_type_id;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public LecternType getLectern_type_id() {
        return lectern_type_id;
    }

    public void setLectern_type_id(LecternType lectern_type_id) {
        this.lectern_type_id = lectern_type_id;
    }

    public Lectern() {
    }

    public Faculty getFaculty_idfaculty() {
        return faculty_idfaculty;
    }

    public void setFaculty_idfaculty(Faculty faculty_idfaculty) {
        this.faculty_idfaculty = faculty_idfaculty;
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
    
}

