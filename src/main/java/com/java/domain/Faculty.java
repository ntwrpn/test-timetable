
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Faculty")

@NamedQueries({
@NamedQuery(name = "Faculty.getAll", query = "SELECT c from Faculty c"),
@NamedQuery(name = "Faculty.getById", query = "SELECT c from Faculty c where c.id=:id")
}) 


public class Faculty {

    
    @Id
    @Column(name = "idfaculty")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "corps_id")
    private int corps_id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "name")
    private String name;


    public Faculty() {
    }

    
    public int getCorps_id() {
        return corps_id;
    }
    
    public void setCorps_id(int corps_id) {
        this.corps_id=corps_id;
    }

    public String getFullname() {
        return fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname=fullname;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }

}

