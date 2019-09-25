
package com.javamaster.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Turn")

@NamedQueries({
@NamedQuery(name = "Turn.getAll", query = "SELECT c from Turn c"),
@NamedQuery(name = "Turn.getById", query = "SELECT c from Turn c where c.id=:id")
}) 


public class Turn {

    
    @Id
    @Column(name = "idturn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "turncol")
    private String turncol;


    public Turn() {
    }

    
    public String getTurncol() {
        return turncol;
    }
    
    public void setTurncol(String turncol) {
        this.turncol=turncol;
    }

}

