
package com.java.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Role")

@NamedQueries({
@NamedQuery(name = "Role.getAll", query = "SELECT c from Role c"),
@NamedQuery(name = "Role.getById", query = "SELECT c from Role c where c.id=:id")
}) 


public class Role {

    
    @Id
    @Column(name = "idrole")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "acess")
    private int acess;

    @Column(name = "type")
    private String type;


    public Role() {
    }

    
    public int getAcess() {
        return acess;
    }
    
    public void setAcess(int acess) {
        this.acess=acess;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type=type;
    }

}

