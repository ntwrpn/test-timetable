
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Subject")

@NamedQueries({
@NamedQuery(name = "Subject.getAll", query = "SELECT c from Subject c"),
@NamedQuery(name = "Subject.getById", query = "SELECT c from Subject c where c.id=:id")
}) 


public class Subject {

    
    @Id
    @Column(name = "idsubject")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name = "special_info")
    private String special_info;


    public Subject() {
    }

    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

    public String getSpecial_info() {
        return special_info;
    }
    
    public void setSpecial_info(String special_info) {
        this.special_info=special_info;
    }

}

