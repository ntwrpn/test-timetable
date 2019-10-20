
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Speciality")

@NamedQueries({
@NamedQuery(name = "Speciality.getAll", query = "SELECT c from Speciality c"),
@NamedQuery(name = "Speciality.getById", query = "SELECT c from Speciality c where c.id=:id")
}) 


public class Speciality {

    
    @Id
    @Column(name = "idspeciality")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "descr")
    private String descr;

    @Column(name = "lectern_id")
    private int lectern_id;

    @Column(name = "name")
    private String name;


    public Speciality() {
    }

    
    public String getDescr() {
        return descr;
    }
    
    public void setDescr(String descr) {
        this.descr=descr;
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

}

